package me.dnorris.pool.arena.event;

/**
 *
 * An interface to represent an event that can be cancelled
 *
 * @author https://github.com/danorris709
 */
public interface Cancellable {

    /**
     *
     * If the event has been cancelled
     *
     * @return true if cancelled - false otherwise
     */
    boolean isCancelled();

    /**
     *
     * Updated if the event has been cancelled
     *
     * @param cancelled true if cancelled - false otherwise
     */
    void setCancelled(boolean cancelled);

}
