package me.dnorris.pool.data.hitbox.implementation;

import me.dnorris.pool.data.hitbox.Hitbox;
import me.dnorris.pool.data.location.Location;

import java.awt.*;

public abstract class AbstractLocationHitbox implements Hitbox {

    private Location center;
    private Dimension dimensions;
    private boolean immovable;

    public AbstractLocationHitbox(Location center, Dimension dimensions, boolean immovable) {
        this.center = center;
        this.dimensions = dimensions;
        this.immovable = immovable;
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
}
