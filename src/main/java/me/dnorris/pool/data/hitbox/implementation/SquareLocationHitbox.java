package me.dnorris.pool.data.hitbox.implementation;

import me.dnorris.pool.data.hitbox.Hitbox;
import me.dnorris.pool.data.location.Location;

import java.awt.*;

public class SquareLocationHitbox extends AbstractLocationHitbox {

    public SquareLocationHitbox(Location center, Dimension dimensions, boolean immovable, boolean interactable) {
        super(center, dimensions, immovable, interactable);
    }

    @Override
    public boolean isColliding(Hitbox other) {
        if(!super.isColliding(other)) {
            return false;
        }

        return false;
    }
}
