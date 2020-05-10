package me.dnorris.pool.game.listener;

import me.dnorris.pool.arena.Entity;
import me.dnorris.pool.arena.event.listener.EventHandler;
import me.dnorris.pool.arena.event.listener.Listener;
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
            activeGame.setCueBallInHand(true);
            activeGame.setTurn(turn.getOpposition());
            activeGame.setShotsInTurn(2);
            return;
        }

        if(event.getFirstCollision() == null) {
            activeGame.setTurn(turn.getOpposition());
            activeGame.setShotsInTurn(2);
            return;
        }

        if(color == null) {
            if(!event.getPottedBalls().isEmpty()) {
                Color pottedColour = this.getPottedColour(event);

                if(pottedColour == null) {
                    return;
                }

                activeGame.setTeamColour(turn, pottedColour);
                activeGame.setTeamColour(turn.getOpposition(), this.getOtherColour(pottedColour));
            }
        }else {
            if(this.pottedOtherTeamsBall(event, color)) {
                activeGame.setTurn(turn.getOpposition());
                activeGame.setShotsInTurn(2);
                return;
            }
        }

        if(event.getPottedBalls().isEmpty()) {
            activeGame.setShotsInTurn(activeGame.getShotsInTurn() - 1);
        }

        if(activeGame.getShotsInTurn() <= 0) {
            activeGame.setTurn(turn.getOpposition());
            activeGame.setShotsInTurn(1);
        }
    }

    private boolean pottedCueBall(TurnFinishEvent event) {
        for(Entity pottedBall : event.getPottedBalls()) {
            if(Objects.equals(pottedBall, GameEntity.getCueBall())) {
                return true;
            }
        }

        return false;
    }

    private boolean pottedOtherTeamsBall(TurnFinishEvent event, Color colour) {
        for (Entity pottedBall : event.getPottedBalls()) {
            if(!Objects.equals(pottedBall.getColour(), colour)) {
                return true;
            }
        }

        return false;
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
}
