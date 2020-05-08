package me.dnorris.pool.game.handler;

import me.dnorris.pool.arena.GameArena;
import me.dnorris.pool.arena.key.KeyEventType;
import me.dnorris.pool.arena.key.KeyHandler;
import me.dnorris.pool.data.location.Location;
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
        GameEntity.getPointer().setLocation(GameEntity.getCueBall().getLocation().clone());
        GameFactory.getActiveGame().getArena().addEntity(GameEntity.getPointer());
    }

    @KeyHandler(keyCode = MouseEvent.NOBUTTON, getType = KeyEventType.MOUSE_MOVED)
    public void onMouseMoved(GameArena arena, InputEvent event) {
        if(!GameFactory.getActiveGame().isCueBallInHand()) {
            return;
        }

        MouseEvent mouseEvent = (MouseEvent) event;
        Location newCueBallPoint = new Location2D(mouseEvent.getPoint());

        newCueBallPoint.setX(Math.max(110, newCueBallPoint.getX()));
        newCueBallPoint.setX(Math.min(350, newCueBallPoint.getX()));
        newCueBallPoint.setY(Math.max(110, newCueBallPoint.getY()));
        newCueBallPoint.setY(Math.min(590, newCueBallPoint.getY()));

        GameEntity.getCueBall().setLocation(newCueBallPoint);
    }
}
