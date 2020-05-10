package me.dnorris.pool.game.listener;

import me.dnorris.pool.arena.Entity;
import me.dnorris.pool.arena.event.listener.EventHandler;
import me.dnorris.pool.arena.event.listener.Listener;
import me.dnorris.pool.data.location.implementation.Location2D;
import me.dnorris.pool.game.GameData;
import me.dnorris.pool.game.GameEntity;
import me.dnorris.pool.game.event.TurnFinishEvent;
import me.dnorris.pool.game.team.Team;

import java.awt.*;
import java.util.Objects;

public class TurnFinishListener implements Listener {

    private final GameData currentGame;

    public TurnFinishListener(GameData currentGame) {
        this.currentGame = currentGame;
    }

    @EventHandler
    public void onTurnFinish(TurnFinishEvent event) {
        Team turn = event.getTurn();
        Color color = currentGame.getTeamColour(turn);
        GameData activeGame = event.getActiveGame();

        if(this.pottedCueBall(event)) {
            this.handleCueBallPotted(event);
            return;
        }

        if(event.getFirstCollision() == null) {
            this.penalizeCurrentPlayer(event);
            return;
        }

        if((color == null && !this.attemptAssignColour(activeGame, event))) {
            return;
        } else if(this.hasCommittedFoul(activeGame, event)) {
            this.penalizeCurrentPlayer(event);
            return;
        }

        this.attemptDecreaseShotsRemaining(event);
        this.attemptSwitchPlayer(event);
        this.attemptReplacePointer(event);
    }

    private boolean pottedCueBall(TurnFinishEvent event) {
        for(Entity pottedBall : event.getPottedBalls()) {
            if(Objects.equals(pottedBall, GameEntity.getCueBall())) {
                return true;
            }
        }

        return false;
    }

    private void handleCueBallPotted(TurnFinishEvent event) {
        GameEntity.getCueBall().setLocation(new Location2D(350, 350));
        event.getActiveGame().setCueBallInHand(true);
        this.penalizeCurrentPlayer(event);
    }

    private void penalizeCurrentPlayer(TurnFinishEvent event) {
        event.getActiveGame().setTurn(event.getTurn().getOpposition());
        event.getActiveGame().setShotsInTurn(2);
    }

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

    private Color getOtherColour(Color color) {
        if(Objects.equals(color, Color.YELLOW)) {
            return Color.RED;
        }

        return Color.YELLOW;
    }

    private boolean hasCommittedFoul(GameData activeGame, TurnFinishEvent event) {
        if(this.pottedOtherTeamsBall(event, activeGame.getTeamColour(event.getTurn()))) {
            return true;
        }

        return !Objects.equals(event.getFirstCollision().getColour(), activeGame.getTeamColour(event.getTurn()));
    }

    private boolean pottedOtherTeamsBall(TurnFinishEvent event, Color colour) {
        for (Entity pottedBall : event.getPottedBalls()) {
            if(!Objects.equals(pottedBall.getColour(), colour)) {
                return true;
            }
        }

        return false;
    }

    private void attemptDecreaseShotsRemaining(TurnFinishEvent event) {
        if(!event.getPottedBalls().isEmpty()) {
            return;
        }

        event.getActiveGame().setShotsInTurn(event.getActiveGame().getShotsInTurn() - 1);
    }

    private void attemptSwitchPlayer(TurnFinishEvent event) {
        if (event.getActiveGame().getShotsInTurn() > 0) {
            return;
        }

        event.getActiveGame().setTurn(event.getTurn().getOpposition());
        event.getActiveGame().setShotsInTurn(1);
    }

    private void attemptReplacePointer(TurnFinishEvent event) {
        if (event.getActiveGame().isCueBallInHand()) {
            return;
        }

        Entity pointer = GameEntity.getPointer();

        pointer.setLocation(GameEntity.getCueBall().getLocation().clone());
        event.getActiveGame().getArena().addEntity(pointer);
    }
}
