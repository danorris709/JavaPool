package me.dnorris.pool.arena.entity;

import me.dnorris.pool.arena.Entity;
import me.dnorris.pool.data.hitbox.Hitbox;
import me.dnorris.pool.data.location.Location;
import me.dnorris.pool.data.vector.Vector;

import javax.swing.*;

public abstract class AbstractEntity implements Entity {

    private Location location;
    private Hitbox hitbox;
    private Vector motion;
    private JComponent component;

    protected AbstractEntity(Location location, Hitbox hitbox, Vector motion, JComponent component) {
        this.location = location;
        this.hitbox = hitbox;
        this.motion = motion;
        this.component = component;
    }

    @Override
    public Location getLocation() {
        return this.location;
    }

    @Override
    public void setLocation(Location location) {
        this.location = location;
        this.getComponent().setLocation(location.asPoint());
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

    @Override
    public JComponent getComponent() {
        return this.component;
    }
}
