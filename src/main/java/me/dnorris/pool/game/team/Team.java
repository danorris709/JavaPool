package me.dnorris.pool.game.team;

public enum Team {

    PLAYER_ONE("PLAYER 1's TURN"),
    PLAYER_TWO("PLAYER 2's TURN"),

    ;

    private final String displayText;

    Team(String displayText) {
        this.displayText = displayText;
    }

    public String getDisplayText() {
        return this.displayText;
    }

    public Team getOpposition() {
        if(this.ordinal() == 0) {
            return PLAYER_TWO;
        }

        return PLAYER_ONE;
    }

}
