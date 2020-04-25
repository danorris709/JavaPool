package me.dnorris.pool.arena.arena.jframe.listener;

import me.dnorris.pool.arena.GameFunction;
import me.dnorris.pool.arena.arena.jframe.SimpleArena;
import me.dnorris.pool.arena.key.KeyEventType;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandlerListener implements KeyListener {

    private final SimpleArena gameArena;

    public KeyHandlerListener(SimpleArena gameArena) {
        this.gameArena = gameArena;
    }

    @Override
    public void keyTyped(KeyEvent event) {
        this.attemptRun(event, KeyEventType.KEY_TYPED);
    }

    @Override
    public void keyPressed(KeyEvent event) {
        this.attemptRun(event, KeyEventType.KEY_PRESSED);
    }

    @Override
    public void keyReleased(KeyEvent event) {
        this.attemptRun(event, KeyEventType.KEY_RELEASED);
    }

    private void attemptRun(KeyEvent event, KeyEventType type) {
        for (GameFunction handler : this.gameArena.getHandlers(event.getKeyCode())) {
            if(handler.getEventType() != type) {
                continue;
            }

            handler.getFunction().run(this.gameArena, event);
        }
    }
}
