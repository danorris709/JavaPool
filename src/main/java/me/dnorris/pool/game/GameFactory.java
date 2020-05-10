package me.dnorris.pool.game;

import me.dnorris.pool.PoolClient;
import me.dnorris.pool.arena.Entity;
import me.dnorris.pool.arena.GameArena;
import me.dnorris.pool.arena.arena.jframe.SimpleArena;
import me.dnorris.pool.arena.entity.EntityBuilder;
import me.dnorris.pool.arena.entity.EntityType;
import me.dnorris.pool.arena.event.Events;
import me.dnorris.pool.arena.event.event.EntityCollisionEvent;
import me.dnorris.pool.arena.event.event.EntityStopMovingEvent;
import me.dnorris.pool.game.data.BasicGameData;
import me.dnorris.pool.game.event.BallCollideEvent;
import me.dnorris.pool.game.event.TurnFinishEvent;
import me.dnorris.pool.game.event.listener.BallCollisionListener;
import me.dnorris.pool.game.event.listener.BallTurnListener;
import me.dnorris.pool.game.handler.CuePlaceHandler;
import me.dnorris.pool.game.handler.CuePowerHandler;
import me.dnorris.pool.game.handler.CueReleaseHandler;
import me.dnorris.pool.game.handler.PointerDirectionHandler;
import me.dnorris.pool.game.listener.BallPotListener;
import me.dnorris.pool.game.listener.GameWinListener;
import me.dnorris.pool.game.listener.TurnFinishListener;
import me.dnorris.pool.game.team.Team;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;

public class GameFactory {

    private static GameData activeGame;

    public static void initBoard() {
        GameArena gameArena = new SimpleArena(new Dimension(1500, 750));
        GameEntity gameEntity = new GameEntity();

        gameArena.addEntity(gameEntity.getBorder());
        gameArena.addEntity(gameEntity.getCloth());
        gameArena.addEntity(gameEntity.getHeadString());
        gameArena.addEntity(gameEntity.getTopRightCornerHole());
        gameArena.addEntity(gameEntity.getTopLeftCornerHole());
        gameArena.addEntity(gameEntity.getTopMiddleHole());
        gameArena.addEntity(gameEntity.getBottomMiddleHole());
        gameArena.addEntity(gameEntity.getBottomRightCornerHole());
        gameArena.addEntity(gameEntity.getBottomLeftCornerHole());
        gameArena.addEntity(gameEntity.getBlackBallSpot());
        gameArena.addEntity(gameEntity.getCueBall());
        gameArena.addEntity(gameEntity.getBlackBall());
        gameArena.addEntity(gameEntity.getPercentageBar());
        gameArena.addEntity(gameEntity.getTurnIdentifier());

        for(int i = 0; i < GameLocation.BALL_SPAWN_POINTS.length; i++) {
            gameArena.addEntity(getBall(i));
        }

        gameArena.addHandler(CuePowerHandler.class);
        gameArena.addHandler(PointerDirectionHandler.class);
        gameArena.addHandler(CueReleaseHandler.class);
        gameArena.addHandler(CuePlaceHandler.class);

        activeGame = new BasicGameData(gameArena, gameEntity);

        Events.registerListener(new BallPotListener(activeGame));
        Events.registerListener(new BallCollisionListener(activeGame));
        Events.registerListener(new BallTurnListener(activeGame));
        Events.registerListener(new TurnFinishListener(activeGame));
        Events.registerListener(new GameWinListener(activeGame));
    }

    private static Entity getBall(int position) {
        try {
            return new EntityBuilder()
                    .setLocation(GameLocation.BALL_SPAWN_POINTS[position].getFirst().clone())
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

        Events.unregisterAllListeners(TurnFinishEvent.class);
        Events.unregisterAllListeners(BallCollideEvent.class);
        Events.unregisterAllListeners(EntityCollisionEvent.class);
        Events.unregisterAllListeners(EntityStopMovingEvent.class);
        activeGame.getArena().shutdown();
        PoolClient.getScreenManager().finishGame(winner.getDisplayName() + " has won the game!", activeGame.getStartTime());
    }

    public static GameData getActiveGame() {
        return activeGame;
    }

    public static Entity[] getPockets(GameData gameData) {
        GameEntity gameEntity = gameData.getGameEntities();

        return new Entity[] {
                gameEntity.getTopRightCornerHole(),
                gameEntity.getTopLeftCornerHole(),
                gameEntity.getTopMiddleHole(),
                gameEntity.getBottomMiddleHole(),
                gameEntity.getTopLeftCornerHole(),
                gameEntity.getBottomLeftCornerHole()
        };
    }
}
