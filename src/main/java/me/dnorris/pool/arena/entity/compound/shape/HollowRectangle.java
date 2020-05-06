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

public class HollowRectangle extends CompoundEntity {

    public HollowRectangle(Location centerLocation, Color colour, Vector motion, Dimension dimension, int outsideWidth, boolean immovable, boolean interactable) {
        super(centerLocation, colour, motion, new HollowRectangleLocationHitbox(centerLocation, dimension, outsideWidth, immovable, interactable),
                createEntities(centerLocation, colour, dimension, outsideWidth, immovable, interactable));
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

    private boolean hasCollidedWithYWalls(Location location, double height) {
        if(location.getY() >= this.getLocation().getY() && location.getY() <= (this.getLocation().getX() + height)) {
            return true;
        }

        double farX = this.getLocation().getY() + this.getHitbox().getDimensions().getHeight();

        return location.getX() <= farX && location.getX() >= (farX - height);
    }

    private boolean hasCollidedWithXWalls(Location location, double width) {
        if(location.getX() >= this.getLocation().getX() && location.getX() <= (this.getLocation().getX() + width)) {
            return true;
        }

        double farX = this.getLocation().getX() + this.getHitbox().getDimensions().getWidth();

        return location.getX() <= farX && location.getX() >= (farX - width);
    }

    private static RectangleEntity[] createEntities(Location centerPoint, Color colour, Dimension dimension, int outsideWidth, boolean immovable, boolean interactable) {
        List<RectangleEntity> entities = Lists.newArrayList();

        for(int i = 0; i < outsideWidth; i++) {
            Location nextPoint = centerPoint.subtract(i, i, 0);
            Hitbox hitbox = new RectangleLocationHitbox(nextPoint, new Dimension(dimension.width + 2*i, dimension.height + 2*i), immovable, interactable);

            entities.add(new RectangleEntity(nextPoint, colour, null, hitbox.getDimensions(), true, immovable, interactable));
        }

        return entities.toArray(new RectangleEntity[0]);
    }

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
