package me.dnorris.pool.game.handler;

import me.dnorris.pool.arena.GameArena;
import me.dnorris.pool.arena.key.KeyEventType;
import me.dnorris.pool.arena.key.KeyHandler;
import me.dnorris.pool.game.GameFactory;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class CuePowerHandler {

    @KeyHandler(keyCode = KeyEvent.VK_UP, getType = KeyEventType.KEY_PRESSED)
    public void onUpArrowPressed(GameArena arena, InputEvent event) {
        GameFactory.getActiveGame().getGameEntities().getPercentageBar().addPercentage(10);
    }

    @KeyHandler(keyCode = KeyEvent.VK_DOWN, getType = KeyEventType.KEY_PRESSED)
    public void onDownArrowPressed(GameArena arena, InputEvent event) {
        GameFactory.getActiveGame().getGameEntities().getPercentageBar().takePercentage(10);
    }
}
