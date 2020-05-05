package me.dnorris.pool.data.hitbox.implementation.location;

import me.dnorris.pool.data.hitbox.Hitbox;
import me.dnorris.pool.data.hitbox.implementation.AbstractLocationHitbox;
import me.dnorris.pool.data.location.Location;
import me.dnorris.pool.data.location.implementation.Location2D;

import java.awt.*;

public class RectangleLocationHitbox extends AbstractLocationHitbox {

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
