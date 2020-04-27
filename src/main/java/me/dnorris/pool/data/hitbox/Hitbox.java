package me.dnorris.pool.data.hitbox;

import me.dnorris.pool.data.location.Location;

import java.awt.*;

public interface Hitbox {

    Location getCenter();

    Dimension getDimensions();

    boolean isImmovable();

    void setImmovable(boolean immovable);

    boolean isColliding(Hitbox other);

}
