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

        int xPos = (int) (this.getLocation().getX() - (this.getHitbox().getDimensions().getWidth() / 2.0));
        int yPos = (int) (this.getLocation().getY() - (this.getHitbox().getDimensions().getHeight() / 2.0));

        if (this.hollow) {
            graphics.drawOval(xPos, yPos, (int) this.getHitbox().getDimensions().getWidth(), (int) this.getHitbox().getDimensions().getHeight());
        } else {
            graphics.fillOval(xPos, yPos, (int) this.getHitbox().getDimensions().getWidth(), (int) this.getHitbox().getDimensions().getHeight());
        }
    }

    @Override
    public void tick() {
        super.tick();
    }
}
