package me.dnorris.pool.arena.entity;

import me.dnorris.pool.arena.Entity;
import me.dnorris.pool.data.hitbox.Hitbox;
import me.dnorris.pool.data.location.Location;
import me.dnorris.pool.data.vector.Vector;
import me.dnorris.pool.data.vector.implementation.Vector2D;

import java.awt.*;

public abstract class AbstractEntity implements Entity {

    private static Vector FRICTION = new Vector2D(0.05, 0.05);

    private Color colour;
    private Location location;
    private Hitbox hitbox;
    private Vector motion;

    protected boolean hollow;
    protected boolean dirty;

    protected AbstractEntity(Color colour, Location location, Hitbox hitbox, Vector motion, boolean hollow) {
        this.colour = colour;
        this.location = location;
        this.hitbox = hitbox;
        this.motion = motion;
        this.hollow = hollow;
        this.dirty = true;
    }

    protected boolean isHollow() {
        return this.hollow;
    }

    protected void setHollow(boolean hollow) {
        this.hollow = hollow;
    }

    protected boolean isDirty() {
        return this.dirty;
    }

    protected void setDirty(boolean dirty) {
        this.dirty = dirty;
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
        if(this.getHitbox().isImmovable()) {
            return Vector2D.NONE;
        }

        return this.motion;
    }

    @Override
    public void setMotion(Vector motion) {
        if(this.getHitbox().isImmovable()) {
            return;
        }

        this.motion = motion;
    }

    @Override
    public void tick() {
        if(this.hitbox == null || this.hitbox.isImmovable()) {
            return;
        }

        this.getLocation().setX((int) (this.getLocation().getX() + this.getMotion().getX()));
        this.getLocation().setY((int) (this.getLocation().getY() + this.getMotion().getY()));

        this.getMotion().setX(this.getMotion().getX() * FRICTION.getX());
        this.getMotion().setY(this.getMotion().getY() * FRICTION.getY());
        this.dirty = true;
    }
}
