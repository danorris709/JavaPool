package me.dnorris.pool.game;

import me.dnorris.arena.Entity;
import me.dnorris.arena.GameArena;
import me.dnorris.arena.arena.jframe.SimpleArena;
import me.dnorris.arena.entity.EntityBuilder;
import me.dnorris.arena.entity.EntityType;
import me.dnorris.arena.event.Events;
import me.dnorris.arena.event.event.EntityCollisionEvent;
import me.dnorris.arena.event.event.EntityStopMovingEvent;
import me.dnorris.pool.PoolClient;
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

/**
 *
 * Static factory for starting, and ending games
 *
 * @author https://github.com/danorris709
 */
public class GameFactory {

    private static GameData activeGame;

    /**
     *
     * Start a new game
     *
     */
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

    /**
     *
     * Private method for getting the {@link Entity} objects for the balls on the board
     * using the {@link GameLocation} class
     *
     * @param position Position being got
     * @return Entity at given {@param position}
     */
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

    /**
     *
     * Finish the game with specified winner
     *
     * @param winner Winner of the game
     */
    public static void finishGame(Team winner) {
        GameData activeGame = getActiveGame();

        Events.unregisterAllListeners(TurnFinishEvent.class);
        Events.unregisterAllListeners(BallCollideEvent.class);
        Events.unregisterAllListeners(EntityCollisionEvent.class);
        Events.unregisterAllListeners(EntityStopMovingEvent.class);
        activeGame.getArena().shutdown();
        PoolClient.getScreenManager().finishGame(winner.getDisplayName() + " has won the game!", activeGame.getStartTime());
    }

    /**
     *
     * Get current running game
     *
     * @return Current game
     */
    public static GameData getActiveGame() {
        return activeGame;
    }

    /**
     *
     * Get the pocket entities in an array
     *
     * @param gameData Game for which you wish to obtain the pockets
     * @return Pockets for said game
     */
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
