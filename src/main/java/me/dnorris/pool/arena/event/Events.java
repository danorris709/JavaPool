package me.dnorris.pool.arena.event;

import com.google.common.collect.Maps;
import me.dnorris.pool.arena.event.handler.EventListener;
import me.dnorris.pool.arena.event.handler.EventListenerDetails;
import me.dnorris.pool.arena.event.listener.Listener;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

public class Events {

    private static final Map<Class<? extends Event>, EventListener> EVENT_LISTENER_CACHE = Maps.newHashMap();

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
