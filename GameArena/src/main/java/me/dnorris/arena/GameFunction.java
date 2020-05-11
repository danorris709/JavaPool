package me.dnorris.arena;

import me.dnorris.arena.key.KeyEventType;
import me.dnorris.arena.key.KeyHandler;
import me.dnorris.data.BiFunction;

import java.awt.event.InputEvent;

/**
 *
 * Immutable object used by the {@link GameArena} for {@link KeyHandler} annotations
 *
 * @author https://github.com/danorris709
 */
public class GameFunction {

    private final KeyEventType eventType;
    private final BiFunction<GameArena, InputEvent> function;

    /**
     *
     * Basic constructor setting the variables
     *
     * @param eventType The {@link KeyEventType} type
     * @param function The {@link BiFunction} executed once the key is pressed
     */
    public GameFunction(KeyEventType eventType, BiFunction<GameArena, InputEvent> function) {
        this.eventType = eventType;
        this.function = function;
    }

    public KeyEventType getEventType() {
        return this.eventType;
    }

    public BiFunction<GameArena, InputEvent> getFunction() {
        return this.function;
    }
}
