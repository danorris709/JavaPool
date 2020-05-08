package me.dnorris.pool.game.event;

import me.dnorris.pool.arena.Entity;
import me.dnorris.pool.arena.event.Event;
import me.dnorris.pool.arena.event.handler.EventListener;

public class BallPotEvent implements Event {

    private static final EventListener EVENT_HANDLER = new EventListener();

    private final Entity ball;

    public BallPotEvent(Entity ball) {
        this.ball = ball;
    }

    public Entity getBall() {
        return this.ball;
    }

    @Override
    public EventListener getHandler() {
        return EVENT_HANDLER;
    }

    public static EventListener getHandlers() {
        return EVENT_HANDLER;
    }
}
