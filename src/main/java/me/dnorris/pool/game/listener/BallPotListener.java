package me.dnorris.pool.game.listener;

import me.dnorris.pool.arena.entity.shape.ArcShape;
import me.dnorris.pool.arena.entity.shape.CircleEntity;
import me.dnorris.pool.arena.event.event.EntityCollisionEvent;
import me.dnorris.pool.arena.event.listener.EventHandler;
import me.dnorris.pool.arena.event.listener.Listener;
import me.dnorris.pool.data.vector.implementation.Vector2D;
import me.dnorris.pool.game.GameData;
import me.dnorris.pool.game.GameEntity;

import java.util.Objects;

public class BallPotListener implements Listener {

    private final GameData currentGame;

    public BallPotListener(GameData currentGame) {
        this.currentGame = currentGame;
    }

    @EventHandler
    public void onEntityCollide(EntityCollisionEvent event) {
        if (!(event.getFirst() instanceof ArcShape) && !(event.getSecond() instanceof ArcShape)) {
            return;
        }

        if (!(event.getFirst() instanceof CircleEntity) && !(event.getSecond() instanceof CircleEntity)) {
            return;
        }

        CircleEntity ball = this.getBall(event);

        ball.setMotion(Vector2D.NONE);

        if (Objects.equals(ball, GameEntity.getCueBall())) {
            // TODO: 07/05/2020
        } else if (Objects.equals(ball, GameEntity.getBlackBall())) {
            // TODO: 07/05/2020
        } else {
            // TODO: 07/05/2020 remove the ball and place it up above yanno
        }
    }

    private CircleEntity getBall(EntityCollisionEvent event) {
        if(event.getFirst() instanceof CircleEntity) {
            return (CircleEntity) event.getFirst();
        }

        return (CircleEntity) event.getSecond();
    }
}
