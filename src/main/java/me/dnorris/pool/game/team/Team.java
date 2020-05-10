package me.dnorris.pool.game.team;

public enum Team {

    PLAYER_ONE("PLAYER 1's TURN", "Player One"),
    PLAYER_TWO("PLAYER 2's TURN", "Player Two"),

    ;

    private final String displayText;
    private final String displayName;

    Team(String displayText, String displayName) {
        this.displayText = displayText;
        this.displayName = displayName;
    }

    public String getDisplayText() {
        return this.displayText;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public Team getOpposition() {
        if(this.ordinal() == 0) {
            return PLAYER_TWO;
        }

        return PLAYER_ONE;
    }

}
