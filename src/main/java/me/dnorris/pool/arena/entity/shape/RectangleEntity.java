package me.dnorris.pool.arena.entity.shape;

import me.dnorris.pool.arena.entity.AbstractEntity;
import me.dnorris.pool.data.location.Location;
import me.dnorris.pool.data.vector.implementation.Vector2D;

import javax.swing.*;

public class RectangleEntity extends AbstractEntity {

    public RectangleEntity(Location location) {
        super(location, null, new Vector2D(0.0, 0.0),
                new JPanel()); // TODO: 24/04/2020 initialize these values and give a proper hitbox
    }

    @Override
    public void tick() {

    }
}
