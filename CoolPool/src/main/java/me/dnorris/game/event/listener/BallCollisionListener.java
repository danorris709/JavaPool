package me.dnorris.game.event.listener;

import me.dnorris.arena.entity.shape.CircleEntity;
import me.dnorris.arena.event.Events;
import me.dnorris.arena.event.event.EntityCollisionEvent;
import me.dnorris.arena.event.listener.EventHandler;
import me.dnorris.arena.event.listener.Listener;
import me.dnorris.game.GameData;
import me.dnorris.game.event.BallCollideEvent;

/**
 *
 * A listener to call the {@link BallCollideEvent} as an abstraction, and simplification, of {@link EntityCollisionEvent}
 *
 * @author https://github.com/danorris709
 */
public class BallCollisionListener implements Listener {

    private final GameData currentGame;

    /**
     *
     * @param currentGame Linking the game to the listener
     */
    public BallCollisionListener(GameData currentGame) {
        this.currentGame = currentGame;
    }

    @EventHandler
    public void onEntityCollide(EntityCollisionEvent event) {
        if (!(event.getFirst() instanceof CircleEntity) || !(event.getSecond() instanceof CircleEntity)) {
            return;
        }

        Events.callEvent(new BallCollideEvent(this.currentGame, event.getFirst(), event.getSecond()));
    }
}
