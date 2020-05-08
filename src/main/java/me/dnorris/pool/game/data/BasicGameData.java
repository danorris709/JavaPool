package me.dnorris.pool.game.data;

import me.dnorris.pool.arena.Entity;
import me.dnorris.pool.arena.GameArena;
import me.dnorris.pool.arena.entity.shape.CircleEntity;
import me.dnorris.pool.data.vector.implementation.Vector2D;
import me.dnorris.pool.game.GameData;
import me.dnorris.pool.game.GameEntity;
import me.dnorris.pool.game.team.Team;

import java.util.Objects;

public class BasicGameData implements GameData {

    private final GameArena arena;

    private Team turn = Team.PLAYER_ONE;
    private boolean cueInHand = true;
    private int pottedBalls = 0;
    private int shotsInTurn = 1;

    public BasicGameData(GameArena arena) {
        this.arena = arena;
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
        this.setShotsInTurn(1);

        GameEntity.getTurnIdentifier().setText(this.turn.getDisplayText());
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
    }
}
