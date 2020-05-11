package me.dnorris.game;

import me.dnorris.arena.GameArena;
import me.dnorris.game.team.Team;

import java.awt.*;
import java.time.Instant;

/**
 *
 * Interface for all information stored about the Pool game
 *
 * @author https://github.com/danorris709
 */
public interface GameData {

    /**
     *
     * Get the {@link GameArena} being used by the pool game
     *
     * @return the arena being used
     */
    GameArena getArena();

    /**
     *
     * Get the game entities for this instance of the game
     *
     * @return Game entities class
     */
    GameEntity getGameEntities();

    /**
     *
     * Get the {@link Instant} at which the game was started
     *
     * @return Time started
     */
    Instant getStartTime();

    /**
     *
     * Get the current {@link Team}'s turn
     *
     * @return The current turn
     */
    Team getTurn();

    /**
     *
     * Set and update the current turn
     *
     * @param team New turn
     */
    void setTurn(Team team);

    /**
     *
     * Flag for if the cue ball is being placed above the head string
     *
     * @return If the cue ball is in hand
     */
    boolean isCueBallInHand();

    /**
     *
     * Updates the flag and the cue ball for if it is now in ahnd
     *
     * @param placing Is the cue ball in hand
     */
    void setCueBallInHand(boolean placing);

    /**
     *
     * Determines if all of the balls on tha table have stopped moving
     *
     * @return true if all are motionless
     */
    boolean haveBallsStoppedMoving();

    /**
     *
     * Get the number of potted balls from the game
     *
     * @return The number of balls potted so far
     */
    int getPottedBalls();

    /**
     *
     * Update the number of balls potted so far
     *
     * @param pottedBalls Sets the number of potted balls
     */
    void setPottedBalls(int pottedBalls);

    /**
     *
     * Get shots in the turn for the current {@link Team}
     *
     * @return How many shots remain
     */
    int getShotsInTurn();

    /**
     *
     * Sets and updates the current shots in the turn
     *
     * @param shotsInTurn New amount of shots in the turn
     */
    void setShotsInTurn(int shotsInTurn);

    /**
     *
     * Gets the colour of ball the team is allowed to pot
     *
     * @param team Team being checked
     * @return Team's ball colour - null if yet to be decided
     */
    Color getTeamColour(Team team);

    /**
     *
     * Updates the team's ball colour
     *
     * @param team The team being updated
     * @param color The team's new ball colour
     */
    void setTeamColour(Team team, Color color);

    /**
     *
     * Determines if a team can pot the black ball based on how many of their colour ball
     * remain on the table.
     *
     * If their colour hasn't been decided it will return false.
     *
     * @param team Team being checked
     * @return If the team can pot the black ball
     */
    boolean isOnBlackBall(Team team);

}
