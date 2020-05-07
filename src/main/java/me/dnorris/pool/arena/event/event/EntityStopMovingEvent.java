package me.dnorris.pool.arena.event.event;

import me.dnorris.pool.arena.Entity;
import me.dnorris.pool.arena.event.Event;
import me.dnorris.pool.arena.event.handler.EventListener;

public class EntityStopMovingEvent implements Event {

    private static final EventListener EVENT_HANDLER = new EventListener();

    private Entity entity;

    public EntityStopMovingEvent(Entity entity) {
        this.entity = entity;
    }

    public Entity getEntity() {
        return this.entity;
    }

    @Override
    public EventListener getHandler() {
        return EVENT_HANDLER;
    }

    public static EventListener getHandlers() {
        return EVENT_HANDLER;
    }
}
