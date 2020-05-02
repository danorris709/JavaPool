package me.dnorris.pool.game.handler;

import me.dnorris.pool.arena.GameArena;
import me.dnorris.pool.arena.entity.shape.LineEntity;
import me.dnorris.pool.arena.key.KeyEventType;
import me.dnorris.pool.arena.key.KeyHandler;
import me.dnorris.pool.data.vector.implementation.Vector2D;
import me.dnorris.pool.game.GameEntity;

import java.awt.event.KeyEvent;
import java.util.Objects;

public class CueReleaseHandler {

    private static final int MAX_POWER = 50;

    @KeyHandler(keyCode = KeyEvent.VK_SPACE, getType = KeyEventType.KEY_PRESSED)
    public void onSpaceBarPressed(GameArena arena, KeyEvent event) {
        if(!Objects.equals(GameEntity.getCueBall().getMotion(), Vector2D.NONE)) {
            System.out.println("Hello");
            System.out.println(GameEntity.getCueBall().getMotion().toString());
            return;
        }

        LineEntity line = GameEntity.getPointer();
        double opp = line.getEndPoint().getY() - line.getLocation().getY();
        double adj = line.getEndPoint().getX() - line.getLocation().getX();
        double angle = Math.atan(opp / adj);
        double strength = (10.0 + GameEntity.getPercentageBar().getFilledPercentage()) / 2.00;

        GameEntity.getCueBall().setMotion(new Vector2D(Math.cos(angle) * strength, Math.sin(angle) / strength));
    }
}
