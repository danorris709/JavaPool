package me.dnorris.pool.arena.arena.jframe;

import me.dnorris.pool.arena.Entity;
import me.dnorris.pool.arena.arena.AbstractGameArena;
import me.dnorris.pool.arena.arena.jframe.listener.KeyHandlerListener;
import me.dnorris.pool.data.location.Location;
import me.dnorris.pool.data.vector.Vector;
import me.dnorris.pool.data.vector.implementation.Vector2D;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class SimpleArena extends AbstractGameArena {

    private final Object lock = new Object();

    public SimpleArena(Dimension dimensions) {
        super(dimensions);

        JFrame jFrame = new GameFrame(this);

        jFrame.addKeyListener(new KeyHandlerListener(this));

        new Thread(() -> {
            while(true) {
                jFrame.repaint();

                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void addEntity(Entity entity) {
        synchronized (this.lock) {
            super.addEntity(entity);
        }
    }

    @Override
    public void removeEntity(Entity entity) {
        synchronized (this.lock) {
            super.removeEntity(entity);
        }
    }

    @Override
    public void tick(Graphics2D graphics) {
        synchronized (this.lock) {
            for (Entity entity : this.getEntities()) {
                entity.paint(graphics);
                entity.tick();
            }
        }
    }

    @Override
    public void doPhysics(Entity entity) {
        for(Entity other : this.getEntities()) {
            if(Objects.equals(other, entity) || other.getHitbox() == null || entity.getHitbox() == null) {
                continue;
            }

            if(other.getHitbox().isColliding(entity.getHitbox())) {
                this.handlePhysics(entity, other);
            }
        }
    }

    private void handlePhysics(Entity first, Entity second) {
        Location firstLocation = first.getLocation();
        Location secondLocation = second.getLocation();

        Vector impactVector = this.calculateNormalizedImpact(firstLocation, secondLocation);
        double firstDotProduct = first.getMotion().dotProduct(impactVector);
        double secondDotProduct = second.getMotion().dotProduct(impactVector);

        Vector firstDeflect = new Vector2D(-impactVector.getX() * secondDotProduct, -impactVector.getY() * secondDotProduct);
        Vector secondDeflect = new Vector2D(impactVector.getX() * firstDotProduct, impactVector.getY() * firstDotProduct);

        Vector firstEndMotion = new Vector2D(first.getMotion().getX() + firstDeflect.getX() - secondDeflect.getX(), first.getMotion().getY() + firstDeflect.getY() - secondDeflect.getY());
        Vector secondEndMotion = new Vector2D(second.getMotion().getX() + secondDeflect.getX() - firstDeflect.getX(), second.getMotion().getY() + secondDeflect.getY() - firstDeflect.getY());

        double scalar = this.calculateScalar(first.getMotion(), second.getMotion(), firstEndMotion, secondEndMotion);

        first.setMotion(firstEndMotion.multiply(scalar));
        second.setMotion(secondEndMotion.multiply(scalar));
    }

    private Vector calculateNormalizedImpact(Location firstLocation, Location secondLocation) {
        return new Vector2D(secondLocation.getX() - firstLocation.getX(), secondLocation.getY() - firstLocation.getY()).normalize();
    }

    private double calculateScalar(Vector startFirst, Vector startSecond, Vector endFirst, Vector endSecond) {
        return (startFirst.getLength() + startSecond.getLength()) / (endFirst.getLength() + endSecond.getLength());
    }
}
