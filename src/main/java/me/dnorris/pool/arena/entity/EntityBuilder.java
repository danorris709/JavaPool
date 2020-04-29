package me.dnorris.pool.arena.entity;

import me.dnorris.pool.arena.Entity;
import me.dnorris.pool.data.hitbox.Hitbox;
import me.dnorris.pool.data.location.Location;
import me.dnorris.pool.data.vector.Vector;
import me.dnorris.pool.data.vector.implementation.Vector2D;

import java.awt.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class EntityBuilder {

    protected Color colour;
    protected Location location;
    protected Hitbox hitbox;
    protected Vector motion = Vector2D.NONE;
    protected boolean hollow;
    protected boolean immovable;
    protected boolean interactable;
    protected Dimension dimension;
    protected EntityType type;

    public EntityBuilder() {}

    public EntityBuilder setColour(Color colour) {
        this.colour = colour;
        return this;
    }

    public EntityBuilder setLocation(Location location) {
        this.location = location;
        return this;
    }

    public EntityBuilder setHitbox(Hitbox hitbox) {
        this.hitbox = hitbox;
        return this;
    }

    public EntityBuilder setMotion(Vector motion) {
        this.motion = motion;
        return this;
    }

    public EntityBuilder setHollow(boolean hollow) {
        this.hollow = hollow;
        return this;
    }

    public EntityBuilder setType(EntityType type) {
        this.type = type;
        return this;
    }

    public EntityBuilder setImmovable(boolean immovable) {
        this.immovable = immovable;
        return this;
    }

    public EntityBuilder setInteractable(boolean interactable) {
        this.interactable = interactable;
        return this;
    }

    public EntityBuilder setDimension(Dimension dimension) {
        this.dimension = dimension;
        return this;
    }

    public Entity build() throws IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor<? extends Entity> constructor = this.type.getConstructor();

        return constructor.newInstance(this.location, this.colour, this.motion, this.dimension, this.hollow, this.immovable, this.interactable);
    }

    public EntityBuilder clone() {
        EntityBuilder newBuilder = new EntityBuilder();

        if (this.hitbox != null) {
            newBuilder.hitbox = this.hitbox.clone();
        }

        if (this.location != null) {
            newBuilder.location = this.location.clone();
        }

        if (this.dimension != null) {
            newBuilder.dimension = new Dimension(this.dimension.width, this.dimension.height);
        }

        newBuilder.colour = this.colour;
        newBuilder.motion = this.motion;
        newBuilder.hollow = this.hollow;
        newBuilder.immovable = this.immovable;
        newBuilder.interactable = this.interactable;
        newBuilder.type = this.type;

        return newBuilder;
    }
}
