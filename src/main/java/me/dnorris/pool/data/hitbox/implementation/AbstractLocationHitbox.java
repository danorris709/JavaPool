package me.dnorris.pool.data.hitbox.implementation;

import me.dnorris.pool.data.hitbox.Hitbox;
import me.dnorris.pool.data.location.Location;

import java.awt.*;

public abstract class AbstractLocationHitbox implements Hitbox {

    private final int priority;
    private Location center;
    private Dimension dimensions;
    private boolean immovable;
    private boolean interactable;

    public AbstractLocationHitbox(int priority, Location center, Dimension dimensions, boolean immovable, boolean interactable) {
        this.priority = priority;
        this.center = center;
        this.dimensions = dimensions;
        this.immovable = immovable;
        this.interactable = interactable;
    }

    @Override
    public int getPriority() {
        return this.priority;
    }

    @Override
    public Location getCenter() {
        return this.center;
    }

    @Override
    public Dimension getDimensions() {
        return this.dimensions;
    }

    @Override
    public boolean isImmovable() {
        return this.immovable;
    }

    @Override
    public void setImmovable(boolean immovable) {
        this.immovable = immovable;
    }

    @Override
    public boolean isInteractable() {
        return this.interactable;
    }

    @Override
    public void setInteractable(boolean interactable) {
        this.interactable = interactable;
    }

    @Override
    public boolean isColliding(Hitbox other) {
        return !this.isInteractable();
    }

    @Override
    public Hitbox clone() {
        return null;
    }
}
