package me.dnorris.pool.game.event;

import me.dnorris.arena.Entity;
import me.dnorris.arena.event.Event;
import me.dnorris.arena.event.handler.EventListener;
import me.dnorris.pool.game.GameData;

/**
 *
 * An event called when two balls collide
 *
 * @author https://github.com/danorris709
 */
public class BallCollideEvent implements Event {

    private static final EventListener EVENT_HANDLER = new EventListener();

    private final GameData activeGame;
    private final Entity first;
    private final Entity second;

    /**
     *
     * Basic constructor
     *
     * @param activeGame The game being played
     * @param first First ball colliding (initiator)
     * @param second Second ball colliding
     */
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
