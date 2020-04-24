package me.dnorris.pool.arena;

import java.util.List;

public interface GameArena {

    List<Entity> getEntities();

    void addEntity(Entity object);

    void removeEntity(Entity object);

    List<GameFunction> getHandlers(long key);

    void addHandler(Class<?> keyHandler);

    void removeHandler(Class<?> keyHandler);

    void tick();

}
