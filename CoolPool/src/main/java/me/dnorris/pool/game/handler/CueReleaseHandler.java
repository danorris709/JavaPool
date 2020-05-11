package me.dnorris.pool.game.handler;

import me.dnorris.arena.GameArena;
import me.dnorris.arena.entity.shape.LineEntity;
import me.dnorris.arena.key.KeyEventType;
import me.dnorris.arena.key.KeyHandler;
import me.dnorris.data.vector.Vector;
import me.dnorris.data.vector.implementation.Vector2D;
import me.dnorris.pool.game.GameEntity;
import me.dnorris.pool.game.GameFactory;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

/**
 *
 * A handler for when the space bar is used to shoot the cue ball
 *
 * @author https://github.com/danorris709
 */
public class CueReleaseHandler {

    @KeyHandler(keyCode = KeyEvent.VK_SPACE, getType = KeyEventType.KEY_PRESSED)
    public void onSpaceBarPressed(GameArena arena, InputEvent event) {
        if(!GameFactory.getActiveGame().haveBallsStoppedMoving() || GameFactory.getActiveGame().isCueBallInHand()) {
            return;
        }

        GameEntity gameEntity = GameFactory.getActiveGame().getGameEntities();
        LineEntity line = gameEntity.getPointer();
        Vector normalVector = new Vector2D(line.getEndPoint(), line.getLocation()).normalize();
        double strength = gameEntity.getPercentageBar().getFilledPercentage();
        Vector vector = normalVector.multiply(Math.max(1, strength) * 0.1);

        gameEntity.getCueBall().setMotion(vector);
        arena.removeEntity(gameEntity.getPointer());
    }
}
