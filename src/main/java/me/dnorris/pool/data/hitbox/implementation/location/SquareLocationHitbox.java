package me.dnorris.pool.data.hitbox.implementation.location;

import me.dnorris.pool.data.hitbox.Hitbox;
import me.dnorris.pool.data.hitbox.implementation.AbstractLocationHitbox;
import me.dnorris.pool.data.location.Location;

import java.awt.*;

public class SquareLocationHitbox extends AbstractLocationHitbox {

    public SquareLocationHitbox(int priority, Location center, Dimension dimensions, boolean immovable, boolean interactable) {
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

        return false;
    }

    @Override
    public Hitbox clone() {
        return new SquareLocationHitbox(
                this.getPriority(),
                this.getCenter().clone(),
                new Dimension(this.getDimensions().width, this.getDimensions().height),
                this.isImmovable(),
                this.isInteractable()
        );
    }
}
