package me.dnorris.pool.game;

import me.dnorris.data.Pair;
import me.dnorris.data.location.Location;
import me.dnorris.data.location.implementation.Location2D;

import java.awt.*;

/**
 *
 * Static factory for handling important {@link Location}s to the Pool game
 *
 * @author https://github.com/danorris709
 */
public class GameLocation {

    private static final Pair<Integer, Integer> BLACK_BALL_POSITION = new Pair<>(850, 100 + 250);

    @SuppressWarnings("unchecked")
    public static final Pair<Location, Color>[] BALL_SPAWN_POINTS = new Pair[] {
            new Pair<>(new Location2D(BLACK_BALL_POSITION.getFirst() - 40, BLACK_BALL_POSITION.getSecond()), Color.RED),

            new Pair<>(new Location2D(BLACK_BALL_POSITION.getFirst() - 20, BLACK_BALL_POSITION.getSecond() - 10), Color.RED),
            new Pair<>(new Location2D(BLACK_BALL_POSITION.getFirst() - 20, BLACK_BALL_POSITION.getSecond() + 10), Color.YELLOW),

            new Pair<>(new Location2D(BLACK_BALL_POSITION.getFirst(), BLACK_BALL_POSITION.getSecond() - 20), Color.YELLOW),
            new Pair<>(new Location2D(BLACK_BALL_POSITION.getFirst(), BLACK_BALL_POSITION.getSecond() + 20), Color.RED),

            new Pair<>(new Location2D(BLACK_BALL_POSITION.getFirst() + 20, BLACK_BALL_POSITION.getSecond() - 30), Color.RED),
            new Pair<>(new Location2D(BLACK_BALL_POSITION.getFirst() + 20, BLACK_BALL_POSITION.getSecond() - 10), Color.YELLOW),
            new Pair<>(new Location2D(BLACK_BALL_POSITION.getFirst() + 20, BLACK_BALL_POSITION.getSecond() + 10), Color.RED),
            new Pair<>(new Location2D(BLACK_BALL_POSITION.getFirst() + 20, BLACK_BALL_POSITION.getSecond() + 30), Color.YELLOW),

            new Pair<>(new Location2D(BLACK_BALL_POSITION.getFirst() + 40, BLACK_BALL_POSITION.getSecond() - 40), Color.YELLOW),
            new Pair<>(new Location2D(BLACK_BALL_POSITION.getFirst() + 40, BLACK_BALL_POSITION.getSecond() - 20), Color.RED),
            new Pair<>(new Location2D(BLACK_BALL_POSITION.getFirst() + 40, BLACK_BALL_POSITION.getSecond()), Color.YELLOW),
            new Pair<>(new Location2D(BLACK_BALL_POSITION.getFirst() + 40, BLACK_BALL_POSITION.getSecond() + 20), Color.YELLOW),
            new Pair<>(new Location2D(BLACK_BALL_POSITION.getFirst() + 40, BLACK_BALL_POSITION.getSecond() + 40), Color.RED)
    };

}
