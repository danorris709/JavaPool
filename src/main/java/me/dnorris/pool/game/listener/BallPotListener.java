package me.dnorris.pool.game.listener;

import me.dnorris.pool.arena.entity.shape.ArcShape;
import me.dnorris.pool.arena.entity.shape.CircleEntity;
import me.dnorris.pool.arena.event.event.EntityCollisionEvent;
import me.dnorris.pool.arena.event.listener.EventHandler;
import me.dnorris.pool.arena.event.listener.Listener;
import me.dnorris.pool.game.GameData;

public class BallPotListener implements Listener {

    private final GameData currentGame;

    public BallPotListener(GameData currentGame) {
        this.currentGame = currentGame;
    }

    @EventHandler
    public void onEntityCollide(EntityCollisionEvent event) {
        if(!(event.getFirst() instanceof ArcShape) && !(event.getSecond() instanceof ArcShape)) {
            return;
        }

        if(!(event.getFirst() instanceof CircleEntity) && !(event.getSecond() instanceof CircleEntity)) {
            return;
        }

        // TODO: 07/05/2020 remove the ball and place it up above yanno
    }
}
