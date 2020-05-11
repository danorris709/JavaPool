package me.dnorris.pool.game.listener;

import me.dnorris.arena.Entity;
import me.dnorris.arena.entity.shape.ArcShape;
import me.dnorris.arena.entity.shape.CircleEntity;
import me.dnorris.arena.event.EventFactory;
import me.dnorris.arena.event.Events;
import me.dnorris.arena.event.event.EntityCollisionEvent;
import me.dnorris.arena.event.listener.EventHandler;
import me.dnorris.arena.event.listener.Listener;
import me.dnorris.data.location.Location;
import me.dnorris.data.location.implementation.Location2D;
import me.dnorris.data.vector.implementation.Vector2D;
import me.dnorris.pool.game.GameData;
import me.dnorris.pool.game.GameFactory;
import me.dnorris.pool.game.event.BallPotEvent;
import me.dnorris.pool.game.team.Team;

import java.util.Objects;

/**
 *
 * A listener to handle the {@link BallPotEvent}
 *
 * @author https://github.com/danorris709
 */
public class BallPotListener implements Listener {

    private static final Location POTTED_BALLS = new Location2D(350, 50);
    private final GameData currentGame;

    /**
     *
     * @param currentGame The game linked to the listener
     */
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

    /**
     *
     * Determines if the {@link EntityCollisionEvent} is between a ball and a pocket
     *
     * @param event The event being checked
     * @return True if a valid pocket collision
     */
    private boolean isBallToPocketCollision(EntityCollisionEvent event) {
        if(currentGame.isCueBallInHand()) {
            return false;
        }

        if(!(event.getFirst() instanceof ArcShape) && !(event.getSecond() instanceof ArcShape)) {
            return false;
        }

        return (event.getFirst() instanceof CircleEntity) || (event.getSecond() instanceof CircleEntity);
    }

    /**
     *
     * Handles putting the ball above the table and going back a spot for the white ball (as
     * it gets replaced)
     *
     * @param ball The ball being put in the rack
     */
    private void putBallInRack(Entity ball) {
        ball.setMotion(Vector2D.NONE);
        ball.setLocation(POTTED_BALLS.add(20 * this.currentGame.getPottedBalls(), 0, 0));
        this.currentGame.setPottedBalls(this.currentGame.getPottedBalls() + 1);

        if(Objects.equals(ball, this.currentGame.getGameEntities().getCueBall())) {
            POTTED_BALLS.subtract(20, 0, 0);
        }
    }

    /**
     *
     * Determines the ball from the event and returns it casted to a {@link CircleEntity}
     *
     * @param event The event being used
     * @return The ball being obtained
     */
    private CircleEntity getBall(EntityCollisionEvent event) {
        if(event.getFirst() instanceof CircleEntity) {
            return (CircleEntity) event.getFirst();
        }

        return (CircleEntity) event.getSecond();
    }
}
