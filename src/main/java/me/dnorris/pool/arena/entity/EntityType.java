package me.dnorris.pool.arena.entity;

import me.dnorris.pool.arena.Entity;
import me.dnorris.pool.arena.entity.compound.shape.HollowRectangle;
import me.dnorris.pool.arena.entity.shape.RectangleEntity;
import me.dnorris.pool.data.location.Location;
import me.dnorris.pool.data.vector.Vector;

import java.awt.*;
import java.lang.reflect.Constructor;

public enum EntityType {

    RECTANGLE(RectangleEntity.class),
    HOLLOW_RECTANGLE(HollowRectangle.class),

    ;

    private Constructor<? extends Entity> entityConstructor;

    EntityType(Class<? extends Entity> entityClass) {
        try {
            this.entityConstructor = entityClass.getConstructor(Location.class, Color.class, Vector.class, Dimension.class, Boolean.class, Boolean.class, Boolean.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public Constructor<? extends Entity> getConstructor() {
        return this.entityConstructor;
    }
}
