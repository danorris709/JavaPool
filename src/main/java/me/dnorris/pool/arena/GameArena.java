package me.dnorris.pool.arena;

import java.awt.*;
import java.util.List;

public interface GameArena {

    Dimension getDimensions();

    void setDimensions(Dimension dimensions);

    List<Entity> getEntities();

    void addEntity(Entity object);

    void removeEntity(Entity object);

    List<GameFunction> getHandlers(long key);

    void addHandler(Class<?> keyHandler);

    void removeHandler(Class<?> keyHandler);

    void tick(Graphics2D graphics);

}
