package me.dnorris.pool.data.hitbox.implementation.location;

import me.dnorris.pool.data.hitbox.Hitbox;
import me.dnorris.pool.data.hitbox.implementation.AbstractLocationHitbox;
import me.dnorris.pool.data.location.Location;

import java.awt.*;

public class EmptyHitbox extends AbstractLocationHitbox {

    public EmptyHitbox(Location center, boolean immovable) {
        super(Integer.MIN_VALUE, center, new Dimension(0, 0), immovable, false);
    }

    @Override
    public boolean isColliding(Hitbox other) {
        return false;
    }
}
