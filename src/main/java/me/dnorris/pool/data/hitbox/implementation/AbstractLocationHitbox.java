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
