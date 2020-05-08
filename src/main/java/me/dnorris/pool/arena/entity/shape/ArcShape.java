package me.dnorris.pool.arena.entity.shape;

import me.dnorris.pool.arena.Entity;
import me.dnorris.pool.arena.entity.AbstractEntity;
import me.dnorris.pool.arena.entity.EntityBuilder;
import me.dnorris.pool.arena.entity.EntityType;
import me.dnorris.pool.data.hitbox.Hitbox;
import me.dnorris.pool.data.hitbox.implementation.location.CircleLocationHitbox;
import me.dnorris.pool.data.hitbox.implementation.location.RectangleLocationHitbox;
import me.dnorris.pool.data.location.Location;
import me.dnorris.pool.data.vector.Vector;

import java.awt.*;

/**
 *
 * Represents an arc on the {@link me.dnorris.pool.arena.GameArena}
 *
 * @author https://github.com/danorris709
 */
public class ArcShape extends AbstractEntity {

    private int startAngle;
    private int arcAngle;

    /**
     *
     * Default constructor
     * Protected for instantiation via sub classes or the builder
     *
     * @param location Location of the arc
     * @param colour Colour of the arc
     * @param motion initial motion of the arc
     * @param dimension dimensions of the arc
     * @param hollow If the arc is hollow
     * @param immovable If the arc can be moved
     * @param interactable If the arc can be interacted with
     * @param startAngle start angle of the arc
     * @param arcAngle number of degrees the arc rotates from the start angle
     */
    protected ArcShape(Location location, Color colour, Vector motion, Dimension dimension, boolean hollow, boolean immovable, boolean interactable, int startAngle, int arcAngle) {
        super(colour, location, new RectangleLocationHitbox(location, dimension, immovable, interactable), motion, hollow);

        this.startAngle = startAngle;
        this.arcAngle = arcAngle;
    }

    @Override
    public void paint(Graphics2D graphics) {
        graphics.setColor(this.getColour());

        int xPos = (int) (this.getLocation().getX() - (this.getHitbox().getDimensions().getWidth() / 2.0));
        int yPos = (int) (this.getLocation().getY() - (this.getHitbox().getDimensions().getHeight() / 2.0));

        if (this.hollow) {
            graphics.drawArc(xPos, yPos, this.getHitbox().getDimensions().width, this.getHitbox().getDimensions().height, this.startAngle, this.arcAngle);
        } else {
            graphics.fillArc(xPos, yPos, this.getHitbox().getDimensions().width, this.getHitbox().getDimensions().height, this.startAngle, this.arcAngle);
        }
    }

    @Override
    public void tick() {
        super.tick();
    }

    /**
     *
     * Builder class for Arc as it has custom flags (i.e. start & end angles)
     *
     * @author https://github.com/danorris709
     */
    public static class Builder extends EntityBuilder {

        private int startAngle;
        private int arcAngle;

        public Builder() { super(); }

        @Override
        public Builder setColour(Color colour) {
            return (Builder) super.setColour(colour);
        }

        @Override
        public Builder setLocation(Location location) {
            return (Builder) super.setLocation(location);
        }

        @Override
        public Builder setHitbox(Hitbox hitbox) {
            return (Builder) super.setHitbox(hitbox);
        }

        @Override
        public Builder setMotion(Vector motion) {
            return (Builder) super.setMotion(motion);
        }

        @Override
        public Builder setHollow(boolean hollow) {
            return (Builder) super.setHollow(hollow);
        }

        @Override
        public Builder setType(EntityType type) {
            return (Builder) super.setType(type);
        }

        @Override
        public Builder setImmovable(boolean immovable) {
            return (Builder) super.setImmovable(immovable);
        }

        @Override
        public Builder setInteractable(boolean interactable) {
            return (Builder) super.setInteractable(interactable);
        }

        @Override
        public Builder setDimension(Dimension dimension) {
            return (Builder) super.setDimension(dimension);
        }

        public Builder setStartAngle(int startAngle) {
            this.startAngle = startAngle;
            return this;
        }

        public Builder setArcAngle(int arcAngle) {
            this.arcAngle = arcAngle;
            return this;
        }

        @Override
        public Entity build() {
            return new ArcShape(
                    this.location,
                    this.colour,
                    this.motion,
                    this.dimension,
                    this.hollow,
                    this.immovable,
                    this.interactable,
                    this.startAngle,
                    this.arcAngle
            );
        }

        @Override
        public Builder clone() {
            Builder newBuilder = new Builder();

            if (this.hitbox != null) {
                newBuilder.hitbox = this.hitbox.clone();
            }

            if (this.location != null) {
                newBuilder.location = this.location.clone();
            }

            if (this.dimension != null) {
                newBuilder.dimension = new Dimension(this.dimension.width, this.dimension.height);
            }

            newBuilder.colour = this.colour;
            newBuilder.motion = this.motion;
            newBuilder.hollow = this.hollow;
            newBuilder.immovable = this.immovable;
            newBuilder.interactable = this.interactable;
            newBuilder.type = this.type;

            newBuilder.arcAngle = this.arcAngle;
            newBuilder.startAngle = this.startAngle;

            return newBuilder;
        }
    }
}
