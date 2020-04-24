package me.dnorris.pool.arena.arena;

import com.google.common.collect.Lists;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import me.dnorris.pool.arena.Entity;
import me.dnorris.pool.arena.GameArena;
import me.dnorris.pool.data.TriFunction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Map;

public class JFrameArena extends JFrame implements GameArena {

    private final List<Entity> entities = Lists.newArrayList();
    private final Map<Long, List<TriFunction<GameArena, JFrame, KeyEvent>>> keyHandlers = new Long2ObjectOpenHashMap();

    public JFrameArena(String title, Dimension dimensions, boolean resizable, Color backgroundColour) {
        this.setTitle(title);
        this.setSize(dimensions);
        this.setResizable(resizable);
        this.setBackground(backgroundColour);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public JFrameArena(Dimension dimensions) {
        this("Let's Play Pool!", dimensions, false, Color.BLACK);
    }

    @Override
    public List<Entity> getEntities() {
        return this.entities;
    }

    @Override
    public void addEntity(Entity entity) {
        this.entities.add(entity);

        // TODO: 24/04/2020 add to jframe
    }

    @Override
    public void removeEntity(Entity entity) {
        this.entities.add(entity);

        // TODO: 24/04/2020 remove from jframe
    }

    @Override
    public List<TriFunction<GameArena, JFrame, KeyEvent>> getHandlers(long key) {
        return null;
    }

    @Override
    public void addHandler(Class keyHandler) {

    }

    @Override
    public void removeHandler(Class keyHandler) {

    }

    @Override
    public void tick() {

    }
}
