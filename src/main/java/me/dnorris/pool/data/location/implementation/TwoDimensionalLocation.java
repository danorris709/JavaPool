package me.dnorris.pool.data.location.implementation;

import me.dnorris.pool.data.location.Location;

import java.util.Objects;

public class TwoDimensionalLocation implements Location {

    private double x;
    private double y;

    public TwoDimensionalLocation(double x, double y) {
        this.x = x;
        this.y = y;
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
        throw new UnsupportedOperationException("Cannot have a Z value for a 2dimensional location");
    }

    @Deprecated
    @Override
    public void setZ(double z) {
        throw new UnsupportedOperationException("Cannot have a Z value for a 2dimensional location");
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (object == null || this.getClass() != object.getClass()) {
            return false;
        }

        TwoDimensionalLocation that = (TwoDimensionalLocation) object;

        return Double.compare(that.x, this.x) == 0 &&
                Double.compare(that.y, this.y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.x, this.y);
    }
}
