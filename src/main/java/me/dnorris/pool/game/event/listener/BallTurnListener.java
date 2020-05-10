package me.dnorris.pool.game.event.listener;

import com.google.common.collect.Lists;
import me.dnorris.pool.arena.Entity;
import me.dnorris.pool.arena.event.Events;
import me.dnorris.pool.arena.event.event.EntityStopMovingEvent;
import me.dnorris.pool.arena.event.listener.EventHandler;
import me.dnorris.pool.arena.event.listener.Listener;
import me.dnorris.pool.game.GameData;
import me.dnorris.pool.game.GameFactory;
import me.dnorris.pool.game.event.BallCollideEvent;
import me.dnorris.pool.game.event.BallPotEvent;
import me.dnorris.pool.game.event.TurnFinishEvent;

import java.util.List;
import java.util.Objects;

public class BallTurnListener implements Listener {

    private final GameData activeGame;

    private Entity firstHit;
    private List<Entity> pottedBalls = Lists.newArrayList();

    public BallTurnListener(GameData activeGame) {
        this.activeGame = activeGame;
    }

    @EventHandler
    public void onTurnFinish(EntityStopMovingEvent event) {
        if(!this.activeGame.haveBallsStoppedMoving() || this.activeGame.isCueBallInHand()) {
            return;
        }

        Events.callEvent(new TurnFinishEvent(this.activeGame, this.activeGame.getTurn(), this.firstHit, this.pottedBalls));
        this.firstHit = null;
        this.pottedBalls = Lists.newArrayList();
    }

    @EventHandler
    public void onBallPotEvent(BallPotEvent event) {
        this.pottedBalls.add(event.getBall());
    }

    @EventHandler
    public void onBallCollision(BallCollideEvent event) {
        if(this.firstHit != null) {
            return;
        }

        boolean firstBallCue = Objects.equals(GameFactory.getActiveGame().getGameEntities().getCueBall(), event.getFirst());
        boolean secondBallCue = Objects.equals(GameFactory.getActiveGame().getGameEntities().getCueBall(), event.getSecond());

        if(!firstBallCue && !secondBallCue) {
            return;
        }

        if(firstBallCue) {
            this.firstHit = event.getSecond();
        }else {
            this.firstHit = event.getFirst();
        }
    }
}
