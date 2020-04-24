package me.dnorris.pool.data.location;

import java.awt.*;

public interface Location {

    double getX();

    void setX(double x);

    double getY();

    void setY(double y);

    double getZ();

    void setZ(double z);

    Point asPoint();

    Location clone();

}
