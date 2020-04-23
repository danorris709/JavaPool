package me.dnorris.pool.arena;

import me.dnorris.pool.data.hitbox.Hitbox;
import me.dnorris.pool.data.location.Location;
import me.dnorris.pool.data.vector.Vector;

public interface Entity {

    Location getLocation();

    void setLocation(Object location);

    Hitbox getHitbox();

    Vector getMotion();

    void setMotion(Vector motion);

    void tick();

}
