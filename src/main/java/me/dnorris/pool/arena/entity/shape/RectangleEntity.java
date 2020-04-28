package me.dnorris.pool.arena.entity.shape;

import me.dnorris.pool.arena.entity.AbstractEntity;
import me.dnorris.pool.data.hitbox.implementation.SquareLocationHitbox;
import me.dnorris.pool.data.location.Location;
import me.dnorris.pool.data.vector.implementation.Vector2D;

import java.awt.*;

public class RectangleEntity extends AbstractEntity {

    public RectangleEntity(Color colour, Location location, Dimension dimension, boolean hollow, boolean immovable) {
        super(colour, location, new SquareLocationHitbox(location, dimension, immovable), new Vector2D(0.0, 0.0), hollow);
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
