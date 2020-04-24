package me.dnorris.pool.arena;

import me.dnorris.pool.arena.key.KeyEventType;
import me.dnorris.pool.data.TriFunction;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class GameFunction {

    private final KeyEventType eventType;
    private final TriFunction<GameArena, JFrame, KeyEvent> function;

    public GameFunction(KeyEventType eventType, TriFunction<GameArena, JFrame, KeyEvent> function) {
        this.eventType = eventType;
        this.function = function;
    }

    public KeyEventType getEventType() {
        return this.eventType;
    }

    public TriFunction<GameArena, JFrame, KeyEvent> getFunction() {
        return this.function;
    }
}
