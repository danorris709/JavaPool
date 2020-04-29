package me.dnorris.pool.game.handler;

import com.google.common.collect.Maps;
import me.dnorris.pool.arena.GameArena;
import me.dnorris.pool.arena.entity.shape.LineEntity;
import me.dnorris.pool.arena.key.KeyEventType;
import me.dnorris.pool.arena.key.KeyHandler;
import me.dnorris.pool.data.Pair;
import me.dnorris.pool.data.location.Location;
import me.dnorris.pool.game.GameEntity;

import java.awt.event.KeyEvent;
import java.util.Map;

public class PointerDirectionHandler {

    @KeyHandler(keyCode = { KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT }, getType = KeyEventType.KEY_PRESSED)
    public void onLeftArrowPressed(GameArena arena, KeyEvent event) {
        this.movePointerEndPoint(GameEntity.getPointer(), Arrow.fromKeyCode(event.getKeyCode()));
    }

    private void movePointerEndPoint(LineEntity line, Arrow arrow) {
        Location endPoint = line.getEndPoint();
        Pair<Integer, Integer> pair = arrow.moveLine(line);

        line.setEndPoint(endPoint.add(pair.getFirst(), pair.getSecond(), 0));
    }

    enum Arrow {

        LEFT(KeyEvent.VK_RIGHT) {
            @Override
            public Pair<Integer, Integer> moveLine(LineEntity lineEntity) {
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
            public Pair<Integer, Integer> moveLine(LineEntity lineEntity) {
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
        
        public abstract Pair<Integer, Integer> moveLine(LineEntity lineEntity);

        public static Arrow fromKeyCode(long keyCode) {
            return ARROW_MAP.get(keyCode);
        }
    }
}
