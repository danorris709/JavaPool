package me.dnorris.pool.arena.event;

import me.dnorris.pool.arena.event.listener.Listener;

import java.util.List;

public interface EventHandler {

    List<Listener> getListeners();

}
