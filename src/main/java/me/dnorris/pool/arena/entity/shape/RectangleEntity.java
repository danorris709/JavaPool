package me.dnorris.pool.arena.entity.shape;

import me.dnorris.pool.arena.entity.AbstractEntity;
import me.dnorris.pool.data.hitbox.implementation.SquareLocationHitbox;
import me.dnorris.pool.data.location.Location;
import me.dnorris.pool.data.vector.Vector;

import java.awt.*;

public class RectangleEntity extends AbstractEntity {

    public RectangleEntity(Location location, Color colour, Vector motion, Dimension dimension, boolean hollow, boolean immovable, boolean interactable) {
        super(colour, location, new SquareLocationHitbox(location, dimension, immovable, interactable), motion, hollow);
    }

    @Override
    public void paint(Graphics2D graphics) {
        graphics.setColor(this.getColour());

        if (this.hollow) {
            graphics.draw(new Rectangle(this.getLocation().asPoint(), this.getHitbox().getDimensions()));
        } else {
            graphics.fill(new Rectangle(this.getLocation().asPoint(), this.getHitbox().getDimensions()));
        }
    }

    @Override
    public void tick() {
        super.tick();
    }
}
