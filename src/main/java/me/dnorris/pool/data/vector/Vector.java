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
    Vector normalize();

    /**
     *
     * Multiply the X and Y values of the {@link Vector} by the specified parameter
     *
     * @param value The amount to multiply by
     * @return a new clone of the vector
     */
    Vector multiply(double value);

    /**
     *
     * A new copy of the existing {@link Vector} object
     *
     * @return The new clone
     */
    Vector clone();

    /**
     *
     * Calculate the dot product between the two {@link Vector}s
     *
     * @param vector The other {@link Vector}
     * @return the dot product
     */
    double dotProduct(Vector vector);

    // TODO: 21/04/2020 Add more maths functions as required

}
