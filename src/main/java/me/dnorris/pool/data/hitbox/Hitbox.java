package me.dnorris.pool.data.hitbox;

import me.dnorris.pool.data.location.Location;

import java.awt.*;

public interface Hitbox {

    Location getCenter();

    Dimension getDimensions();

    boolean isColliding(Hitbox other);

}
