package me.dnorris.pool.game.handler;

import com.google.common.collect.Maps;
import me.dnorris.arena.GameArena;
import me.dnorris.arena.entity.shape.LineEntity;
import me.dnorris.arena.key.KeyEventType;
import me.dnorris.arena.key.KeyHandler;
import me.dnorris.data.Pair;
import me.dnorris.data.location.Location;
import me.dnorris.pool.game.GameFactory;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.Map;

/**
 *
 * Handler class for using the arrow keys, and shift key, to move the pointer around the board.
 * When the shift key is pressed the movement of the pointer is slowed to 1 pixel per movement.
 *
 * @author https://github.com/danorris709
 */
public class PointerDirectionHandler {

    private boolean slowed = false;

    @KeyHandler(keyCode = { KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT }, getType = KeyEventType.KEY_PRESSED)
    public void onLeftArrowPressed(GameArena arena, InputEvent event) {
        if(!GameFactory.getActiveGame().haveBallsStoppedMoving() || GameFactory.getActiveGame().isCueBallInHand()) {
            return;
        }

        this.movePointerEndPoint(arena, GameFactory.getActiveGame().getGameEntities().getPointer(), Arrow.fromKeyCode(((KeyEvent) event).getKeyCode()));
    }

    @KeyHandler(keyCode = { KeyEvent.VK_SHIFT }, getType = KeyEventType.KEY_PRESSED)
    public void onShiftHeld(GameArena arena, InputEvent event) {
        this.slowed = true;
    }

    @KeyHandler(keyCode = { KeyEvent.VK_SHIFT }, getType = KeyEventType.KEY_RELEASED)
    public void onShiftRelease(GameArena arena, InputEvent event) {
        this.slowed = false;
    }

    /**
     *
     * Moves the pointer around the board depending on the direction
     * Adds the pointer to the board if it was removed and not replaced
     *
     * @param arena The game arena being moved around
     * @param line The line being moved
     * @param arrow The direction being moved in
     */
    private void movePointerEndPoint(GameArena arena, LineEntity line, Arrow arrow) {
        if(line.getArena() == null) {
            arena.addEntity(line);
            line.setLocation(GameFactory.getActiveGame().getGameEntities().getCueBall().getLocation());
        }

        line.setEndPoint(arrow.moveLine(line, this.slowed));
    }

    /**
     *
     * Enum used for determining the movement of the pointer.
     * Local to this class as it's only ever used here
     *
     * @author https://github.com/danorris709
     */
    enum Arrow {

        LEFT(KeyEvent.VK_RIGHT) {
            @Override
            public Pair<Integer, Integer> getNextPosition(LineEntity lineEntity) {
                Location endPoint = lineEntity.getEndPoint();

                if (endPoint.getY() == TOP_Y_POINT) {
                    if (endPoint.getX() == RIGHT_X_POINT || endPoint.getX() != LEFT_X_POINT) {
                        return new Pair<>(-10, 0);
                    }

                    return new Pair<>(0, 10);
                } else if (endPoint.getY() == BOTTOM_Y_POINT) {
                    if (endPoint.getX() == RIGHT_X_POINT) {
                        return new Pair<>(0, -10);
                    }

                    return new Pair<>(10, 0);
                } else {
                    if(endPoint.getX() == LEFT_X_POINT) {
                        return new Pair<>(0, 10);
                    }

                    return new Pair<>(0, -10);
                }
            }
        },
        RIGHT(KeyEvent.VK_LEFT) {
            @Override
            public Pair<Integer, Integer> getNextPosition(LineEntity lineEntity) {
                Location endPoint = lineEntity.getEndPoint();

                if (endPoint.getY() == TOP_Y_POINT) {
                    if (endPoint.getX() == RIGHT_X_POINT) {
                        return new Pair<>(0, 10);
                    }

                    return new Pair<>(10, 0);
                } else if (endPoint.getY() == BOTTOM_Y_POINT) {
                    if (endPoint.getX() == LEFT_X_POINT) {
                        return new Pair<>(0, -10);
                    }

                    return new Pair<>(-10, 0);
                } else {
                    if(endPoint.getX() == LEFT_X_POINT) {
                        return new Pair<>(0, -10);
                    }

                    return new Pair<>(0, 10);
                }
            }
        },

        ;

        private static final int LEFT_X_POINT = 100;
        private static final int RIGHT_X_POINT = 100 + (1000 / 4) + 750;
        private static final int TOP_Y_POINT = 100;
        private static final int BOTTOM_Y_POINT = 600;

        private static final Map<Long, Arrow> ARROW_MAP = Maps.newHashMap();

        private final long keyCode;

        static {
            for (Arrow value : values()) {
                ARROW_MAP.put(value.keyCode, value);
            }
        }

        Arrow(long keyCode) {
            this.keyCode = keyCode;
        }
        
        public abstract Pair<Integer, Integer> getNextPosition(LineEntity lineEntity);

        public Location moveLine(LineEntity lineEntity, boolean slowed) {
            Location endPoint = this.getUpdateNextPos(lineEntity, slowed);

            if(endPoint.getX() <= LEFT_X_POINT) {
                endPoint.setX(LEFT_X_POINT);
            }else if(endPoint.getX() >= RIGHT_X_POINT) {
                endPoint.setX(RIGHT_X_POINT);
            }

            if(endPoint.getY() <= TOP_Y_POINT) {
                endPoint.setY(TOP_Y_POINT);
            }else if(endPoint.getY() >= BOTTOM_Y_POINT) {
                endPoint.setY(BOTTOM_Y_POINT);
            }

            return endPoint;
        }

        private Location getUpdateNextPos(LineEntity lineEntity, boolean slowed) {
            Pair<Integer, Integer> nextPosition = getNextPosition(lineEntity);

            if (nextPosition.getFirst() != 0) {
                if (nextPosition.getFirst() < 0) {
                    nextPosition = new Pair<>(-1, 0);
                } else {
                    nextPosition = new Pair<>(1, 0);
                }
            } else {
                if (nextPosition.getSecond() < 0) {
                    nextPosition = new Pair<>(0, -1);
                } else {
                    nextPosition = new Pair<>(0, 1);
                }
            }

            if(!slowed) {
                nextPosition = new Pair<>(nextPosition.getFirst() * 50, nextPosition.getSecond() * 50);
            }

            return lineEntity.getEndPoint().add(nextPosition.getFirst(), nextPosition.getSecond(), 0);
        }

        public static Arrow fromKeyCode(long keyCode) {
            return ARROW_MAP.get(keyCode);
        }
    }
}
