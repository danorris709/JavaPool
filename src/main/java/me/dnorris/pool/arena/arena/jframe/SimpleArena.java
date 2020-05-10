package me.dnorris.pool.arena.arena.jframe;

import me.dnorris.pool.arena.Entity;
import me.dnorris.pool.arena.arena.AbstractGameArena;
import me.dnorris.pool.arena.arena.jframe.listener.KeyHandlerListener;
import me.dnorris.pool.arena.event.EventFactory;
import me.dnorris.pool.arena.event.event.EntityCollisionEvent;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 *
 * Simple implementation of the {@link me.dnorris.pool.arena.GameArena} interface
 * using a concurrent {@link Thread} for ticking and painting the entities, and using a
 * basic lock object to prevent {@link java.util.ConcurrentModificationException}
 *
 * @author https://github.com/danorris709
 */
public class SimpleArena extends AbstractGameArena {

    private final Object lock = new Object();

    /**
     *
     * Basic constructor
     *
     * @param dimensions Dimensions of the {@link me.dnorris.pool.arena.GameArena}
     */
    public SimpleArena(Dimension dimensions) {
        super(dimensions);

        JFrame jFrame = new GameFrame(this);

        KeyHandlerListener listener = new KeyHandlerListener(this);

        jFrame.addKeyListener(listener);
        jFrame.addMouseListener(listener);
        jFrame.addMouseMotionListener(listener);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        new Thread(() -> {
            while(true) {
                jFrame.repaint();

                try {
                    Thread.sleep(10);
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
                EntityCollisionEvent collisionEvent = EventFactory.callCollisionEvent(entity, other);

                if(!collisionEvent.isCancelled()) {
                    entity.collide(other);
                }
            }
        }
    }
}
