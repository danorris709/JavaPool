package me.dnorris.pool;

import me.dnorris.pool.arena.GameArena;
import me.dnorris.pool.arena.arena.jframe.SimpleArena;
import me.dnorris.pool.game.GameEntity;

import java.awt.*;

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
    }
}
