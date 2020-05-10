package me.dnorris.pool.game.listener;

import me.dnorris.pool.arena.Entity;
import me.dnorris.pool.arena.event.listener.EventHandler;
import me.dnorris.pool.arena.event.listener.Listener;
import me.dnorris.pool.game.GameData;
import me.dnorris.pool.game.GameEntity;
import me.dnorris.pool.game.GameFactory;
import me.dnorris.pool.game.event.BallCollideEvent;
import me.dnorris.pool.game.event.TurnFinishEvent;

import java.util.Objects;

public class GameWinListener implements Listener {

    private final GameData activeGame;

    private Entity lastCollision;

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

        if(event.hasPottedPredicate(e -> Objects.equals(e, GameEntity.getCueBall())) && Objects.equals(GameEntity.getBlackBall(), this.lastCollision)) {
            GameFactory.finishGame(event.getTurn().getOpposition());
            return;
        }

        if(!event.hasPottedPredicate(e -> Objects.equals(e, GameEntity.getBlackBall()))) {
            return;
        }

        GameFactory.finishGame(event.getTurn());
    }

    @EventHandler
    public void onBallCollide(BallCollideEvent event) {
        if (Objects.equals(event.getFirst(), GameEntity.getCueBall())) {
            lastCollision = event.getSecond();
        } else if (Objects.equals(event.getSecond(), GameEntity.getCueBall())) {
            lastCollision = event.getFirst();
        }
    }
}
