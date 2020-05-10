package me.dnorris.pool.arena;

import java.awt.*;
import java.util.List;

/**
 *
 * Represents a gameboard with {@link Entity}s
 *
 * @author https://github.com/danorris709
 */
public interface GameArena {

    /**
     *
     * Get the {@link Dimension} of the {@link GameArena}
     *
     * @return Height and width
     */
    Dimension getDimensions();

    /**
     *
     * Set the {@link Dimension} of the {@link GameArena}
     *
     * @param dimensions the new dimensions of the {@link GameArena}
     */
    void setDimensions(Dimension dimensions);

    /**
     *
     * Get all the {@link Entity} in the {@link GameArena}
     *
     * @return The list of all entities
     */
    List<Entity> getEntities();

    /**
     *
     * Add a new entity to the {@link GameArena}
     *
     * @param object A new entity
     */
    void addEntity(Entity object);

    /**
     *
     * Remove an existing entity from the {@link GameArena}
     *
     * @param object The old entity
     */
    void removeEntity(Entity object);

    /**
     *
     * Get the {@link GameFunction} handlers for a specific key press
     *
     * @param key The identifier of the key pressed
     * @return The list of functions for that key
     */
    List<GameFunction> getHandlers(long key);

    /**
     *
     * Add a new class that contains {@link me.dnorris.pool.arena.key.KeyHandler}s
     *
     * @param keyHandler Class containing handlers
     */
    void addHandler(Class<?> keyHandler);

    /**
     *
     * Remove a class that contains {@link me.dnorris.pool.arena.key.KeyHandler}s
     *
     * @param keyHandler Class containing handlers
     */
    void removeHandler(Class<?> keyHandler);

    /**
     *
     * Painting the {@link Entity} to the {@link GameArena} and ticking the entities for movement
     * and similar things.
     *
     * @param graphics The graphics for painting the {@link GameArena}
     */
    void tick(Graphics2D graphics);

    /**
     *
     * Handling the physics between the {@link Entity} and all other entities on the
     * {@link GameArena}
     *
     * @param entity The entity being checked
     */
    void doPhysics(Entity entity);

    /**
     *
     * Closes all related threads and GUIs
     *
     */
    void shutdown();

}
