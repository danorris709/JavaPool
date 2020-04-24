package me.dnorris.pool.arena;

import me.dnorris.pool.data.TriFunction;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.List;

public interface GameArena {

    List<Entity> getEntities();

    void addEntity(Entity object);

    void removeEntity(Entity object);

    List<TriFunction<GameArena, JFrame, KeyEvent>> getHandlers(long key);

    void addHandler(Class keyHandler);

    void removeHandler(Class keyHandler);

    void tick();

}
