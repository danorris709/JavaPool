package me.dnorris.pool.data.hitbox.implementation.location;

import me.dnorris.pool.data.hitbox.Hitbox;
import me.dnorris.pool.data.hitbox.implementation.AbstractLocationHitbox;
import me.dnorris.pool.data.location.Location;

import java.awt.*;

public class CircleLocationHitbox extends AbstractLocationHitbox {

    public CircleLocationHitbox(Location center, Dimension dimensions, boolean immovable, boolean interactable) {
        super(1, center, dimensions, immovable, interactable);
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
