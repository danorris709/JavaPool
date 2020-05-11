package me.dnorris.game.listener;

import me.dnorris.arena.Entity;
import me.dnorris.arena.GameArena;
import me.dnorris.arena.event.listener.EventHandler;
import me.dnorris.arena.event.listener.Listener;
import me.dnorris.data.location.implementation.Location2D;
import me.dnorris.game.GameData;
import me.dnorris.game.GameEntity;
import me.dnorris.game.event.TurnFinishEvent;
import me.dnorris.game.team.Team;

import java.awt.*;
import java.util.Objects;

/**
 *
 * A listener to enforce the basic rules of pool.
 *
 * @author https://github.com/danorris709
 */
public class TurnFinishListener implements Listener {

    private final GameData currentGame;

    /**
     *
     * Constructor taking the current game
     *
     * @param currentGame Game related to this listener
     */
    public TurnFinishListener(GameData currentGame) {
        this.currentGame = currentGame;
    }

    @EventHandler
    public void onTurnFinish(TurnFinishEvent event) {
        Team turn = event.getTurn();
        Color color = currentGame.getTeamColour(turn);
        GameData activeGame = event.getActiveGame();

        if(this.hasPottedCueBall(event)) {
            this.handleCueBallPotted(event);
            return;
        }

        if(event.getFirstCollision() == null) {
            this.penalizeCurrentPlayer(event);
            return;
        }

        if(color == null && !this.attemptAssignColour(activeGame, event)) {
            return;
        } else if(color != null && this.hasCommittedFoul(activeGame, event)) {
            this.penalizeCurrentPlayer(event);
            return;
        }

        this.attemptDecreaseShotsRemaining(event);
        this.attemptSwitchPlayer(event);
        this.attemptReplacePointer(event);
    }

    /**
     *
     * Determines if the cue ball was potted during the {@link TurnFinishEvent}
     *
     * @param event The event being checked
     * @return True if the cue ball was putted
     */
    private boolean hasPottedCueBall(TurnFinishEvent event) {
        return event.hasPottedPredicate(e -> Objects.equals(e, event.getActiveGame().getGameEntities().getCueBall()));
    }

    /**
     *
     * Handles the penalty for potting the cue ball
     *
     * @param event The event being used to handle the penalty
     */
    private void handleCueBallPotted(TurnFinishEvent event) {
        event.getActiveGame().getGameEntities().getCueBall().setLocation(new Location2D(350, 350));
        event.getActiveGame().setCueBallInHand(true);
        this.penalizeCurrentPlayer(event);
    }

    /**
     *
     * Generic penalization of a player
     *
     * @param event Event from which the player is being penalized
     */
    private void penalizeCurrentPlayer(TurnFinishEvent event) {
        event.getActiveGame().setTurn(event.getTurn().getOpposition());
        event.getActiveGame().setShotsInTurn(2);
    }

    /**
     *
     * Attempting to assign the person who took the turn a colour based on which balls they potted
     *
     * @param activeGame Current game
     * @param event Turn event
     * @return If the turn event should continue rule logic
     */
    private boolean attemptAssignColour(GameData activeGame, TurnFinishEvent event) {
        if (event.getPottedBalls().isEmpty()) {
            return true;
        }

        Color pottedColour = this.getPottedColour(event);

        if (pottedColour == null) {
            return false;
        }

        activeGame.setTeamColour(event.getTurn(), pottedColour);
        activeGame.setTeamColour(event.getTurn().getOpposition(), this.getOtherColour(pottedColour));
        return true;
    }

    /**
     *
     * Get the colour of ball that was potted
     * Will return null if more than one colour, or no colour, was potted
     *
     * @param event The event being tested
     * @return The colour of the balls potted
     */
    private Color getPottedColour(TurnFinishEvent event) {
        Color colour = null;

        for (Entity pottedBall : event.getPottedBalls()) {
            if(colour != null && !Objects.equals(pottedBall.getColour(), colour)) {
                return null;
            }

            colour = pottedBall.getColour();
        }

        return colour;
    }

    /**
     *
     * Gets the other colour between the two possible teams
     *
     * @param color The first team's colour
     * @return Second team's colour
     */
    private Color getOtherColour(Color color) {
        if(Objects.equals(color, Color.YELLOW)) {
            return Color.RED;
        }

        return Color.YELLOW;
    }

    /**
     *
     * Determine if a rule has been broken during the event
     *
     * @param activeGame Game being played
     * @param event Event being checked
     * @return True if a rule has been broken
     */
    private boolean hasCommittedFoul(GameData activeGame, TurnFinishEvent event) {
        Color turnColor = activeGame.getTeamColour(event.getTurn().getOpposition());

        if(event.hasPottedPredicate(e -> Objects.equals(turnColor, e.getColour()))) {
            return true;
        }

        if(activeGame.isOnBlackBall(event.getTurn())) {
            return false;
        }

        return !Objects.equals(event.getFirstCollision().getColour(), activeGame.getTeamColour(event.getTurn()));
    }

    /**
     *
     * Attempts to decrease the shots in the turn if no balls have been potted
     *
     * @param event Event being tested
     */
    private void attemptDecreaseShotsRemaining(TurnFinishEvent event) {
        if(!event.getPottedBalls().isEmpty()) {
            return;
        }

        event.getActiveGame().setShotsInTurn(event.getActiveGame().getShotsInTurn() - 1);
    }

    /**
     *
     * Attempts to switch the player taking the shot if there are no more
     * shots in their turn
     *
     * @param event The event being tested
     */
    private void attemptSwitchPlayer(TurnFinishEvent event) {
        if (event.getActiveGame().getShotsInTurn() > 0) {
            return;
        }

        event.getActiveGame().setTurn(event.getTurn().getOpposition());
        event.getActiveGame().setShotsInTurn(1);
    }

    /**
     *
     * Attempts to place the pointer back on the {@link GameArena}
     *
     * @param event The event being tested
     */
    private void attemptReplacePointer(TurnFinishEvent event) {
        if (event.getActiveGame().isCueBallInHand()) {
            return;
        }

        GameEntity gameEntity = event.getActiveGame().getGameEntities();
        Entity pointer = gameEntity.getPointer();

        pointer.setLocation(gameEntity.getCueBall().getLocation().clone());
        event.getActiveGame().getArena().addEntity(pointer);
    }
}
