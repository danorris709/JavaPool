package me.dnorris.pool.arena;

import me.dnorris.pool.data.hitbox.Hitbox;
import me.dnorris.pool.data.location.Location;
import me.dnorris.pool.data.vector.Vector;

import javax.swing.*;

public interface Entity {

    Location getLocation();

    void setLocation(Location location);

    Hitbox getHitbox();

    Vector getMotion();

    void setMotion(Vector motion);

    JComponent getComponent();

    void tick();

}
