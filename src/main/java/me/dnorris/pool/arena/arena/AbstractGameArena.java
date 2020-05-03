package me.dnorris.pool.arena.arena;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import me.dnorris.pool.arena.Entity;
import me.dnorris.pool.arena.GameArena;
import me.dnorris.pool.arena.GameFunction;
import me.dnorris.pool.arena.key.KeyHandler;
import me.dnorris.pool.data.BiFunction;
import me.dnorris.pool.data.Pair;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public abstract class AbstractGameArena implements GameArena {

    private final List<Entity> entities = Lists.newArrayList();
    private final Map<Class<?>, List<Pair<Long, BiFunction<GameArena, KeyEvent>>>> classFunctionCache = Maps.newHashMap();
    private final Map<Long, List<GameFunction>> keyHandlers = new Long2ObjectOpenHashMap<>();

    private Dimension dimensions;

    public AbstractGameArena(Dimension dimensions) {
        this.dimensions = dimensions;
    }

    @Override
    public Dimension getDimensions() {
        return this.dimensions;
    }

    @Override
    public void setDimensions(Dimension dimensions) {
        this.dimensions = dimensions;
    }

    @Override
    public List<Entity> getEntities() {
        return this.entities;
    }

    @Override
    public void addEntity(Entity entity) {
        this.entities.add(entity);
        entity.setArena(this);
    }

    @Override
    public void removeEntity(Entity entity) {
        this.entities.add(entity);
        entity.setArena(null);
    }

    @Override
    public List<GameFunction> getHandlers(long key) {
        return this.keyHandlers.getOrDefault(key, Collections.emptyList());
    }

    @Override
    public void addHandler(Class<?> keyHandler) {
        Object keyHandlerInstance = this.getClassInstance(keyHandler);

        for (Method declaredMethod : keyHandler.getDeclaredMethods()) {
            KeyHandler keyHandlerAnnotation = declaredMethod.getAnnotation(KeyHandler.class);

            if (keyHandlerAnnotation == null || declaredMethod.getParameterCount() != 2) {
                continue;
            }

            Parameter[] parameters = declaredMethod.getParameters();

            if (!GameArena.class.isAssignableFrom(parameters[0].getType())
                    || !KeyEvent.class.isAssignableFrom(parameters[1].getType())) {
                continue;
            }

            BiFunction<GameArena, KeyEvent> function = this.createFunctionFromMethod(declaredMethod, keyHandlerInstance);

            for (long keyCode : keyHandlerAnnotation.keyCode()) {
                this.keyHandlers.computeIfAbsent(keyCode, ___ -> Lists.newArrayList()).add(new GameFunction(keyHandlerAnnotation.getType(), function));
                this.classFunctionCache.computeIfAbsent(keyHandler, ___ -> Lists.newArrayList()).add(new Pair<>(keyCode, function));
            }
        }
    }

    private Object getClassInstance(Class<?> clazz) {
        try {
            return clazz.getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }

    private BiFunction<GameArena, KeyEvent> createFunctionFromMethod(Method method, Object instance) {
        return (gameArena, keyEvent) -> {
            try {
                method.invoke(instance, gameArena, keyEvent);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        };
    }

    @Override
    public void removeHandler(Class<?> keyHandler) {
        for (Pair<Long, BiFunction<GameArena, KeyEvent>> pairs : this.classFunctionCache.getOrDefault(keyHandler, Collections.emptyList())) {
            this.keyHandlers.getOrDefault(pairs.getFirst(), Collections.emptyList()).removeIf(gameFunction -> Objects.equals(gameFunction.getFunction(), pairs.getSecond()));
        }

        this.classFunctionCache.remove(keyHandler);
    }
}
