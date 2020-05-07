package me.dnorris.pool.data.hitbox.implementation;

import me.dnorris.pool.data.hitbox.Hitbox;
import me.dnorris.pool.data.location.Location;

import java.awt.*;

/**
 *
 * Abstract implementation of the {@link Hitbox} interface that uses a {@link Location}
 * as the center point.
 * Abstract class used to reduce repeated code in more specific implementations
 *
 * @author https://github.com/danorris709
 */
public abstract class AbstractLocationHitbox implements Hitbox {

    private final int priority;

    private Location center;
    private Dimension dimensions;
    private boolean immovable;
    private boolean interactable;

    /**
     *
     * Default constructor setting all the variables for a {@link Hitbox}
     *
     * @param priority Priority of the {@link Hitbox}
     * @param center Center {@link Location} of the {@link Hitbox}
     * @param dimensions {@link Dimension} of the {@link Hitbox}
     * @param immovable If the {@link Hitbox} can be moved
     * @param interactable If the {@link Hitbox} can be hit
     */
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
    public void setCenter(Location center) {
        this.center = center;
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
        if(!(this.isInteractable() && other.isInteractable())) {
            return false;
        }

        if(other.getPriority() > this.getPriority()) {
            return other.isColliding(this);
        }

        double distanceY = Math.abs(this.getCenter().getY() - other.getCenter().getY());

        if(distanceY > this.getDimensions().getHeight()) {
            return false;
        }

        double distanceX = Math.abs(this.getCenter().getX() - other.getCenter().getX());

        return distanceX <= this.getDimensions().getWidth();
    }

    @Override
    public Location getLocation(Hitbox other) {
        return null;
    }

    @Override
    public Hitbox clone() {
        return null;
    }

    @Override
    public String toString() {
        return "AbstractLocationHitbox{" +
                "priority=" + priority +
                ", center=" + center +
                ", dimensions=" + dimensions +
                ", immovable=" + immovable +
                ", interactable=" + interactable +
                '}';
    }
}
