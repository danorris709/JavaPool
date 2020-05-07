package me.dnorris.pool.arena.event.event;

import me.dnorris.pool.arena.Entity;
import me.dnorris.pool.arena.event.Cancellable;
import me.dnorris.pool.arena.event.Event;
import me.dnorris.pool.arena.event.handler.EventListener;

public class EntityCollisionEvent implements Event, Cancellable {

    private static final EventListener EVENT_HANDLER = new EventListener();

    private Entity first;
    private Entity second;
    private boolean cancelled;

    public EntityCollisionEvent(Entity first, Entity second) {
        this.first = first;
        this.second = second;
    }

    public Entity getFirst() {
        return this.first;
    }

    public Entity getSecond() {
        return this.second;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    @Override
    public EventListener getHandler() {
        return EVENT_HANDLER;
    }

    public static EventListener getHandlers() {
        return EVENT_HANDLER;
    }
}
