package me.dnorris.pool.game.handler;

import me.dnorris.pool.arena.GameArena;
import me.dnorris.pool.arena.entity.shape.LineEntity;
import me.dnorris.pool.arena.key.KeyEventType;
import me.dnorris.pool.arena.key.KeyHandler;
import me.dnorris.pool.data.vector.Vector;
import me.dnorris.pool.data.vector.implementation.Vector2D;
import me.dnorris.pool.game.GameEntity;
import me.dnorris.pool.game.GameFactory;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class CueReleaseHandler {

    private static final int MAX_POWER = 50;

    @KeyHandler(keyCode = KeyEvent.VK_SPACE, getType = KeyEventType.KEY_PRESSED)
    public void onSpaceBarPressed(GameArena arena, InputEvent event) {
        if(!GameFactory.getActiveGame().haveBallsStoppedMoving() || GameFactory.getActiveGame().isCueBallInHand()) {
            return;
        }

        LineEntity line = GameEntity.getPointer();
        Vector normalVector = new Vector2D(line.getEndPoint(), line.getLocation()).normalize();
        double strength = GameEntity.getPercentageBar().getFilledPercentage();
        Vector vector = normalVector.multiply(Math.max(1, strength) * 0.1);

        GameEntity.getCueBall().setMotion(vector);
        arena.removeEntity(GameEntity.getPointer());
    }
}
