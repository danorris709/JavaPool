package me.dnorris.pool.game.event.listener;

import me.dnorris.pool.arena.entity.shape.CircleEntity;
import me.dnorris.pool.arena.event.Events;
import me.dnorris.pool.arena.event.event.EntityCollisionEvent;
import me.dnorris.pool.arena.event.listener.EventHandler;
import me.dnorris.pool.arena.event.listener.Listener;
import me.dnorris.pool.game.GameData;
import me.dnorris.pool.game.event.BallCollideEvent;

public class BallCollisionListener implements Listener {

    private final GameData currentGame;

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
