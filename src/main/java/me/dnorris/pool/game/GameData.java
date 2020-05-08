package me.dnorris.pool.game;

import me.dnorris.pool.arena.GameArena;
import me.dnorris.pool.game.team.Team;

import java.awt.*;

public interface GameData {

    GameArena getArena();

    Team getTurn();

    void setTurn(Team team);

    boolean isCueBallInHand();

    void setCueBallInHand(boolean placing);

    boolean haveBallsStoppedMoving();

    int getPottedBalls();

    void setPottedBalls(int pottedBalls);

    int getShotsInTurn();

    void setShotsInTurn(int shotsInTurn);

    Color getTeamColour(Team team);

    void setTeamColour(Team team, Color color);

    boolean isOnBlackBall(Team team);

}
