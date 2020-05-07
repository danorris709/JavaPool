package me.dnorris.pool.game;

import me.dnorris.pool.arena.GameArena;
import me.dnorris.pool.game.team.Team;

public interface GameData {

    GameArena getArena();

    Team getTurn();

    void setTurn(Team team);

    boolean isPlacingCueBall();

    void setPlacingCueBall(boolean placing);

    boolean haveBallsStoppedMoving();

}
