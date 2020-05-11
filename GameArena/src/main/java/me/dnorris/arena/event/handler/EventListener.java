package me.dnorris.arena.event.handler;

import com.google.common.collect.Lists;
import me.dnorris.arena.event.listener.EventHandler;
import me.dnorris.arena.event.listener.Listener;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;

/**
 *
 * Event Listener for storing what event has what listeners
 *
 * @author https://github.com/danorris709
 */
public class EventListener {

    private final List<EventListenerDetails> listeners = Lists.newArrayList();

    /**
     *
     * @return Registered {@link Listener}s
     */
    public List<EventListenerDetails> getListeners() {
        return this.listeners;
    }

    /**
     *
     * Added a listener for the event
     *
     * @param listener The listener object
     * @param eventHandler The event handler annotation
     * @param method the method annotated
     */
    public void addListener(Listener listener, EventHandler eventHandler, Method method) {
        method.setAccessible(true);

        this.listeners.add(new EventListenerDetails(method, event -> {
            try {
                method.invoke(listener, event);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }, eventHandler.ignoreCancelled()));
    }

    /**
     *
     * Remove all listener functions in the specified object
     *
     * @param listener The class containing listener functions
     */
    public void removeListener(Listener listener) {
        for (Method declaredMethod : listener.getClass().getDeclaredMethods()) {
            EventHandler eventHandler = declaredMethod.getAnnotation(EventHandler.class);

            if(eventHandler == null) {
                continue;
            }

            this.listeners.removeIf(details -> Objects.equals(details.getDeclaredMethod(), declaredMethod));
        }
    }
}
