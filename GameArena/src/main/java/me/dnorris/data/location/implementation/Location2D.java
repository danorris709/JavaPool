package me.dnorris.data.location.implementation;

import me.dnorris.data.location.Location;

import java.awt.*;
import java.util.Objects;

/**
 *
 * Two dimensional implementation of the {@link Location} interface
 *
 * @author https://github.com/danorris709
 */
public class Location2D implements Location {

    private double x;      // x position of the Location
    private double y;      // y position of the Location

    /**
     *
     * Basic constructor passing x, and y values for the two dimensional location
     *
     * @param x The x position of the location
     * @param y The y position of the location
     */
    public Location2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     *
     * Constructor converting a {@link Point} to a {@link Location}
     *
     * @param point The point being converted
     */
    public Location2D(Point point) {
        this(point.x, point.y);
    }

    @Override
    public double getX() {
        return this.x;
    }

    @Override
    public void setX(double x) {
        this.x = x;
    }

    @Override
    public double getY() {
        return this.y;
    }

    @Override
    public void setY(double y) {
        this.y = y;
    }

    @Deprecated
    @Override
    public double getZ() {
        throw new UnsupportedOperationException("Cannot have a Z value for a 2 dimensional location");
    }

    @Deprecated
    @Override
    public void setZ(double z) {
        throw new UnsupportedOperationException("Cannot have a Z value for a 2 dimensional location");
    }

    @Override
    public Point asPoint() {
        return new Point((int) this.x, (int) this.y);
    }

    @Override
    public Location add(double x, double y, double z) {
        Location location = this.clone();

        location.setX(location.getX() + x);
        location.setY(location.getY() + y);

        return location;
    }

    @Override
    public Location subtract(double x, double y, double z) {
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
