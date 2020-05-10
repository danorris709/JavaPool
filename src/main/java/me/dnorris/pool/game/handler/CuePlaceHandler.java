package me.dnorris.pool.game.handler;

import me.dnorris.pool.arena.Entity;
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

        GameEntity gameEntity = GameFactory.getActiveGame().getGameEntities();

        if(this.canPlacePointer(gameEntity.getCueBall())) {
            GameFactory.getActiveGame().setCueBallInHand(false);
            gameEntity.getPointer().setLocation(gameEntity.getCueBall().getLocation().clone());
            GameFactory.getActiveGame().getArena().addEntity(gameEntity.getPointer());
        }
    }

    private boolean canPlacePointer(Entity cueBall) {
        for(Entity entity : GameFactory.getPockets(GameFactory.getActiveGame())) {
            if(entity.getHitbox().isColliding(cueBall.getHitbox())) {
                return false;
            }
        }

        return true;
    }

    @KeyHandler(keyCode = MouseEvent.NOBUTTON, getType = KeyEventType.MOUSE_MOVED)
    public void onMouseMoved(GameArena arena, InputEvent event) {
        if(!GameFactory.getActiveGame().isCueBallInHand()) {
            return;
        }

        MouseEvent mouseEvent = (MouseEvent) event;
        Location newCueBallPoint = new Location2D(mouseEvent.getPoint());

        newCueBallPoint.setX(Math.max(115, newCueBallPoint.getX()));
        newCueBallPoint.setX(Math.min(350, newCueBallPoint.getX()));
        newCueBallPoint.setY(Math.max(115, newCueBallPoint.getY()));
        newCueBallPoint.setY(Math.min(585, newCueBallPoint.getY()));

        GameFactory.getActiveGame().getGameEntities().getCueBall().setLocation(newCueBallPoint);
    }
}
