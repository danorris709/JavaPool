package me.dnorris.pool.arena.entity.shape;

import me.dnorris.pool.arena.Entity;
import me.dnorris.pool.arena.entity.AbstractEntity;
import me.dnorris.pool.data.hitbox.implementation.location.RectangleLocationHitbox;
import me.dnorris.pool.data.location.Location;
import me.dnorris.pool.data.vector.Vector;
import me.dnorris.pool.data.vector.implementation.Vector2D;

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
            other.setMotion(this.getMotion().multiply(new Vector2D(-1, 1)));
            return;
        }

        other.setMotion(this.getMotion().multiply(new Vector2D(1, -1)));
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
