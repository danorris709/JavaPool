package me.dnorris.pool.game.handler;

import me.dnorris.pool.arena.Entity;
import me.dnorris.pool.arena.GameArena;
import me.dnorris.pool.arena.key.KeyEventType;
import me.dnorris.pool.arena.key.KeyHandler;
import me.dnorris.pool.game.GameEntity;

import java.awt.*;
import java.awt.event.KeyEvent;

public class CuePowerHandler {

    @KeyHandler(keyCode = KeyEvent.VK_UP, getType = KeyEventType.KEY_PRESSED)
    public void onUpArrowPressed(GameArena arena, KeyEvent event) {
        this.updateFullPowerBarDimensions();
        this.updateEmptyPowerBarDimensions();
    }

    private void updateFullPowerBarDimensions() {
        Entity fullPowerBar = GameEntity.getFullPowerBar();
        Dimension fullDimensions = fullPowerBar.getHitbox().getDimensions();

        fullPowerBar.setLocation(fullPowerBar.getLocation().subtract(0, 10, 0));
        fullDimensions.setSize(
                fullDimensions.getWidth(),
                fullDimensions.getHeight() + 10
        );
        fullPowerBar.setDirty(true);
    }

    private void updateEmptyPowerBarDimensions() {
        Entity emptyPowerBar = GameEntity.getEmptyPowerBar();
        Dimension emptyDimensions = emptyPowerBar.getHitbox().getDimensions();

        emptyPowerBar.getHitbox().getDimensions().setSize(
                emptyDimensions.getWidth(),
                emptyDimensions.getHeight() - 10
        );
        emptyPowerBar.setDirty(true);
    }
}
