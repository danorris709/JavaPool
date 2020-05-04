package me.dnorris.pool.arena.entity.shape;

import me.dnorris.pool.arena.entity.AbstractEntity;
import me.dnorris.pool.data.hitbox.implementation.location.CircleLocationHitbox;
import me.dnorris.pool.data.hitbox.implementation.location.RectangleLocationHitbox;
import me.dnorris.pool.data.location.Location;
import me.dnorris.pool.data.vector.Vector;

import java.awt.*;

public class CircleEntity extends AbstractEntity {

    public CircleEntity(Location location, Color colour, Vector motion, Dimension dimension, boolean hollow, boolean immovable, boolean interactable) {
        super(colour, location, new CircleLocationHitbox(location, dimension, immovable, interactable), motion, hollow);
    }

    @Override
    public void paint(Graphics2D graphics) {
        graphics.setColor(this.getColour());

        if (this.hollow) {
            graphics.drawOval((int) this.getLocation().getX(), (int) this.getLocation().getY(), (int) this.getHitbox().getDimensions().getWidth(), (int) this.getHitbox().getDimensions().getHeight());
        } else {
            graphics.fillOval((int) this.getLocation().getX(), (int) this.getLocation().getY(), (int) this.getHitbox().getDimensions().getWidth(), (int) this.getHitbox().getDimensions().getHeight());
        }
    }

    @Override
    public void tick() {
        super.tick();
    }
}
