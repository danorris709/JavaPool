package me.dnorris.pool.arena;

import java.util.List;

public interface GameArena {

    List<Object> getEntities();

    void addEntity(Object object);

    void removeEntity(Object object);

    List<Object> getHandlers(long key);

    void addHandler(Class<? extends Object> keyHandler);

    void removeHandler(Class<? extends Object> keyHandler);

    void tick();

}
