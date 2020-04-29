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

        // TODO: 30/04/2020  
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

        public static Arrow fromKeyCode(long keyCode) {
            return ARROW_MAP.get(keyCode);
        }
    }
}
