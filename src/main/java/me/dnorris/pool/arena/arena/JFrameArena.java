package me.dnorris.pool.arena.arena;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import me.dnorris.pool.arena.Entity;
import me.dnorris.pool.arena.GameArena;
import me.dnorris.pool.arena.key.KeyHandler;
import me.dnorris.pool.data.Pair;
import me.dnorris.pool.data.TriFunction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class JFrameArena extends JFrame implements GameArena {

    private final List<Entity> entities = Lists.newArrayList();
    private final Map<Class, List<Pair<Long, TriFunction<GameArena, JFrame, KeyEvent>>>> classFunctionCache = Maps.newHashMap();
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
        return this.keyHandlers.getOrDefault(key, Collections.emptyList());
    }

    @Override
    public void addHandler(Class keyHandler) {
        Object keyHandlerInstance = this.getClassInstance(keyHandler);

        for (Method declaredMethod : keyHandler.getDeclaredMethods()) {
            KeyHandler keyHandlerAnnotation = declaredMethod.getAnnotation(KeyHandler.class);

            if (keyHandlerAnnotation == null || declaredMethod.getParameterCount() != 3) {
                continue;
            }

            Parameter[] parameters = declaredMethod.getParameters();

            if (!GameArena.class.isAssignableFrom(parameters[0].getType())
                    || !JFrame.class.isAssignableFrom(parameters[1].getType())
                    || !KeyEvent.class.isAssignableFrom(parameters[2].getType())) {
                continue;
            }

            TriFunction<GameArena, JFrame, KeyEvent> function = this.createFunctionFromMethod(declaredMethod, keyHandlerInstance);

            this.keyHandlers.computeIfAbsent(keyHandlerAnnotation.keyCode(), ___ -> Lists.newArrayList()).add(function);
            this.classFunctionCache.computeIfAbsent(keyHandler, ___ -> Lists.newArrayList()).add(new Pair<>(keyHandlerAnnotation.keyCode(), function));
        }
    }

    private Object getClassInstance(Class clazz) {
        try {
            return clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }

    private TriFunction<GameArena, JFrame, KeyEvent> createFunctionFromMethod(Method method, Object instance) {
        return (gameArena, jFrame, keyEvent) -> {
            try {
                method.invoke(instance, gameArena, jFrame, keyEvent);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        };
    }

    @Override
    public void removeHandler(Class keyHandler) {
        for (Pair<Long, TriFunction<GameArena, JFrame, KeyEvent>> pairs : this.classFunctionCache.getOrDefault(keyHandler, Collections.emptyList())) {
            this.keyHandlers.getOrDefault(pairs.getFirst(), Collections.emptyList()).remove(pairs.getSecond());
        }

        this.classFunctionCache.remove(keyHandler);
    }

    @Override
    public void tick() {

    }
}
