package me.dnorris.pool.data.location;

import java.awt.*;

public interface Location {

    int getX();

    void setX(int x);

    int getY();

    void setY(int y);

    int getZ();

    void setZ(int z);

    Point asPoint();

    Location clone();

}
