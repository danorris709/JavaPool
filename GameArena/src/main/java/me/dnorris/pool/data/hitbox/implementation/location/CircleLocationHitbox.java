package me.dnorris.pool.data.hitbox.implementation.location;

import me.dnorris.pool.data.hitbox.Hitbox;
import me.dnorris.pool.data.hitbox.implementation.AbstractLocationHitbox;
import me.dnorris.pool.data.location.Location;

import java.awt.*;

/**
 *
 * A spherical implementation of the {@link AbstractLocationHitbox}
 *
 * @author https://github.com/danorris709
 */
public class CircleLocationHitbox extends AbstractLocationHitbox {

    /**
     *
     * Default constructor. Specifying priority of 1 for ball vs ball collisions.
     *
     * @param center Center {@link Location} of the ball
     * @param dimensions {@link Dimension} of the ball
     * @param immovable if the ball can be moved
     * @param interactable if the ball can be interacted with
     */
    public CircleLocationHitbox(Location center, Dimension dimensions, boolean immovable, boolean interactable) {
        super(1, center, dimensions, immovable, interactable);
    }

    @Override
    public boolean isColliding(Hitbox other) {
        if(!(this.isInteractable() && other.isInteractable())) {
            return false;
        }

        if(other.getPriority() > this.getPriority()) {
            return other.isColliding(this);
        }

        double distanceY = this.getCenter().getY() - other.getCenter().getY();
        double radius = this.getDimensions().height;
        double distanceX = this.getCenter().getX() - other.getCenter().getX();

        if(radius > 30) {
            radius /= 1.5;
        }

        return (Math.pow(distanceY, 2) + Math.pow(distanceX, 2)) <= Math.pow(radius, 2);
    }

    @Override
    public Hitbox clone() {
        return new CircleLocationHitbox(
                this.getCenter().clone(),
                new Dimension(this.getDimensions().width, this.getDimensions().height),
                this.isImmovable(),
                this.isInteractable()
        );
    }
}
