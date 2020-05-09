package me.dnorris.pool.arena.entity;

import me.dnorris.pool.arena.Entity;
import me.dnorris.pool.arena.GameArena;
import me.dnorris.pool.arena.event.EventFactory;
import me.dnorris.pool.data.hitbox.Hitbox;
import me.dnorris.pool.data.location.Location;
import me.dnorris.pool.data.vector.Vector;
import me.dnorris.pool.data.vector.implementation.Vector2D;

import java.awt.*;
import java.util.Objects;

/**
 *
 * Abstract implementation of the {@link Entity} interface to reduce the amount of
 * repeated code in the lower, more specific, implementations of the {@link Entity} interface
 *
 * @author https://github.com/danorris709
 */
public abstract class AbstractEntity implements Entity {

    // Friction constant
    private static Vector FRICTION = new Vector2D(0.995, 0.995);

    private GameArena arena;
    private Color colour;
    private Location location;
    private Hitbox hitbox;
    private Vector motion;

    protected boolean hollow;

    /**
     *
     * Basic constructor that can only be accessed from classes extending this abstract implementation
     *
     * @param colour Colour of the entity
     * @param location Center point of the entity
     * @param hitbox Entity's hitbox
     * @param motion The initial motion of the entity
     * @param hollow If the entity is graphically hollow
     */
    protected AbstractEntity(Color colour, Location location, Hitbox hitbox, Vector motion, boolean hollow) {
        this.colour = colour;
        this.location = location;
        this.hitbox = hitbox;
        this.motion = motion;
        this.hollow = hollow;
    }

    protected boolean isHollow() {
        return this.hollow;
    }

    protected void setHollow(boolean hollow) {
        this.hollow = hollow;
    }

    @Override
    public GameArena getArena() {
        return this.arena;
    }

    @Override
    public void setArena(GameArena arena) {
        this.arena = arena;
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
        this.hitbox.setCenter(location);
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
    public void collide(Entity other) {}

    @Override
    public void tick() {
        if (this.hitbox == null || this.hitbox.isImmovable() || this.getArena() == null) {
            return;
        }

        this.getArena().doPhysics(this);

        Vector motion = this.getMotion();

        this.getLocation().setX(this.getLocation().getX() + motion.getX());
        this.getLocation().setY(this.getLocation().getY() + motion.getY());
        this.getMotion().setX(this.getMotion().getX() * FRICTION.getX());
        this.getMotion().setY(this.getMotion().getY() * FRICTION.getY());

        double absX = Math.abs(this.getMotion().getX());
        double absY = Math.abs(this.getMotion().getY());

        if (absX <= 1e-3 && absY <= 1e-3 && !Objects.equals(this.getMotion(), Vector2D.NONE)) {
            this.setMotion(Vector2D.NONE);
            EventFactory.callStopMovingEvent(this);
        } else if (absX <= 1e-3) {
            this.setMotion(new Vector2D(0, this.getMotion().getY()));
        } else if (absY <= 1e-3) {
            this.setMotion(new Vector2D(this.getMotion().getX(), 0));
        }
    }
}
