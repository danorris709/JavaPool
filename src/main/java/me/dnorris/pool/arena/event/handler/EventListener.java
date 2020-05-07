package me.dnorris.pool.arena.event.handler;

import com.google.common.collect.Lists;
import me.dnorris.pool.arena.event.listener.EventHandler;
import me.dnorris.pool.arena.event.listener.Listener;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;

public class EventListener {

    private final List<EventListenerDetails> listeners = Lists.newArrayList();

    public List<EventListenerDetails> getListeners() {
        return this.listeners;
    }

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

    public void removeListener(Listener listener) {
        for (Method declaredMethod : listener.getClass().getDeclaredMethods()) {
            me.dnorris.pool.arena.event.listener.EventHandler eventHandler = declaredMethod.getAnnotation(me.dnorris.pool.arena.event.listener.EventHandler.class);

            if(eventHandler == null) {
                continue;
            }

            this.listeners.removeIf(details -> Objects.equals(details.getDeclaredMethod(), declaredMethod));
        }
    }
}
