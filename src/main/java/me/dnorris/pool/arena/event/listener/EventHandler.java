package me.dnorris.pool.arena.event.listener;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * Annotation for flagging functions as methods to handle events
 *
 * @author https://github.com/danorris709
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface EventHandler {

    /**
     *
     * If the event handler function should ignore events that have been cancelled
     *
     * @return if the listener ignores cancelled events
     */
    boolean ignoreCancelled() default true;

}
