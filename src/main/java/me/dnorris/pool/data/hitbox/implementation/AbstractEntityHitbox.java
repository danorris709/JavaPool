package me.dnorris.pool.data.hitbox.implementation;

import me.dnorris.pool.arena.Entity;
import me.dnorris.pool.data.hitbox.Hitbox;
import me.dnorris.pool.data.location.Location;

public abstract class AbstractEntityHitbox implements Hitbox {

    private Entity center;

    protected AbstractEntityHitbox(Entity center) {
        this.center = center;
    }

    @Override
    public Location getCenter() {
        return this.center.getLocation();
    }
}
