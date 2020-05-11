package me.dnorris.pool.arena.event.handler;

import me.dnorris.pool.arena.event.Event;
import me.dnorris.pool.data.Method;

/**
 *
 * A DTO for details about an event listener
 *
 * @author https://github.com/danorris709
 */
public class EventListenerDetails {

    private final java.lang.reflect.Method declaredMethod;
    private final Method<Event> eventMethod;
    private final boolean ignoreCancelled;

    /**
     *
     * Constructor for the DTO
     *
     * @param declaredMethod The method cached to easily remove in the future
     * @param eventMethod The function called when the event is called
     * @param ignoreCancelled If the event should be ignored if cancelled is true
     */
    public EventListenerDetails(java.lang.reflect.Method declaredMethod, Method<Event> eventMethod, boolean ignoreCancelled) {
        this.declaredMethod = declaredMethod;
        this.eventMethod = eventMethod;
        this.ignoreCancelled = ignoreCancelled;
    }

    public java.lang.reflect.Method getDeclaredMethod() {
        return this.declaredMethod;
    }

    public Method<Event> getEventMethod() {
        return this.eventMethod;
    }

    public boolean isIgnoreCancelled() {
        return this.ignoreCancelled;
    }
}
