package me.dnorris.pool.arena.entity;

import me.dnorris.pool.arena.Entity;
import me.dnorris.pool.arena.entity.shape.CircleEntity;
import me.dnorris.pool.arena.entity.shape.RectangleEntity;
import me.dnorris.pool.data.location.Location;
import me.dnorris.pool.data.vector.Vector;

import java.awt.*;
import java.lang.reflect.Constructor;

public enum EntityType {

    RECTANGLE(RectangleEntity.class),
    CIRCLE(CircleEntity.class),

    HOLLOW_RECTANGLE(null),
    ARC(null),

    ;

    private Constructor<? extends Entity> entityConstructor = null;

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

    public Constructor<? extends Entity> getConstructor() throws UnsupportedOperationException {
        if(this.entityConstructor == null) {
            throw new UnsupportedOperationException("Use correct builder for this type");
        }

        return this.entityConstructor;
    }
}
