package me.dnorris.pool.arena.arena.jframe;

import me.dnorris.pool.arena.Entity;
import me.dnorris.pool.arena.arena.AbstractGameArena;
import me.dnorris.pool.arena.arena.jframe.listener.KeyHandlerListener;
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
                for(Entity other : this.getEntities()) {
                    if(Objects.equals(other, entity) || other.getHitbox() == null || entity.getHitbox() == null) {
                        continue;
                    }

                    if(other.getHitbox().isColliding(entity.getHitbox())) {
                        // TODO: 02/05/2020 physics belong here
                    }
                }

                entity.paint(graphics);
                entity.tick();
            }
        }
    }
}
