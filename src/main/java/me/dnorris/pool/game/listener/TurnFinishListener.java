package me.dnorris.pool.game.listener;

import me.dnorris.pool.arena.event.listener.EventHandler;
import me.dnorris.pool.arena.event.listener.Listener;
import me.dnorris.pool.game.GameData;
import me.dnorris.pool.game.event.TurnFinishEvent;

public class TurnFinishListener implements Listener {

    private final GameData currentGame;

    public TurnFinishListener(GameData currentGame) {
        this.currentGame = currentGame;
    }

    @EventHandler
    public void onTurnFinish(TurnFinishEvent event) {
        // TODO: 08/05/2020 rule logic
    }
}
