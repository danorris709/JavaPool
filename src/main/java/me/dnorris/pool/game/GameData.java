package me.dnorris.pool.game;

import me.dnorris.pool.game.team.Team;

public interface GameData {

    Team getTurn();

    void setTurn(Team team);

    boolean isPlacingCueBall();

    void setPlacingCueBall(boolean placing);

}
