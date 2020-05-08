package me.dnorris.pool.arena.entity.compound.shape;

import com.google.common.collect.Lists;
import me.dnorris.pool.arena.Entity;
import me.dnorris.pool.arena.entity.EntityBuilder;
import me.dnorris.pool.arena.entity.EntityType;
import me.dnorris.pool.arena.entity.compound.CompoundEntity;
import me.dnorris.pool.arena.entity.shape.RectangleEntity;
import me.dnorris.pool.data.hitbox.Hitbox;
import me.dnorris.pool.data.hitbox.implementation.location.HollowRectangleLocationHitbox;
import me.dnorris.pool.data.hitbox.implementation.location.RectangleLocationHitbox;
import me.dnorris.pool.data.location.Location;
import me.dnorris.pool.data.vector.Vector;
import me.dnorris.pool.data.vector.implementation.Vector2D;

import java.awt.*;
import java.util.List;

/**
 *
 * Represents a hollow rectangle on the {@link me.dnorris.pool.arena.GameArena}
 * Used for when entities need to be inside the {@link RectangleEntity} BUT it cannot escape (i.e.
 * need to interact with the walls but not the internals)
 * Thus requiring a different entity to handle the hitbox and collisions seperately.
 *
 * @author https://github.com/danorris709
 */
public class HollowRectangle extends CompoundEntity {

    /**
     *
     * Basic constructor
     * Protected for sub classes and the builder
     *
     * @param centerLocation Location of the Rectangle
     * @param colour Colour of the rectangle
     * @param motion initial motion of the rectangle
     * @param dimension dimensions of the rectangle
     * @param outsideWidth The thickness of the outside bars
     * @param immovable If the rectangle can be moved
     * @param interactable If the rectangle can be interacted with
     */
    protected HollowRectangle(Location centerLocation, Color colour, Vector motion, Dimension dimension, int outsideWidth, boolean immovable, boolean interactable) {
        super(centerLocation, colour, motion, new HollowRectangleLocationHitbox(centerLocation, dimension, outsideWidth, immovable, interactable),
                createEntities(centerLocation, colour, dimension, outsideWidth, immovable, interactable));
    }

    /**
     *
     * Used in the constructor when passing the entities to the parent class.
     * Creates the rectangles as hollow
     *
     * @param centerPoint Location of the Rectangle
     * @param colour Colour of the rectangle
     * @param dimension dimensions of the rectangle
     * @param outsideWidth The thickness of the outside bars
     * @param immovable If the rectangle can be moved
     * @param interactable If the rectangle can be interacted with
     * @return The {@link RectangleEntity}s created
     */
    private static RectangleEntity[] createEntities(Location centerPoint, Color colour, Dimension dimension, int outsideWidth, boolean immovable, boolean interactable) {
        List<RectangleEntity> entities = Lists.newArrayList();

        for (int i = 0; i < outsideWidth; i++) {
            Location nextPoint = centerPoint.subtract(i, i, 0);
            Hitbox hitbox = new RectangleLocationHitbox(nextPoint, new Dimension(dimension.width + 2 * i, dimension.height + 2 * i), immovable, interactable);

            entities.add(new RectangleEntity(nextPoint, colour, null, hitbox.getDimensions(), true, immovable, interactable));
        }

        return entities.toArray(new RectangleEntity[0]);
    }

    @Override
    public void collide(Entity other) {
        if(other.getHitbox().getPriority() > this.getHitbox().getPriority()) {
            other.collide(this);
            return;
        }

        Location collisionPoint = this.getHitbox().getLocation(other.getHitbox());
        boolean collidedWithX = this.hasCollidedWithXWalls(collisionPoint, other.getHitbox().getDimensions().getWidth());
        boolean collidedWithY = this.hasCollidedWithYWalls(collisionPoint, other.getHitbox().getDimensions().getHeight());

        if(collidedWithY && collidedWithX) {
            other.setMotion(other.getMotion().multiply(new Vector2D(-1, -1)));
        }else if(collidedWithY) {
            other.setMotion(other.getMotion().multiply(new Vector2D(1, -1)));
        }else {
            other.setMotion(other.getMotion().multiply(new Vector2D(-1, 1)));
        }
    }

    /**
     *
     * Determines if something has collided with the left or right hand walls and thus the Y velocity
     * needs to be inverted.
     *
     * @param location The location of the collision
     * @param height The height of the ball
     * @return If the entity collided with the left or right walls.
     */
    private boolean hasCollidedWithYWalls(Location location, double height) {
        if(location.getY() >= this.getLocation().getY() && location.getY() <= (this.getLocation().getY() + height)) {
            return true;
        }

        double farY = this.getLocation().getY() + this.getHitbox().getDimensions().getHeight();

        return location.getY() <= farY && location.getY() >= (farY - height);
    }

    /**
     *
     * Determines if something has collided with the upper or lower walls and thus the X velocity
     * needs to be inverted.
     *
     * @param location The location of the collision
     * @param width The width of the ball
     * @return If the entity collided with the upper or lower walls.
     */
    private boolean hasCollidedWithXWalls(Location location, double width) {
        if(location.getX() >= this.getLocation().getX() && location.getX() <= (this.getLocation().getX() + width)) {
            return true;
        }

        double farX = this.getLocation().getX() + this.getHitbox().getDimensions().getWidth();

        return location.getX() <= farX && location.getX() >= (farX - width);
    }

    /**
     *
     * Builder class for Hollow Rectangle as it has custom flags (i.e. thickness)
     *
     * @author https://github.com/danorris709
     */
    public static class Builder extends EntityBuilder {

        private int outsideWidth;

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

        public Builder setOutsideWidth(int outsideWidth) {
            this.outsideWidth = outsideWidth;
            return this;
        }

        @Override
        public Entity build() {
            return new HollowRectangle(
                    this.location,
                    this.colour,
                    this.motion,
                    this.dimension,
                    this.outsideWidth,
                    this.immovable,
                    this.interactable
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

            newBuilder.outsideWidth = this.outsideWidth;

            return newBuilder;
        }
    }
}
