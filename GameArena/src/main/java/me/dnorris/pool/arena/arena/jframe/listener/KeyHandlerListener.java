package me.dnorris.pool.arena.arena.jframe.listener;

import me.dnorris.pool.arena.GameArena;
import me.dnorris.pool.arena.GameFunction;
import me.dnorris.pool.arena.arena.jframe.SimpleArena;
import me.dnorris.pool.arena.key.KeyEventType;
import me.dnorris.pool.arena.key.KeyHandler;

import java.awt.event.*;

/**
 *
 * Listener used by the {@link GameArena} for calling the
 * {@link KeyHandler} implementations when a key is pressed
 *
 * @author https://github.com/danorris709
 */
public class KeyHandlerListener implements KeyListener, MouseListener, MouseMotionListener {

    private final SimpleArena gameArena;

    /**
     *
     * Basic constructor for a game arena
     *
     * @param gameArena The game arena checking for key presses
     */
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

    @Override
    public void mouseClicked(MouseEvent event) {
        this.attemptRun(event, KeyEventType.MOUSE_CLICKED);
    }

    @Override
    public void mousePressed(MouseEvent event) {
        this.attemptRun(event, KeyEventType.MOUSE_PRESSED);
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        this.attemptRun(event, KeyEventType.MOUSE_RELEASED);
    }

    @Override
    public void mouseEntered(MouseEvent event) {
        this.attemptRun(event, KeyEventType.MOUSE_ENTERED);
    }

    @Override
    public void mouseExited(MouseEvent event) {
        this.attemptRun(event, KeyEventType.MOUSE_EXITED);
    }

    @Override
    public void mouseMoved(MouseEvent event) {
        this.attemptRun(event, KeyEventType.MOUSE_MOVED);
    }

    @Override
    public void mouseDragged(MouseEvent event) {
        this.attemptRun(event, KeyEventType.MOUSE_DRAGGED);
    }

    private void attemptRun(InputEvent event, KeyEventType type) {
        int keyCode = this.getKeyCode(event);

        for (GameFunction handler : this.gameArena.getHandlers(keyCode)) {
            if(handler.getEventType() != type) {
                continue;
            }

            handler.getFunction().run(this.gameArena, event);
        }
    }

    private int getKeyCode(InputEvent event) {
        if(event instanceof MouseEvent) {
            return ((MouseEvent) event).getButton();
        }else {
            return ((KeyEvent) event).getKeyCode();
        }
    }
}
