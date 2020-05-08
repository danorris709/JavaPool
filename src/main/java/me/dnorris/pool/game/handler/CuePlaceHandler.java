package me.dnorris.pool.game.handler;

import me.dnorris.pool.arena.GameArena;
import me.dnorris.pool.arena.key.KeyEventType;
import me.dnorris.pool.arena.key.KeyHandler;
import me.dnorris.pool.data.location.implementation.Location2D;
import me.dnorris.pool.game.GameEntity;
import me.dnorris.pool.game.GameFactory;

import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;

public class CuePlaceHandler {

    @KeyHandler(keyCode = MouseEvent.BUTTON1, getType = KeyEventType.MOUSE_CLICKED)
    public void onMouseClicked(GameArena arena, InputEvent event) {
        if(!GameFactory.getActiveGame().isCueBallInHand()) {
            return;
        }

        GameFactory.getActiveGame().setCueBallInHand(false);
    }

    @KeyHandler(keyCode = MouseEvent.NOBUTTON, getType = KeyEventType.MOUSE_MOVED)
    public void onMouseMoved(GameArena arena, InputEvent event) {
        if(!GameFactory.getActiveGame().isCueBallInHand()) {
            return;
        }

        MouseEvent mouseEvent = (MouseEvent) event;

        GameEntity.getCueBall().setLocation(new Location2D(mouseEvent.getLocationOnScreen()));
    }
}
