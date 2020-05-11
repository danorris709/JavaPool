package me.dnorris.pool.game.team;

/**
 *
 * An enum for representing which player's turn it is in local games.
 *
 * @author https://github.com/danorris709
 */
public enum Team {

    PLAYER_ONE("PLAYER 1's TURN", "Player One"),
    PLAYER_TWO("PLAYER 2's TURN", "Player Two"),

    ;

    private final String displayText;
    private final String displayName;

    /**
     *
     * Constructor taking the display text and display name
     *
     * @param displayText The display text for above the table
     * @param displayName The display name for finishing the game
     */
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

    /**
     *
     * Gets the opposite team
     *
     * @return The other team
     */
    public Team getOpposition() {
        if(this.ordinal() == 0) {
            return PLAYER_TWO;
        }

        return PLAYER_ONE;
    }

}
