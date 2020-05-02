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
