package me.dnorris.pool.game;

import me.dnorris.pool.PoolClient;
import me.dnorris.pool.arena.Entity;
import me.dnorris.pool.arena.GameArena;
import me.dnorris.pool.arena.arena.jframe.SimpleArena;
import me.dnorris.pool.arena.entity.EntityBuilder;
import me.dnorris.pool.arena.entity.EntityType;
import me.dnorris.pool.arena.event.Events;
import me.dnorris.pool.game.data.BasicGameData;
import me.dnorris.pool.game.event.listener.BallCollisionListener;
import me.dnorris.pool.game.event.listener.BallTurnListener;
import me.dnorris.pool.game.handler.CuePlaceHandler;
import me.dnorris.pool.game.handler.CuePowerHandler;
import me.dnorris.pool.game.handler.CueReleaseHandler;
import me.dnorris.pool.game.handler.PointerDirectionHandler;
import me.dnorris.pool.game.listener.BallPotListener;
import me.dnorris.pool.game.listener.TurnFinishListener;
import me.dnorris.pool.game.team.Team;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;

public class GameFactory {

    private static final Entity[] POCKETS = new Entity[] {
            GameEntity.getTopLeftCornerHole(),
            GameEntity.getBottomLeftCornerHole(),
            GameEntity.getTopMiddleHole(),
            GameEntity.getBottomMiddleHole(),
            GameEntity.getTopRightCornerHole(),
            GameEntity.getBottomRightCornerHole()
    };

    private static GameData activeGame;

    public static void initBoard() {
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
        gameArena.addEntity(GameEntity.getTurnIdentifier());

        for(int i = 0; i < GameLocation.BALL_SPAWN_POINTS.length; i++) {
            gameArena.addEntity(getBall(i));
        }

        gameArena.addHandler(CuePowerHandler.class);
        gameArena.addHandler(PointerDirectionHandler.class);
        gameArena.addHandler(CueReleaseHandler.class);
        gameArena.addHandler(CuePlaceHandler.class);

        activeGame = new BasicGameData(gameArena);

        Events.registerListener(new BallPotListener(activeGame));
        Events.registerListener(new BallCollisionListener(activeGame));
        Events.registerListener(new BallTurnListener(activeGame));
        Events.registerListener(new TurnFinishListener(activeGame));
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

    public static void finishGame(Team winner) {
        GameData activeGame = getActiveGame();

        activeGame.getArena().shutdown();
        PoolClient.getScreenManager().finishGame(winner.getDisplayName() + " has won the game!", activeGame.getStartTime());
    }

    public static GameData getActiveGame() {
        return activeGame;
    }

    public static Entity[] getPockets() {
        return POCKETS;
    }
}
