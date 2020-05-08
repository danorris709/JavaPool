package me.dnorris.pool.game.listener;

import me.dnorris.pool.arena.entity.shape.ArcShape;
import me.dnorris.pool.arena.entity.shape.CircleEntity;
import me.dnorris.pool.arena.event.Events;
import me.dnorris.pool.arena.event.event.EntityCollisionEvent;
import me.dnorris.pool.arena.event.listener.EventHandler;
import me.dnorris.pool.arena.event.listener.Listener;
import me.dnorris.pool.data.location.Location;
import me.dnorris.pool.data.location.implementation.Location2D;
import me.dnorris.pool.data.vector.implementation.Vector2D;
import me.dnorris.pool.game.GameData;
import me.dnorris.pool.game.GameEntity;
import me.dnorris.pool.game.GameFactory;
import me.dnorris.pool.game.event.BallPotEvent;
import me.dnorris.pool.game.team.Team;

import java.util.Objects;

public class BallPotListener implements Listener {

    private static final Location POTTED_BALLS = new Location2D(250, 50);
    private final GameData currentGame;

    public BallPotListener(GameData currentGame) {
        this.currentGame = currentGame;
    }

    @EventHandler
    public void onEntityCollide(EntityCollisionEvent event) {
        if(currentGame.isCueBallInHand()) {
            return;
        }

        if (!(event.getFirst() instanceof ArcShape) && !(event.getSecond() instanceof ArcShape)) {
            return;
        }

        if (!(event.getFirst() instanceof CircleEntity) && !(event.getSecond() instanceof CircleEntity)) {
            return;
        }

        CircleEntity ball = this.getBall(event);

        ball.setMotion(Vector2D.NONE);
        ball.setLocation(POTTED_BALLS.add(20 * this.currentGame.getPottedBalls(), 0, 0));
        this.currentGame.setPottedBalls(this.currentGame.getPottedBalls() + 1);

        if (Objects.equals(ball, GameEntity.getCueBall())) {
            this.handleCueBallPot(ball, this.currentGame);
        } else if (Objects.equals(ball, GameEntity.getBlackBall())) {
            Team team = this.currentGame.getTurn();

            if(this.currentGame.isOnBlackBall(team)) {
                // TODO: 08/05/2020  WON THE GAME!
                return;
            }

            // TODO: 08/05/2020 LOST THE GAME!
        } else {
            Events.callEvent(new BallPotEvent(this.currentGame, ball));
            // TODO: 07/05/2020 remove the ball and place it up above yanno
        }
    }

    private void handleCueBallPot(CircleEntity ball, GameData activeGame) {
        ball.setLocation(new Location2D(350, 350));
        GameFactory.getActiveGame().setCueBallInHand(true);
        GameFactory.getActiveGame().setTurn(GameFactory.getActiveGame().getTurn().getOpposition());
        GameFactory.getActiveGame().setShotsInTurn(2);
        activeGame.getArena().removeEntity(GameEntity.getPointer());
    }

    private CircleEntity getBall(EntityCollisionEvent event) {
        if(event.getFirst() instanceof CircleEntity) {
            return (CircleEntity) event.getFirst();
        }

        return (CircleEntity) event.getSecond();
    }
}
