package me.dnorris.pool.game.handler;

import me.dnorris.pool.arena.GameArena;
import me.dnorris.pool.arena.key.KeyEventType;
import me.dnorris.pool.arena.key.KeyHandler;
import me.dnorris.pool.game.GameEntity;

import java.awt.event.KeyEvent;

public class PointerDirectionHandler {

    @KeyHandler(keyCode = KeyEvent.VK_LEFT, getType = KeyEventType.KEY_PRESSED)
    public void onLeftArrowPressed(GameArena arena, KeyEvent event) {
        GameEntity.getPointer().setEndPoint(GameEntity.getPointer().getEndPoint().add(0, 10, 0));
    }


    @KeyHandler(keyCode = KeyEvent.VK_RIGHT, getType = KeyEventType.KEY_PRESSED)
    public void onRightArrowPressed(GameArena arena, KeyEvent event) {
        GameEntity.getPointer().setEndPoint(GameEntity.getPointer().getEndPoint().subtract(0, 10, 0));
    }
}
