package me.dnorris.pool.arena.entity.shape;

import me.dnorris.pool.arena.Entity;
import me.dnorris.pool.arena.entity.AbstractEntity;
import me.dnorris.pool.data.hitbox.implementation.location.RectangleLocationHitbox;
import me.dnorris.pool.data.location.Location;
import me.dnorris.pool.data.vector.Vector;

import java.awt.*;

public class RectangleEntity extends AbstractEntity {

    public RectangleEntity(Location location, Color colour, Vector motion, Dimension dimension, boolean hollow, boolean immovable, boolean interactable) {
        super(colour, location, new RectangleLocationHitbox(location, dimension, immovable, interactable), motion, hollow);
    }

    @Override
    public void collide(Entity other) {
        if(other.getHitbox().getPriority() > this.getHitbox().getPriority()) {
            other.collide(this);
            return;
        }

        Dimension dimension = this.getHitbox().getDimensions();

        if(dimension.getHeight() > dimension.getWidth()) {
            Vector newMotion = other.getMotion().clone();

            newMotion.setX(newMotion.getX() * -1);

            other.setMotion(newMotion);
            return;
        }

        Vector newMotion = other.getMotion().clone();

        newMotion.setY(newMotion.getY() * -1);

        other.setMotion(newMotion);
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
