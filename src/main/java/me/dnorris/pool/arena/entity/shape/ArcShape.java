package me.dnorris.pool.arena.entity.shape;

import me.dnorris.pool.arena.Entity;
import me.dnorris.pool.arena.entity.AbstractEntity;
import me.dnorris.pool.arena.entity.EntityBuilder;
import me.dnorris.pool.arena.entity.EntityType;
import me.dnorris.pool.data.hitbox.Hitbox;
import me.dnorris.pool.data.hitbox.implementation.SquareLocationHitbox;
import me.dnorris.pool.data.location.Location;
import me.dnorris.pool.data.vector.Vector;

import java.awt.*;

public class ArcShape extends AbstractEntity {

    private int startAngle;
    private int arcAngle;

    public ArcShape(Location location, Color colour, Vector motion, Dimension dimension, boolean hollow, boolean immovable, boolean interactable, int startAngle, int arcAngle) {
        super(colour, location, new SquareLocationHitbox(location, dimension, immovable, interactable), motion, hollow);

        this.startAngle = startAngle;
        this.arcAngle = arcAngle;
    }

    @Override
    public void paint(Graphics2D graphics) {
        graphics.setColor(this.getColour());

        if (this.hollow)
            graphics.drawArc(this.getLocation().getX(), this.getLocation().getY(), (int) this.getHitbox().getDimensions().getWidth(), (int) this.getHitbox().getDimensions().getHeight(),
                    this.startAngle, this.arcAngle);
        else {
            graphics.fillArc(this.getLocation().getX(), this.getLocation().getY(), (int) this.getHitbox().getDimensions().getWidth(), (int) this.getHitbox().getDimensions().getHeight(),
                    this.startAngle, this.arcAngle);
        }
    }

    @Override
    public void tick() {
        super.tick();
    }

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
    }
}
