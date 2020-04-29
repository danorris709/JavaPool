package me.dnorris.pool.arena.entity.compound.shape;

import com.google.common.collect.Lists;
import me.dnorris.pool.arena.Entity;
import me.dnorris.pool.arena.entity.EntityBuilder;
import me.dnorris.pool.arena.entity.EntityType;
import me.dnorris.pool.arena.entity.compound.CompoundEntity;
import me.dnorris.pool.arena.entity.shape.ArcShape;
import me.dnorris.pool.arena.entity.shape.RectangleEntity;
import me.dnorris.pool.data.hitbox.Hitbox;
import me.dnorris.pool.data.hitbox.implementation.SquareLocationHitbox;
import me.dnorris.pool.data.location.Location;
import me.dnorris.pool.data.vector.Vector;

import java.awt.*;
import java.util.List;

public class HollowRectangle extends CompoundEntity {

    public HollowRectangle(Location centerLocation, Color colour, Vector motion, Dimension dimension, int outsideWidth, boolean immovable, boolean interactable) {
        super(centerLocation, colour, motion, null, createEntities(centerLocation, colour, dimension, outsideWidth, immovable, interactable));
    }

    private static RectangleEntity[] createEntities(Location centerPoint, Color colour, Dimension dimension, int outsideWidth, boolean immovable, boolean interactable) {
        List<RectangleEntity> entities = Lists.newArrayList();

        for(int i = 0; i < outsideWidth; i++) {
            Location nextPoint = centerPoint.subtract(i, i, 0);
            Hitbox hitbox = new SquareLocationHitbox(nextPoint, new Dimension(dimension.width + 2*i, dimension.height + 2*i), immovable, interactable);

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
        public EntityBuilder clone() {
            Builder builder = (Builder) super.clone();

            builder.outsideWidth = this.outsideWidth;

            return builder;
        }
    }
}
