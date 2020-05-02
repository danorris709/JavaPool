package me.dnorris.pool.data.hitbox.implementation.location;

import me.dnorris.pool.data.hitbox.Hitbox;
import me.dnorris.pool.data.hitbox.implementation.AbstractLocationHitbox;
import me.dnorris.pool.data.location.Location;

import java.awt.*;

public class RectangleLocationHitbox extends AbstractLocationHitbox {

    public RectangleLocationHitbox(int priority, Location center, Dimension dimensions, boolean immovable, boolean interactable) {
        super(priority, center, dimensions, immovable, interactable);
    }

    @Override
    public boolean isColliding(Hitbox other) {
        if(other.getPriority() > this.getPriority()) {
            return other.isColliding(this);
        }

        if(!super.isColliding(other)) {
            return false;
        }

        double distanceY = Math.abs(this.getCenter().getY() - other.getCenter().getY());

        if(distanceY <= this.getDimensions().getHeight()) {
            return true;
        }

        double distanceX = Math.abs(this.getCenter().getX() - other.getCenter().getX());

        return distanceX <= this.getDimensions().width;
    }

    @Override
    public Hitbox clone() {
        return new RectangleLocationHitbox(
                this.getPriority(),
                this.getCenter().clone(),
                new Dimension(this.getDimensions().width, this.getDimensions().height),
                this.isImmovable(),
                this.isInteractable()
        );
    }
}
