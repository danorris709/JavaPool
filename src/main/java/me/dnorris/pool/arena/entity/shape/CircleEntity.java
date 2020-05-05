package me.dnorris.pool.arena.entity.shape;

import me.dnorris.pool.arena.Entity;
import me.dnorris.pool.arena.entity.AbstractEntity;
import me.dnorris.pool.data.hitbox.implementation.location.CircleLocationHitbox;
import me.dnorris.pool.data.location.Location;
import me.dnorris.pool.data.vector.Vector;
import me.dnorris.pool.data.vector.implementation.Vector2D;

import java.awt.*;

public class CircleEntity extends AbstractEntity {

    public CircleEntity(Location location, Color colour, Vector motion, Dimension dimension, boolean hollow, boolean immovable, boolean interactable) {
        super(colour, location, new CircleLocationHitbox(location, dimension, immovable, interactable), motion, hollow);
    }

    @Override
    public void collide(Entity other) {
        if (other.getHitbox().getPriority() > this.getHitbox().getPriority()) {
            other.collide(this);
            return;
        }

        Location firstLocation = this.getLocation();
        Location secondLocation = other.getLocation();
        Vector impactVector = this.calculateNormalizedImpact(firstLocation, secondLocation);
        double firstDotProduct = this.getMotion().dotProduct(impactVector);
        double secondDotProduct = other.getMotion().dotProduct(impactVector);

        Vector firstDeflect = new Vector2D(-impactVector.getX() * secondDotProduct, -impactVector.getY() * secondDotProduct);
        Vector secondDeflect = new Vector2D(impactVector.getX() * firstDotProduct, impactVector.getY() * firstDotProduct);

        Vector firstEndMotion = new Vector2D(this.getMotion().getX() + firstDeflect.getX() - secondDeflect.getX(), (this.getMotion().getY() + firstDeflect.getY() - secondDeflect.getY()));
        Vector secondEndMotion = new Vector2D(other.getMotion().getX() + secondDeflect.getX() - firstDeflect.getX(), (other.getMotion().getY() + secondDeflect.getY() - firstDeflect.getY()));

        double scalar = this.calculateScalar(this.getMotion(), other.getMotion(), firstEndMotion, secondEndMotion);

        this.setMotion(firstEndMotion.multiply(scalar));
        other.setMotion(secondEndMotion.multiply(scalar));
    }

    private Vector calculateNormalizedImpact(Location firstLocation, Location secondLocation) {
        return new Vector2D(secondLocation.getX() - firstLocation.getX(), secondLocation.getY() - firstLocation.getY()).normalize();
    }

    private double calculateScalar(Vector startFirst, Vector startSecond, Vector endFirst, Vector endSecond) {
        if((endFirst.getLength() + endSecond.getLength()) == 0) {
            return 1;
        }

        return (startFirst.getLength() + startSecond.getLength()) / (endFirst.getLength() + endSecond.getLength());
    }

    @Override
    public void paint(Graphics2D graphics) {
        graphics.setColor(this.getColour());

        int xPos = (int) (this.getLocation().getX() - (this.getHitbox().getDimensions().getWidth() / 2.0));
        int yPos = (int) (this.getLocation().getY() - (this.getHitbox().getDimensions().getHeight() / 2.0));

        if (this.hollow) {
            graphics.drawOval(xPos, yPos, (int) this.getHitbox().getDimensions().getWidth(), (int) this.getHitbox().getDimensions().getHeight());
        } else {
            graphics.fillOval(xPos, yPos, (int) this.getHitbox().getDimensions().getWidth(), (int) this.getHitbox().getDimensions().getHeight());
        }
    }

    @Override
    public void tick() {
        super.tick();
    }
}
