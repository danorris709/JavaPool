package me.dnorris.arena.event.event;

import me.dnorris.arena.Entity;
import me.dnorris.arena.event.Cancellable;
import me.dnorris.arena.event.Event;
import me.dnorris.arena.event.handler.EventListener;

/**
 *
 * An event for representing when two {@link Entity}s collide and interact
 *
 * @author https://github.com/danorris709
 */
public class EntityCollisionEvent implements Event, Cancellable {

    private static final EventListener EVENT_HANDLER = new EventListener();

    private final Entity first;
    private final Entity second;

    private boolean cancelled;

    /**
     *
     * Constructor for the event giving the two entities that have collided
     *
     * @param first The first entity colliding
     * @param second The second entity colliding
     */
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
