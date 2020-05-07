package me.dnorris.pool.arena;

import me.dnorris.pool.arena.key.KeyEventType;
import me.dnorris.pool.data.BiFunction;

import java.awt.event.KeyEvent;

/**
 *
 * Immutable object used by the {@link GameArena} for {@link me.dnorris.pool.arena.key.KeyHandler} annotations
 *
 * @author https://github.com/danorris709
 */
public class GameFunction {

    private final KeyEventType eventType;
    private final BiFunction<GameArena, KeyEvent> function;

    /**
     *
     * Basic constructor setting the variables
     *
     * @param eventType The {@link KeyEventType} type
     * @param function The {@link BiFunction} executed once the key is pressed
     */
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
