package me.dnorris.pool.game.data;

import com.google.common.collect.Maps;
import me.dnorris.pool.arena.Entity;
import me.dnorris.pool.arena.GameArena;
import me.dnorris.pool.arena.entity.shape.CircleEntity;
import me.dnorris.pool.data.vector.implementation.Vector2D;
import me.dnorris.pool.game.GameData;
import me.dnorris.pool.game.GameEntity;
import me.dnorris.pool.game.team.Team;

import java.awt.*;
import java.util.Map;
import java.util.Objects;

public class BasicGameData implements GameData {

    private final GameArena arena;
    private final Map<Integer, Color> teamColour = Maps.newHashMap();

    private Team turn = Team.PLAYER_ONE;
    private boolean cueInHand = true;
    private int pottedBalls = 0;
    private int shotsInTurn = 1;

    public BasicGameData(GameArena arena) {
        this.arena = arena;

        this.setTurn(Team.PLAYER_ONE);
    }

    @Override
    public GameArena getArena() {
        return this.arena;
    }

    @Override
    public Team getTurn() {
        return this.turn;
    }

    @Override
    public void setTurn(Team turn) {
        this.turn = turn;

        GameEntity.getTurnIdentifier().setText(this.turn.getDisplayText() + ": " + this.getShotsInTurn());
    }

    @Override
    public boolean isCueBallInHand() {
        return this.cueInHand;
    }

    @Override
    public void setCueBallInHand(boolean cueInHand) {
        this.cueInHand = cueInHand;
    }

    @Override
    public boolean haveBallsStoppedMoving() {
        for (Entity entity : this.getArena().getEntities()) {
            if(entity instanceof CircleEntity && !Objects.equals(entity.getMotion(), Vector2D.NONE)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int getPottedBalls() {
        return this.pottedBalls;
    }

    @Override
    public void setPottedBalls(int pottedBalls) {
        this.pottedBalls = pottedBalls;
    }

    @Override
    public int getShotsInTurn() {
        return this.shotsInTurn;
    }

    @Override
    public void setShotsInTurn(int shotsInTurn) {
        this.shotsInTurn = shotsInTurn;

        GameEntity.getTurnIdentifier().setText(this.turn.getDisplayText() + ": " + this.getShotsInTurn());
    }

    @Override
    public Color getTeamColour(Team team) {
        return this.teamColour.get(team.ordinal());
    }

    @Override
    public void setTeamColour(Team team, Color colour) {
        this.teamColour.put(team.ordinal(), colour);
    }

    @Override
    public boolean isOnBlackBall(Team team) {
        return false; // TODO: 08/05/2020
    }
}
