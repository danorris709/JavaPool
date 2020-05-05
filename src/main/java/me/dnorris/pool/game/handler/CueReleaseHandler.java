package me.dnorris.pool.game.handler;

import me.dnorris.pool.arena.GameArena;
import me.dnorris.pool.arena.entity.shape.LineEntity;
import me.dnorris.pool.arena.key.KeyEventType;
import me.dnorris.pool.arena.key.KeyHandler;
import me.dnorris.pool.data.vector.Vector;
import me.dnorris.pool.data.vector.implementation.Vector2D;
import me.dnorris.pool.game.GameEntity;

import java.awt.event.KeyEvent;
import java.util.Objects;

public class CueReleaseHandler {

    private static final int MAX_POWER = 50;

    @KeyHandler(keyCode = KeyEvent.VK_SPACE, getType = KeyEventType.KEY_PRESSED)
    public void onSpaceBarPressed(GameArena arena, KeyEvent event) {
        if(!Objects.equals(GameEntity.getCueBall().getMotion(), Vector2D.NONE)) {
            return;
        }

        LineEntity line = GameEntity.getPointer();
        Vector normalVector = new Vector2D(line.getEndPoint(), line.getLocation()).normalize();
        double strength = GameEntity.getPercentageBar().getFilledPercentage();
        Vector vector = normalVector.multiply(Math.max(1, strength));

        GameEntity.getCueBall().setMotion(vector.normalize());
        arena.removeEntity(GameEntity.getPointer());
    }
}
