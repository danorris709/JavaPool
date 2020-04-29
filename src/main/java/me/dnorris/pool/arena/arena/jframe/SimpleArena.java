package me.dnorris.pool.arena.arena.jframe;

import me.dnorris.pool.arena.Entity;
import me.dnorris.pool.arena.arena.AbstractGameArena;
import me.dnorris.pool.arena.arena.jframe.listener.KeyHandlerListener;

import javax.swing.*;
import java.awt.*;

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
}
