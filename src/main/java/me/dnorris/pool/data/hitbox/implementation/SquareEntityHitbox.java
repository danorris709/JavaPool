package me.dnorris.pool.data.hitbox.implementation;

import me.dnorris.pool.arena.Entity;
import me.dnorris.pool.data.hitbox.Hitbox;

public class SquareEntityHitbox extends AbstractEntityHitbox {

    public SquareEntityHitbox(Entity center) {
        super(center);
    }

    @Override
    public boolean isColliding(Hitbox other) {
        return false;
    }
}
