package me.dnorris.pool.arena.event;

import me.dnorris.pool.arena.event.handler.EventListener;

/**
 *
 * Interface for representing an event
 *
 * @author https://github.com/danorris709
 */
public interface Event {

    /**
     *
     * Get the event listener handlers
     *
     * @return The {@link EventListener} DTO
     */
    EventListener getHandler();

}
