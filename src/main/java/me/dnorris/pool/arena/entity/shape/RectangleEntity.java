package me.dnorris.pool.arena.entity.shape;

import me.dnorris.pool.arena.entity.AbstractEntity;
import me.dnorris.pool.data.location.Location;
import me.dnorris.pool.data.vector.implementation.Vector2D;

import java.awt.*;

public class RectangleEntity extends AbstractEntity {

    public RectangleEntity(Location location) {
        super(location, null, new Vector2D(0.0, 0.0));
    }

    @Override
    public void paint(Graphics2D graphics) {

    }

    @Override
    public void tick() {

    }
}
