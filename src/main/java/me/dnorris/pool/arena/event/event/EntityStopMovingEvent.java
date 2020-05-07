package me.dnorris.pool.arena.event.event;

import me.dnorris.pool.arena.Entity;
import me.dnorris.pool.arena.event.Event;
import me.dnorris.pool.arena.event.handler.EventListener;

/**
 *
 * An event representing when an entity motion goes from a very small number to {@link me.dnorris.pool.data.vector.implementation.Vector2D#NONE}
 *
 * @author https://github.com/danorris709
 */
public class EntityStopMovingEvent implements Event {

    private static final EventListener EVENT_HANDLER = new EventListener();

    private final Entity entity;

    /**
     *
     * Constructor for the event giving the entity that's stopping moving
     *
     * @param entity The entity that stopped moving
     */
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
