package me.dnorris.pool.arena.arena.jframe;

import me.dnorris.pool.arena.arena.AbstractGameArena;

import java.awt.*;

public class SimpleArena extends AbstractGameArena {

    public SimpleArena(Dimension dimensions) {
        super(dimensions);

        new GameFrame(this);
    }

    @Override
    public void tick(Graphics2D graphics) {
        // TODO: 24/04/2020
    }
}
