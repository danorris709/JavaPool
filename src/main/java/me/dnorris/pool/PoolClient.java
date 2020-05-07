package me.dnorris.pool;

import me.dnorris.pool.arena.Entity;
import me.dnorris.pool.arena.GameArena;
import me.dnorris.pool.arena.arena.jframe.SimpleArena;
import me.dnorris.pool.arena.entity.EntityBuilder;
import me.dnorris.pool.arena.entity.EntityType;
import me.dnorris.pool.arena.event.Events;
import me.dnorris.pool.arena.event.event.EntityCollisionEvent;
import me.dnorris.pool.arena.event.listener.EventHandler;
import me.dnorris.pool.arena.event.listener.Listener;
import me.dnorris.pool.game.GameEntity;
import me.dnorris.pool.game.GameLocation;
import me.dnorris.pool.game.handler.CuePowerHandler;
import me.dnorris.pool.game.handler.CueReleaseHandler;
import me.dnorris.pool.game.handler.PointerDirectionHandler;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;

public class PoolClient {

    public static void main(String[] args) {
        GameArena gameArena = new SimpleArena(new Dimension(1500, 750));

        gameArena.addEntity(GameEntity.getBorder());
        gameArena.addEntity(GameEntity.getCloth());
        gameArena.addEntity(GameEntity.getHeadString());
        gameArena.addEntity(GameEntity.getTopRightCornerHole());
        gameArena.addEntity(GameEntity.getTopLeftCornerHole());
        gameArena.addEntity(GameEntity.getTopMiddleHole());
        gameArena.addEntity(GameEntity.getBottomMiddleHole());
        gameArena.addEntity(GameEntity.getBottomRightCornerHole());
        gameArena.addEntity(GameEntity.getBottomLeftCornerHole());
        gameArena.addEntity(GameEntity.getBlackBallSpot());
        gameArena.addEntity(GameEntity.getCueBall());
        gameArena.addEntity(GameEntity.getBlackBall());
        gameArena.addEntity(GameEntity.getPercentageBar());
        gameArena.addEntity(GameEntity.getPointer());

        for(int i = 0; i < GameLocation.BALL_SPAWN_POINTS.length; i++) {
            gameArena.addEntity(getBall(i));
        }

        gameArena.addHandler(CuePowerHandler.class);
        gameArena.addHandler(PointerDirectionHandler.class);
        gameArena.addHandler(CueReleaseHandler.class);
    }

    private static Entity getBall(int position) {
        try {
            return new EntityBuilder()
                    .setLocation(GameLocation.BALL_SPAWN_POINTS[position].getFirst())
                    .setInteractable(true)
                    .setImmovable(false)
                    .setDimension(new Dimension(20, 20))
                    .setHollow(false)
                    .setColour(GameLocation.BALL_SPAWN_POINTS[position].getSecond())
                    .setType(EntityType.CIRCLE)
                    .build();
        } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
            e.printStackTrace();
        }

        return null;
    }
}
