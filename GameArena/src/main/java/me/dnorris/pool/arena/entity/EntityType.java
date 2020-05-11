package me.dnorris.pool.arena.entity;

import me.dnorris.pool.arena.Entity;
import me.dnorris.pool.arena.entity.shape.CircleEntity;
import me.dnorris.pool.arena.entity.shape.RectangleEntity;
import me.dnorris.pool.data.location.Location;
import me.dnorris.pool.data.vector.Vector;

import java.awt.*;
import java.lang.reflect.Constructor;

/**
 *
 * An enum representing the different types of buildable entity.
 * Used by the {@link EntityBuilder} builder for finding the correct constructor
 * in an abstract fashion without having to make a unique builder for each entity.
 *
 * @author https://github.com/danorris709
 */
public enum EntityType {

    RECTANGLE(RectangleEntity.class),
    CIRCLE(CircleEntity.class),

    HOLLOW_RECTANGLE(null),
    ARC(null),
    LINE(null),
    PERCENTAGE_BAR(null),
    TEXT(null),

    ;

    private Constructor<? extends Entity> entityConstructor = null;

    /**
     *
     * Constructor for the entity type giving an entity class
     *
     * @param entityClass The class of the entity object
     */
    EntityType(Class<? extends Entity> entityClass) {
        if(entityClass == null) {
            return;
        }

        try {
            this.entityConstructor = entityClass.getConstructor(Location.class, Color.class, Vector.class, Dimension.class, boolean.class, boolean.class, boolean.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * Get the constructor for the entity
     *
     * @return The constructor for the entity
     * @throws UnsupportedOperationException If the constructor is null then the builder cannot be used for this entity type
     */
    public Constructor<? extends Entity> getConstructor() throws UnsupportedOperationException {
        if(this.entityConstructor == null) {
            throw new UnsupportedOperationException("Use correct builder for this type");
        }

        return this.entityConstructor;
    }
}
