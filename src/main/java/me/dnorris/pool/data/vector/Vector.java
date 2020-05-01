package me.dnorris.pool.data.vector;

/**
 *
 * Interface representation a motion with values of X, Y, and Z.
 *
 * @author https://github.com/danorris709
 */
public interface Vector {

    /**
     *
     * Gets the X value of the {@link Vector}
     *
     * @return X value of the {@link Vector}
     */
    double getX();

    /**
     *
     * Sets the X value of the {@link Vector}
     *
     * @param x the new value of the {@link Vector}
     */
    void setX(double x);

    /**
     *
     * Gets the Y value of the {@link Vector}
     *
     * @return Y value of the {@link Vector}
     */
    double getY();

    /**
     *
     * Sets the Y value of the {@link Vector}
     *
     * @param y the new value of the {@link Vector}
     */
    void setY(double y);

    /**
     *
     * Gets the Z value of the {@link Vector}
     *
     * @return Z value of the {@link Vector}
     */
    double getZ();

    /**
     *
     * Sets the Z value of the {@link Vector}
     *
     * @param z the new value of the {@link Vector}
     */
    void setZ(double z);

    /**
     *
     * Gets the length of the {@link Vector}
     *
     * @return length of the {@link Vector}
     */
    double getLength();

    /**
     *
     * A new {@link Vector} with {@link Vector#getLength()} of value 1
     *
     * @return a new unit {@link Vector}
     */
    Vector getUnitVector();

    /**
     *
     * A new copy of the existing {@link Vector} object
     *
     * @return The new clone
     */
    Vector clone();

    // TODO: 21/04/2020 Add more maths functions as required

}
