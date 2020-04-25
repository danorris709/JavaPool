package me.dnorris.pool.data.hitbox.implementation;

import me.dnorris.pool.data.hitbox.Hitbox;
import me.dnorris.pool.data.location.Location;

import java.awt.*;

public class SquareLocationHitbox extends AbstractLocationHitbox {

    public SquareLocationHitbox(Location center, Dimension dimensions) {
        super(center, dimensions);
    }

    @Override
    public boolean isColliding(Hitbox other) {
        return false;
    }
}
