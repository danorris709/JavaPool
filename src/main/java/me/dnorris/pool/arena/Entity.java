package me.dnorris.pool.arena;

import me.dnorris.pool.data.hitbox.Hitbox;
import me.dnorris.pool.data.location.Location;
import me.dnorris.pool.data.vector.Vector;

import java.awt.*;

public interface Entity {

    Color getColour();

    void setColour(Color colour);

    Location getLocation();

    void setLocation(Location location);

    Hitbox getHitbox();

    Vector getMotion();

    void setMotion(Vector motion);

    void paint(Graphics2D graphics);

    void tick();

}
