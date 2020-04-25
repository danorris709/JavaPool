package me.dnorris.pool.arena;

import me.dnorris.pool.arena.key.KeyEventType;
import me.dnorris.pool.data.BiFunction;

import java.awt.event.KeyEvent;

public class GameFunction {

    private final KeyEventType eventType;
    private final BiFunction<GameArena, KeyEvent> function;

    public GameFunction(KeyEventType eventType, BiFunction<GameArena, KeyEvent> function) {
        this.eventType = eventType;
        this.function = function;
    }

    public KeyEventType getEventType() {
        return this.eventType;
    }

    public BiFunction<GameArena, KeyEvent> getFunction() {
        return this.function;
    }
}
