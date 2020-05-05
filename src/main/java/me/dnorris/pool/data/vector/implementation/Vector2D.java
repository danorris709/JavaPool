package me.dnorris.pool.data.vector.implementation;

import me.dnorris.pool.data.location.Location;
import me.dnorris.pool.data.vector.Vector;

import java.util.Objects;

/**
 *
 * Two dimensional implementation of the {@link Vector} interface
 *
 * @author https://github.com/danorris709
 */
public class Vector2D implements Vector {

    public static final Vector NONE = new Vector2D(0.0, 0.0); // No motion vector constant
    public static final Vector i = new Vector2D(1.0, 0.0); // unit vector along the x axis

    private double x;                                               // x motion of the Vector
    private double y;                                               // y motion of the Vector

    /**
     *
     * Basic constructor passing x, and y values for the two dimensional vector
     *
     * @param x The x motion of the vector
     * @param y The y motion of the vector
     */
    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     *
     * Basic constructor between the two points passed
     *
     * @param firstPoint First point
     * @param secondPoint Second point
     */
    public Vector2D(Location firstPoint, Location secondPoint) {
        this.x = firstPoint.getX() - secondPoint.getX();
        this.y = firstPoint.getY() - secondPoint.getY();
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

    @SuppressWarnings("MethodDoesntCallSuperMethod")
    @Override
    public Vector clone() {
        return new Vector2D(this.x, this.y);
    }

    @Override
    public double getLength() {
        return Math.sqrt((this.x * this.x) + (this.y * this.y));
    }

    @Override
    public Vector normalize() {
        Vector clone = this.clone();
        double length = clone.getLength();

        if(length == 0) {
            return i;
        }

        clone.setX(clone.getX() / length);
        clone.setY(clone.getY() / length);

        return clone;
    }

    @Override
    public double dotProduct(Vector vector) {
        return Math.abs((this.getX() * vector.getX()) + (this.getY() * vector.getY()));
    }

    @Override
    public Vector multiply(double value) {
        return new Vector2D(this.x * value, this.y * value);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (object == null || this.getClass() != object.getClass()) {
            return false;
        }

        Vector2D that = (Vector2D) object;

        return Double.compare(that.x, this.x) == 0 &&
                Double.compare(that.y, this.y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.x, this.y);
    }

    @Override
    public String toString() {
        return "Vector2D{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
