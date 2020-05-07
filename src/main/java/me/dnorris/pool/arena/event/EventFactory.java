package me.dnorris.pool.arena.event;

import me.dnorris.pool.arena.Entity;
import me.dnorris.pool.arena.event.event.EntityCollisionEvent;
import me.dnorris.pool.arena.event.event.EntityStopMovingEvent;

/**
 *
 * The static factory for calling events without polluting other logic further
 *
 * @author https://github.com/danorris709
 */
public class EventFactory {

    /**
     *
     * Calling the {@link EntityCollisionEvent}
     *
     * @param first The first {@link Entity}
     * @param second The second {@link Entity}
     * @return The event created & called
     */
    public static EntityCollisionEvent callCollisionEvent(Entity first, Entity second) {
        EntityCollisionEvent collisionEvent = new EntityCollisionEvent(first, second);
        Events.callEvent(collisionEvent);
        return collisionEvent;
    }

    /**
     *
     * Calling the {@link EntityStopMovingEvent}
     *
     * @param entity The {@link Entity} that stopped moving
     */
    public static void callStopMovingEvent(Entity entity) {
        Events.callEvent(new EntityStopMovingEvent(entity));
    }

}
