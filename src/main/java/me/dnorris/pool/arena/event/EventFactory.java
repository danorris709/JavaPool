package me.dnorris.pool.arena.event;

import me.dnorris.pool.arena.Entity;
import me.dnorris.pool.arena.event.event.EntityCollisionEvent;
import me.dnorris.pool.arena.event.event.EntityStopMovingEvent;

public class EventFactory {

    public static EntityCollisionEvent callCollisionEvent(Entity first, Entity second) {
        EntityCollisionEvent collisionEvent = new EntityCollisionEvent(first, second);
        Events.callEvent(collisionEvent);
        return collisionEvent;
    }

    public static void callStopMovingEvent(Entity entity) {
        Events.callEvent(new EntityStopMovingEvent(entity));
    }

}
