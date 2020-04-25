package me.dnorris.pool.arena.entity;

import me.dnorris.pool.arena.Entity;
import me.dnorris.pool.data.hitbox.Hitbox;
import me.dnorris.pool.data.location.Location;
import me.dnorris.pool.data.vector.Vector;

import java.awt.*;

public abstract class AbstractEntity implements Entity {

    private Color colour;
    private Location location;
    private Hitbox hitbox;
    private Vector motion;

    protected AbstractEntity(Color colour, Location location, Hitbox hitbox, Vector motion) {
        this.colour = colour;
        this.location = location;
        this.hitbox = hitbox;
        this.motion = motion;
    }

    @Override
    public Color getColour() {
        return this.colour;
    }

    @Override
    public void setColour(Color colour) {
        this.colour = colour;
    }

    @Override
    public Location getLocation() {
        return this.location;
    }

    @Override
    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public Hitbox getHitbox() {
        return this.hitbox;
    }

    @Override
    public Vector getMotion() {
        return this.motion;
    }

    @Override
    public void setMotion(Vector motion) {
        this.motion = motion;
    }
}
