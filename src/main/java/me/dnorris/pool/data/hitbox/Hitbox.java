package me.dnorris.pool.data.hitbox;

import me.dnorris.pool.data.location.Location;

public interface Hitbox {

    Location getCenter();

    boolean isColliding(Hitbox other);

}
