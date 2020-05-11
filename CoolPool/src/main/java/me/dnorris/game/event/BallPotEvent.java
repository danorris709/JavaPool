package me.dnorris.game.event;

import me.dnorris.arena.Entity;
import me.dnorris.arena.event.Event;
import me.dnorris.arena.event.handler.EventListener;
import me.dnorris.game.GameData;

/**
 *
 * An event called when a ball is potted.
 *
 * @author https://github.com/danorris709
 */
public class BallPotEvent implements Event {

    private static final EventListener EVENT_HANDLER = new EventListener();

    private final GameData activeGame;
    private final Entity ball;

    /**
     *
     * Basic constructor
     *
     * @param activeGame The game being played
     * @param ball The ball potted
     */
    public BallPotEvent(GameData activeGame, Entity ball) {
        this.activeGame = activeGame;
        this.ball = ball;
    }

    public GameData getActiveGame() {
        return this.activeGame;
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
