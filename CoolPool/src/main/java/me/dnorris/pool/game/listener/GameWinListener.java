package me.dnorris.pool.game.listener;

import me.dnorris.arena.Entity;
import me.dnorris.arena.event.listener.EventHandler;
import me.dnorris.arena.event.listener.Listener;
import me.dnorris.pool.game.GameData;
import me.dnorris.pool.game.GameEntity;
import me.dnorris.pool.game.GameFactory;
import me.dnorris.pool.game.event.BallCollideEvent;
import me.dnorris.pool.game.event.TurnFinishEvent;

import java.util.Objects;

/**
 *
 * A listener to check if the player has won (or lost due to potting the cue off the black)
 *
 * @author https://github.com/danorris709
 */
public class GameWinListener implements Listener {

    private final GameData activeGame;

    private Entity lastCollision;

    /**
     *
     * @param activeGame Game linked to the listener
     */
    public GameWinListener(GameData activeGame) {
        this.activeGame = activeGame;
    }

    @EventHandler
    public void onTurnFinish(TurnFinishEvent event) {
        if(!this.activeGame.isOnBlackBall(event.getTurn())) {
            return;
        }

        if(lastCollision == null) {
            return;
        }

        GameEntity gameEntity = event.getActiveGame().getGameEntities();

        if(event.hasPottedPredicate(e -> Objects.equals(e, gameEntity.getCueBall())) && Objects.equals(gameEntity.getBlackBall(), this.lastCollision)) {
            GameFactory.finishGame(event.getTurn().getOpposition());
            return;
        }

        if(!event.hasPottedPredicate(e -> Objects.equals(e, gameEntity.getBlackBall()))) {
            return;
        }

        GameFactory.finishGame(event.getTurn());
    }

    @EventHandler
    public void onBallCollide(BallCollideEvent event) {
        GameEntity gameEntity = event.getActiveGame().getGameEntities();

        if (Objects.equals(event.getFirst(), gameEntity.getCueBall())) {
            lastCollision = event.getSecond();
        } else if (Objects.equals(event.getSecond(), gameEntity.getCueBall())) {
            lastCollision = event.getFirst();
        }
    }
}
