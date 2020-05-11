package me.dnorris.pool.arena.key;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * Annotation for functions that will handle the key press of the specificed key code
 *
 * @author https://github.com/danorris709
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = { ElementType.METHOD })
public @interface KeyHandler {

    /**
     *
     * The key codes of the button being pressed
     *
     * @return The key codes to check for being pressed
     */
    long[] keyCode() default {};

    /**
     *
     * The type of key press
     *
     * @return The type fo key press
     */
    KeyEventType getType() default KeyEventType.KEY_PRESSED;

}
