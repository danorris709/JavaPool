package me.dnorris.pool.data.hitbox.implementation.location;

import me.dnorris.pool.data.hitbox.Hitbox;
import me.dnorris.pool.data.hitbox.implementation.AbstractLocationHitbox;
import me.dnorris.pool.data.location.Location;
import me.dnorris.pool.data.location.implementation.Location2D;

import java.awt.*;

/**
 *
 * A rectangle implementation of the {@link AbstractLocationHitbox}
 *
 * @author https://github.com/danorris709
 */
public class RectangleLocationHitbox extends AbstractLocationHitbox {

    /**
     *
     * Default constructor for Rectangle {@link AbstractLocationHitbox}
     * Priority 2 as ball vs ball collisions don't work for ball vs rectangle
     *
     * @param center Center {@link Location} of the rectangle
     * @param dimensions {@link Dimension} of the rectangle
     * @param immovable if the ball can be moved
     * @param interactable if the rectangle can be interacted with
     */
    public RectangleLocationHitbox(Location center, Dimension dimensions, boolean immovable, boolean interactable) {
        super(2, center.add(dimensions.getWidth() / 2.0, dimensions.getHeight() / 2.0, 0), dimensions, immovable, interactable);
    }

    @Override
    public Location getLocation(Hitbox other) {
        Location otherCenter = other.getCenter();
        Location thisCenter = this.getCenter();

        if(otherCenter.getX() >= thisCenter.getX()) {
            return new Location2D(otherCenter.getX() + (other.getDimensions().getWidth() / 2.0), other.getCenter().getY());
        }

        return new Location2D(otherCenter.getX() - (other.getDimensions().getWidth() / 2.0), other.getCenter().getY());
    }

    @Override
    public Hitbox clone() {
        return new RectangleLocationHitbox(
                this.getCenter().clone(),
                new Dimension(this.getDimensions().width, this.getDimensions().height),
                this.isImmovable(),
                this.isInteractable()
        );
    }
}
