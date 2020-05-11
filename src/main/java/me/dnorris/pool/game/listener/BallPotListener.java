package me.dnorris.pool.game.listener;

import me.dnorris.pool.arena.Entity;
import me.dnorris.pool.arena.entity.shape.ArcShape;
import me.dnorris.pool.arena.entity.shape.CircleEntity;
import me.dnorris.pool.arena.event.EventFactory;
import me.dnorris.pool.arena.event.Events;
import me.dnorris.pool.arena.event.event.EntityCollisionEvent;
import me.dnorris.pool.arena.event.listener.EventHandler;
import me.dnorris.pool.arena.event.listener.Listener;
import me.dnorris.pool.data.location.Location;
import me.dnorris.pool.data.location.implementation.Location2D;
import me.dnorris.pool.data.vector.implementation.Vector2D;
import me.dnorris.pool.game.GameData;
import me.dnorris.pool.game.GameFactory;
import me.dnorris.pool.game.event.BallPotEvent;
import me.dnorris.pool.game.team.Team;

import java.util.Objects;

public class BallPotListener implements Listener {

    private static final Location POTTED_BALLS = new Location2D(350, 50);
    private final GameData currentGame;

    public BallPotListener(GameData currentGame) {
        this.currentGame = currentGame;
    }

    @EventHandler
    public void onEntityCollide(EntityCollisionEvent event) {
        if(!this.isBallToPocketCollision(event)) {
            return;
        }

        CircleEntity ball = this.getBall(event);

        this.putBallInRack(ball);

        if (Objects.equals(ball, this.currentGame.getGameEntities().getBlackBall())) {
            Team team = this.currentGame.getTurn();

            if(this.currentGame.isOnBlackBall(team)) {
                Events.callEvent(new BallPotEvent(this.currentGame, ball));
                return;
            }

            GameFactory.finishGame(team.getOpposition());
        } else {
            Events.callEvent(new BallPotEvent(this.currentGame, ball));
        }

        EventFactory.callStopMovingEvent(ball);
    }

    private boolean isBallToPocketCollision(EntityCollisionEvent event) {
        if(currentGame.isCueBallInHand()) {
            return false;
        }

        if(!(event.getFirst() instanceof ArcShape) && !(event.getSecond() instanceof ArcShape)) {
            return false;
        }

        return (event.getFirst() instanceof CircleEntity) || (event.getSecond() instanceof CircleEntity);
    }

    private void putBallInRack(Entity ball) {
        ball.setMotion(Vector2D.NONE);
        ball.setLocation(POTTED_BALLS.add(20 * this.currentGame.getPottedBalls(), 0, 0));
        this.currentGame.setPottedBalls(this.currentGame.getPottedBalls() + 1);

        if(Objects.equals(ball, this.currentGame.getGameEntities().getCueBall())) {
            POTTED_BALLS.subtract(20, 0, 0);
        }
    }

    private CircleEntity getBall(EntityCollisionEvent event) {
        if(event.getFirst() instanceof CircleEntity) {
            return (CircleEntity) event.getFirst();
        }

        return (CircleEntity) event.getSecond();
    }
}
