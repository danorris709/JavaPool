package me.dnorris.pool.arena.entity.shape;

import me.dnorris.pool.arena.Entity;
import me.dnorris.pool.arena.entity.AbstractEntity;
import me.dnorris.pool.arena.entity.EntityBuilder;
import me.dnorris.pool.arena.entity.EntityType;
import me.dnorris.pool.data.hitbox.Hitbox;
import me.dnorris.pool.data.hitbox.implementation.location.EmptyHitbox;
import me.dnorris.pool.data.location.Location;
import me.dnorris.pool.data.vector.Vector;

import java.awt.*;

public class LineEntity extends AbstractEntity {

    private Location endPoint;

    public LineEntity(Location location, Location endPoint, Color colour, Vector motion, boolean immovable) {
        super(colour, location, new EmptyHitbox(location, immovable), motion, false);

        this.endPoint = endPoint;
    }

    public Location getEndPoint() {
        return this.endPoint;
    }

    public void setEndPoint(Location endPoint) {
        this.endPoint = endPoint;
        this.setDirty(true);
    }

    @Override
    public void paint(Graphics2D graphics) {
        if (!this.isDirty()) {
            return;
        }

        graphics.setColor(this.getColour());
        graphics.drawLine(this.getLocation().getX(), this.getLocation().getY(), this.getEndPoint().getX(), this.getEndPoint().getY());

        this.setDirty(false);
    }

    @Override
    public void tick() {
        super.tick();
    }

    public static class Builder extends EntityBuilder {

        private Location endPoint;

        public Builder() {
            super();
        }

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
            throw new UnsupportedOperationException("Lines cannot be hollow");
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
            throw new UnsupportedOperationException("Lines cannot be interactable");
        }

        @Override
        public Builder setDimension(Dimension dimension) {
            throw new UnsupportedOperationException("Lines cannot have dimensions");
        }

        public Builder setEndPoint(Location endPoint) {
            this.endPoint = endPoint;
            return this;
        }

        @Override
        public LineEntity build() {
            return new LineEntity(
                    this.location,
                    this.endPoint,
                    this.colour,
                    this.motion,
                    this.immovable
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

            if (this.endPoint != null) {
                newBuilder.endPoint = endPoint.clone();
            }

            newBuilder.colour = this.colour;
            newBuilder.motion = this.motion;
            newBuilder.hollow = this.hollow;
            newBuilder.immovable = this.immovable;
            newBuilder.interactable = this.interactable;
            newBuilder.type = this.type;

            return newBuilder;
        }
    }
}