package me.dnorris.game.event;

import me.dnorris.arena.Entity;
import me.dnorris.arena.event.Event;
import me.dnorris.arena.event.handler.EventListener;
import me.dnorris.game.GameData;
import me.dnorris.game.team.Team;

import java.util.List;
import java.util.function.Predicate;

/**
 *
 * An event called when all the balls on the pool table stop moving.
 * Used for rule logic
 *
 * @author https://github.com/danorris709
 */
public class TurnFinishEvent implements Event {

    private static final EventListener EVENT_HANDLER = new EventListener();

    private final GameData activeGame;
    private final Team turn;
    private final Entity firstCollision;
    private final List<Entity> pottedBalls;

    /**
     *
     * Basic constructor
     *
     * @param activeGame The game being run in
     * @param turn The player who's turn it is
     * @param firstCollision The first collision of the turn
     * @param pottedBalls The balls potted during the turn
     */
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

    /**
     *
     * Determine if the predicate is ever true for all the potted balls.
     * Iterates through each of the potted balls and tests the predicate against them.
     * If true it'll return true.
     * Returns false otherwise
     *
     * @param predicate To test against all potted balls
     * @return True if predicate is ever true
     */
    public boolean hasPottedPredicate(Predicate<Entity> predicate) {
        for (Entity pottedBall : this.pottedBalls) {
            if(predicate.test(pottedBall)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public EventListener getHandler() {
        return EVENT_HANDLER;
    }

    public static EventListener getHandlers() {
        return EVENT_HANDLER;
    }
}
