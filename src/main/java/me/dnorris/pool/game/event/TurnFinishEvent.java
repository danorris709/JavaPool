package me.dnorris.pool.game.event;

import me.dnorris.pool.arena.Entity;
import me.dnorris.pool.arena.event.Event;
import me.dnorris.pool.arena.event.handler.EventListener;
import me.dnorris.pool.game.team.Team;

import java.util.List;

public class TurnFinishEvent implements Event {

    private static final EventListener EVENT_HANDLER = new EventListener();

    private final Team turn;
    private final int ballsHit;
    private final List<Entity> pottedBalls;

    public TurnFinishEvent(Team turn, int ballsHit, List<Entity> pottedBalls) {
        this.turn = turn;
        this.ballsHit = ballsHit;
        this.pottedBalls = pottedBalls;
    }

    public Team getTurn() {
        return this.turn;
    }

    public int getBallsHit() {
        return this.ballsHit;
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
