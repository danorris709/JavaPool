package me.dnorris.pool.data.location.implementation;

import me.dnorris.pool.data.location.Location;

import java.awt.*;
import java.util.Objects;

public class Location2D implements Location {

    private int x;
    private int y;

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
}
