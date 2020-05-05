package me.dnorris.pool.arena.event;

public interface Cancellable {

    boolean isCancelled();

    void setCancelled(boolean cancelled);

}
