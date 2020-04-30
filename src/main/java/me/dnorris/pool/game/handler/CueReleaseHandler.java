package me.dnorris.pool.game.handler;

import me.dnorris.pool.arena.GameArena;
import me.dnorris.pool.arena.key.KeyEventType;
import me.dnorris.pool.arena.key.KeyHandler;
import me.dnorris.pool.data.vector.implementation.Vector2D;
import me.dnorris.pool.game.GameEntity;

import java.awt.event.KeyEvent;

public class CueReleaseHandler {

    @KeyHandler(keyCode = KeyEvent.VK_SPACE, getType = KeyEventType.KEY_PRESSED)
    public void onSpaceBarPressed(GameArena arena, KeyEvent event) {
        // TODO: 30/04/2020
    }

}
