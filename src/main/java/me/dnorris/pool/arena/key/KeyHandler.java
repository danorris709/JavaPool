package me.dnorris.pool.arena.key;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = { ElementType.METHOD })
public @interface KeyHandler {

    long[] keyCode() default {};

    KeyEventType getType() default KeyEventType.KEY_PRESSED;

}
