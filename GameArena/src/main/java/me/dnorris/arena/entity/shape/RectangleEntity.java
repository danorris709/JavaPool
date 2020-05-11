package me.dnorris.arena.entity.shape;

import me.dnorris.arena.Entity;
import me.dnorris.arena.entity.AbstractEntity;
import me.dnorris.arena.entity.EntityBuilder;
import me.dnorris.data.hitbox.implementation.location.RectangleLocationHitbox;
import me.dnorris.data.location.Location;
import me.dnorris.data.vector.Vector;
import me.dnorris.data.vector.implementation.Vector2D;

import java.awt.*;

/**
 *
 * Basic implementation for a Rectangle {@link Entity}
 *
 * @author https://github.com/danorris709
 */
public class RectangleEntity extends AbstractEntity {

    /**
     *
     * Default constructor
     * Public for instantiation via {@link EntityBuilder}
     *
     * @param location Location of the Rectangle
     * @param colour Colour of the rectangle
     * @param motion initial motion of the rectangle
     * @param dimension dimensions of the rectangle
     * @param hollow If the rectangle is hollow
     * @param immovable If the rectangle can be moved
     * @param interactable If the rectangle can be interacted with
     */
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
