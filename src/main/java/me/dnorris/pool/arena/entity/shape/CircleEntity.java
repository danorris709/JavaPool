package me.dnorris.pool.arena.entity.shape;

import me.dnorris.pool.arena.entity.AbstractEntity;
import me.dnorris.pool.data.hitbox.implementation.SquareLocationHitbox;
import me.dnorris.pool.data.location.Location;
import me.dnorris.pool.data.vector.Vector;

import java.awt.*;

public class CircleEntity extends AbstractEntity {

    public CircleEntity(Location location, Color colour, Vector motion, Dimension dimension, boolean hollow, boolean immovable, boolean interactable) {
        super(colour, location, new SquareLocationHitbox(location, dimension, immovable, interactable), motion, hollow);
    }

    @Override
    public void paint(Graphics2D graphics) {
        if(!this.isDirty()) {
            return;
        }

        graphics.setColor(this.getColour());

        if (this.hollow) {
            graphics.drawOval(this.getLocation().getX(), this.getLocation().getY(), (int) this.getHitbox().getDimensions().getWidth(), (int) this.getHitbox().getDimensions().getHeight());
        } else {
            graphics.fillOval(this.getLocation().getX(), this.getLocation().getY(), (int) this.getHitbox().getDimensions().getWidth(), (int) this.getHitbox().getDimensions().getHeight());
        }

        this.setDirty(false);
    }

    @Override
    public void tick() {
        super.tick();
    }
}
