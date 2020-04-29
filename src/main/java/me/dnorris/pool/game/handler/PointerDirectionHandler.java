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
        GameEntity.getPointer().setEndPoint(GameEntity.getPointer().getEndPoint().add(0, 10, 0));
    }

    private void movePointerEndPoint(LineEntity line, int deltaX, int deltaY) {
        Location endPoint = line.getEndPoint();

        if(endPoint.getX() != 100 && endPoint.getX() != 1000) {
            deltaY = 0;
        }

        if(endPoint.getY() != 100 && endPoint.getY() != 200) {
            deltaX = 0;
        }

        line.setEndPoint(endPoint.add(deltaX, deltaY, 0));
    }

    enum Arrow {

        LEFT(KeyEvent.VK_RIGHT),
        RIGHT(KeyEvent.VK_LEFT),

        ;

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
        
        public Pair<Integer, Integer> moveLine(LineEntity lineEntity) {
            return null; // TODO: 30/04/2020  
        }
    }
}
