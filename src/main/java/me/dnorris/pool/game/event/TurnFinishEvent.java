package me.dnorris.pool.game.event;

import me.dnorris.pool.arena.Entity;
import me.dnorris.pool.arena.event.Event;
import me.dnorris.pool.arena.event.handler.EventListener;
import me.dnorris.pool.game.GameData;
import me.dnorris.pool.game.team.Team;

import java.util.List;

public class TurnFinishEvent implements Event {

    private static final EventListener EVENT_HANDLER = new EventListener();

    private final GameData activeGame;
    private final Team turn;
    private final Entity firstCollision;
    private final List<Entity> pottedBalls;

    public TurnFinishEvent(GameData activeGame, Team turn, Entity firstCollision, List<Entity> pottedBalls) {
        this.activeGame = activeGame;
        this.turn = turn;
        this.firstCollision = firstCollision;
        this.pottedBalls = pottedBalls;
    }

    public GameData getActiveGame() {
        return this.activeGame;
    }

    public Team getTurn() {
        return this.turn;
    }

    public Entity getFirstCollision() {
        return this.firstCollision;
    }

    public List<Entity> getPottedBalls() {
        return this.pottedBalls;
    }

    @Override
    public EventListener getHandler() {
        return EVENT_HANDLER;
    }

    public static EventListener getHandlers() {
        return EVENT_HANDLER;
    }
}
