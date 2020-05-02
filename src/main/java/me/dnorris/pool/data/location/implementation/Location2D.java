package me.dnorris.pool.data.location.implementation;

import me.dnorris.pool.data.location.Location;

import java.awt.*;
import java.util.Objects;

/**
 *
 * Two dimensional implementation of the {@link Location} interface
 *
 * @author https://github.com/danorris709
 */
public class Location2D implements Location {

    private int x;      // x position of the Location
    private int y;      // y position of the Location

    /**
     *
     * Basic constructor passing x, and y values for the two dimensional location
     *
     * @param x The x position of the location
     * @param y The y position of the location
     */
    public Location2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int getX() {
        return this.x;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return this.y;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    @Deprecated
    @Override
    public int getZ() {
        throw new UnsupportedOperationException("Cannot have a Z value for a 2 dimensional location");
    }

    @Deprecated
    @Override
    public void setZ(int z) {
        throw new UnsupportedOperationException("Cannot have a Z value for a 2 dimensional location");
    }

    @Override
    public Point asPoint() {
        return new Point(this.x, this.y);
    }

    @Override
    public Location add(int x, int y, int z) {
        Location location = this.clone();

        location.setX(location.getX() + x);
        location.setY(location.getY() + y);

        return location;
    }

    @Override
    public Location subtract(int x, int y, int z) {
        Location location = this.clone();

        location.setX(location.getX() - x);
        location.setY(location.getY() - y);

        return location;
    }

    @Override
    public double distance(Location other) {
        return this.distanceSquared(other);
    }

    @Override
    public double distanceSquared(Location other) {
        double xDistance = Math.abs(this.x - other.getX());
        double yDistance = Math.abs(this.y - other.getY());

        return Math.sqrt(Math.pow(xDistance, 2) + Math.pow(yDistance, 2));
    }

    @SuppressWarnings("MethodDoesntCallSuperMethod")
    @Override
    public Location clone() {
        return new Location2D(this.x, this.y);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (object == null || this.getClass() != object.getClass()) {
            return false;
        }

        Location2D that = (Location2D) object;

        return that.x == this.x && that.y == this.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.x, this.y);
    }

    @Override
    public String toString() {
        return "Location2D{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
