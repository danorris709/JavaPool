package me.dnorris.pool.data.hitbox.implementation.location;

import me.dnorris.pool.data.hitbox.Hitbox;
import me.dnorris.pool.data.hitbox.implementation.AbstractLocationHitbox;
import me.dnorris.pool.data.location.Location;

import java.awt.*;

/**
 *
 * An implementation of the {@link AbstractLocationHitbox} that cannot be interacted with
 * and has 0 height and 0 width.
 *
 * @author https://github.com/danorris709
 */
public class EmptyHitbox extends AbstractLocationHitbox {

    /**
     *
     * Constructor specifying the center point of the {@link Hitbox}
     * {@link Integer#MIN_VALUE} for the priority because it has no collisions so why would it take priority.
     *
     * @param center Center {@link Location}
     * @param immovable if the hitbox can actually be moved
     */
    public EmptyHitbox(Location center, boolean immovable) {
        super(Integer.MIN_VALUE, center, new Dimension(0, 0), immovable, false);
    }

    @Override
    public boolean isColliding(Hitbox other) {
        return false;
    }
}
