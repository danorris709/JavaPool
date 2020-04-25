package me.dnorris.pool.data.hitbox.implementation;

import me.dnorris.pool.arena.Entity;
import me.dnorris.pool.data.hitbox.Hitbox;
import me.dnorris.pool.data.location.Location;

import java.awt.*;

public abstract class AbstractLocationHitbox implements Hitbox {

    private Location center;
    private Dimension dimensions;

    public AbstractLocationHitbox(Location center, Dimension dimensions) {
        this.center = center;
        this.dimensions = dimensions;
    }

    @Override
    public Location getCenter() {
        return this.center;
    }

    @Override
    public Dimension getDimensions() {
        return this.dimensions;
    }
}
