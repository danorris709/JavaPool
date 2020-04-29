package me.dnorris.pool.game.handler;

import me.dnorris.pool.arena.GameArena;
import me.dnorris.pool.arena.key.KeyEventType;
import me.dnorris.pool.arena.key.KeyHandler;
import me.dnorris.pool.game.GameEntity;

import java.awt.event.KeyEvent;

public class CuePowerHandler {

    @KeyHandler(keyCode = KeyEvent.VK_UP, getType = KeyEventType.KEY_PRESSED)
    public void onKeyPress(GameArena arena, KeyEvent event) {
        GameEntity.getFullPowerBar().setLocation(GameEntity.getFullPowerBar().getLocation().subtract(0, 10, 0));
        GameEntity.getFullPowerBar().getHitbox().getDimensions().setSize(
                GameEntity.getFullPowerBar().getHitbox().getDimensions().getWidth(),
                GameEntity.getFullPowerBar().getHitbox().getDimensions().getHeight() + 10
        );
        GameEntity.getFullPowerBar().setDirty(true);

        GameEntity.getEmptyPowerBar().getHitbox().getDimensions().setSize(
                GameEntity.getFullPowerBar().getHitbox().getDimensions().getWidth(),
                GameEntity.getFullPowerBar().getHitbox().getDimensions().getHeight() - 10
        );
        GameEntity.getEmptyPowerBar().setDirty(true);
    }

}
