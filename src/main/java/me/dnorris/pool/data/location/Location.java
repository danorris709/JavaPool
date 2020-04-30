package me.dnorris.pool.data.location;

import java.awt.*;

/**
 *
 * Location interface for representing a single point in the x,y,z plane
 *
 * @author https://github.com/danorris709
 */
public interface Location {

    /**
     *
     * Gets the X value from the {@link Location}
     *
     * @return the X value
     */
    int getX();

    /**
     *
     * Update the X value
     *
     * @param x set the X value to this
     */
    void setX(int x);

    /**
     *
     * Gets the Y value from the {@link Location}
     *
     * @return the Y value
     */
    int getY();

    /**
     *
     * Update the Y value
     *
     * @param y set the Y value to this
     */
    void setY(int y);

    /**
     *
     * Gets the Z value from the {@link Location}
     *
     * @return the Z value
     */
    int getZ();

    /**
     *
     * Update the Z value
     *
     * @param z set the Z value to this
     */
    void setZ(int z);

    /**
     *
     * Crates a {@link Point} version of the {@link Location}
     *
     * @return The GUI version of the {@link Location}
     */
    Point asPoint();

    /**
     *
     * Creates a direct copy of the object
     *
     * @return New version of the {@link Location}
     */
    Location clone();

    /**
     *
     * Creates a copy of the {@link Location} and adds the parameters
     *
     * @param x The value to be added to the X
     * @param y The value to be added to the Y
     * @param z The value to be added to the Z
     * @return The clone of the object with updated {@link Location#getX()}, {@link Location#getY()}, {@link Location#getZ()} values
     */
    Location add(int x, int y, int z);

    /**
     *
     * Creates a copy of the {@link Location} and subtracts the parameters
     *
     * @param x The value to be taken from the X
     * @param y The value to be taken from the Y
     * @param z The value to be taken from the Z
     * @return The clone of the object with updated {@link Location#getX()}, {@link Location#getY()}, {@link Location#getZ()} values
     */
    Location subtract(int x, int y, int z);

}
