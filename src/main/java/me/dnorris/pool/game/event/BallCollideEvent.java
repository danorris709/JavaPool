package me.dnorris.pool.game.event;

import me.dnorris.pool.arena.Entity;
import me.dnorris.pool.arena.event.Event;
import me.dnorris.pool.arena.event.handler.EventListener;
import me.dnorris.pool.game.GameData;

public class BallCollideEvent implements Event {

    private static final EventListener EVENT_HANDLER = new EventListener();

    private final GameData activeGame;
    private final Entity first;
    private final Entity second;

    public BallCollideEvent(GameData activeGame, Entity first, Entity second) {
        this.activeGame = activeGame;
        this.first = first;
        this.second = second;
    }

    public GameData getActiveGame() {
        return this.activeGame;
    }

    public Entity getFirst() {
        return this.first;
    }

    public Entity getSecond() {
        return this.second;
    }

    @Override
    public EventListener getHandler() {
        return EVENT_HANDLER;
    }

    public static EventListener getHandlers() {
        return EVENT_HANDLER;
    }
}
