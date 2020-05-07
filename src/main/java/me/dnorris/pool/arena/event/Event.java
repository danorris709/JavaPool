package me.dnorris.pool.arena.event;

import me.dnorris.pool.arena.event.handler.EventListener;

public interface Event {

    EventListener getHandler();

}
