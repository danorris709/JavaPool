package me.dnorris.pool.data.vector;

public interface Vector {

    double getX();

    void setX(double x);

    double getY();

    void setY(double y);

    double getZ();

    void setZ(double z);

    double getLength();

    Vector getUnitVector();

    Vector clone();

    // TODO: 21/04/2020 Add more maths functions as required

}
