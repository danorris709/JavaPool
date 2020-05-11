package me.dnorris.pool.arena.event;

import com.google.common.collect.Maps;
import me.dnorris.pool.arena.event.handler.EventListener;
import me.dnorris.pool.arena.event.handler.EventListenerDetails;
import me.dnorris.pool.arena.event.listener.Listener;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 *
 * Static factory for handling the events and listeners
 *
 * @author https://github.com/danorris709
 */
public class Events {

    /**
     * Cache for classes to reduce amount of reflection required
     */
    private static final Map<Class<? extends Event>, EventListener> EVENT_LISTENER_CACHE = Maps.newHashMap();

    /**
     *
     * Handles calling an event and calling all the functions of the registered {@link Listener}
     *
     * @param event The event being called
     */
    public static void callEvent(Event event) {
        for (EventListenerDetails listener : event.getHandler().getListeners()) {
            if(event instanceof Cancellable) {
                if(!listener.isIgnoreCancelled() && ((Cancellable) event).isCancelled()) {
                    continue;
                }
            }

            listener.getEventMethod().run(event);
        }
    }

    /**
     *
     * Registering the listener class and functions to the events
     *
     * @param listener Listener class to check for event functions
     */
    @SuppressWarnings("unchecked")
    public static void registerListener(Listener listener) {
        for (Method declaredMethod : listener.getClass().getDeclaredMethods()) {
            me.dnorris.pool.arena.event.listener.EventHandler eventHandler = declaredMethod.getAnnotation(me.dnorris.pool.arena.event.listener.EventHandler.class);

            if(eventHandler == null) {
                continue;
            }

            Class<? extends Event> event = (Class<? extends Event>) declaredMethod.getParameterTypes()[0];
            EventListener eventListener = getHandlers(event);

            eventListener.addListener(listener, eventHandler, declaredMethod);
        }
    }

    /**
     *
     * Removes all {@link Listener}s for the specific {@link Event}
     *
     * @param event The event to be cleared
     */
    public static void unregisterAllListeners(Class<? extends Event> event) {
        getHandlers(event).getListeners().clear();
    }

    /**
     *
     * Function for handling the {@link Events#EVENT_LISTENER_CACHE} for optimizing reflection
     *
     * @param clazz The class being checked
     * @return The event listener object
     */
    private static EventListener getHandlers(Class<? extends Event> clazz) {
        return EVENT_LISTENER_CACHE.computeIfAbsent(clazz, ___ -> {
            try {
                Method declaredMethod = clazz.getDeclaredMethod("getHandlers");

                declaredMethod.setAccessible(true);

                return (EventListener) declaredMethod.invoke(null);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }

            return null;
        });
    }
}
