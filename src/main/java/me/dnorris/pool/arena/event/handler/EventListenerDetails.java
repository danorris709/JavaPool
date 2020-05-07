package me.dnorris.pool.arena.event.handler;

import me.dnorris.pool.arena.event.Event;
import me.dnorris.pool.data.Method;

public class EventListenerDetails {

    private final java.lang.reflect.Method declaredMethod;
    private final Method<Event> eventMethod;
    private final boolean ignoreCancelled;

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
