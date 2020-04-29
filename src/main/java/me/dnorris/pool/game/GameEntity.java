package me.dnorris.pool.game;

import me.dnorris.pool.arena.Entity;
import me.dnorris.pool.arena.entity.EntityBuilder;
import me.dnorris.pool.arena.entity.EntityType;
import me.dnorris.pool.arena.entity.compound.shape.HollowRectangle;
import me.dnorris.pool.data.Colour;
import me.dnorris.pool.data.location.implementation.Location2D;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;

public class GameEntity {

    private static Entity BORDER = new HollowRectangle.Builder()
            .setLocation(new Location2D(100, 100))
            .setColour(Colour.BROWN)
            .setInteractable(true)
            .setImmovable(true)
            .setType(EntityType.HOLLOW_RECTANGLE)
            .setDimension(new Dimension(1000, 500))
            .setOutsideWidth(20)
            .build();

    private static Entity cloth;

    public static Entity getBorder() {
        return BORDER;
    }

    public static Entity getCloth() {
        if(cloth == null) {
            try {
                cloth = new EntityBuilder()
                        .setLocation(new Location2D(100, 100))
                        .setColour(Colour.DARK_GREEN)
                        .setDimension(new Dimension(1000, 500))
                        .setImmovable(true)
                        .setInteractable(false)
                        .setHollow(false)
                        .setType(EntityType.RECTANGLE)
                        .build();
            } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
                e.printStackTrace();
            }
        }

        return cloth;
    }

}
