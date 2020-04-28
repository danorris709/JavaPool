package me.dnorris.pool.game;

import me.dnorris.pool.arena.Entity;
import me.dnorris.pool.arena.entity.EntityBuilder;
import me.dnorris.pool.arena.entity.compound.shape.HollowRectangle;
import me.dnorris.pool.data.Colour;
import me.dnorris.pool.data.location.implementation.Location2D;
import me.dnorris.pool.data.vector.implementation.Vector2D;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;

public class GameEntity {

    private static Entity border;
    private static Entity cloth;

    public static Entity getBorder() {
        if(border == null) {
            try {
                border = new HollowRectangle.Builder()
                        .setLocation(new Location2D(100, 100))
                        .setColour(Colour.BROWN)
                        .setInteractable(true)
                        .setImmovable(true)
                        .setDimension(new Dimension(500, 250))
                        .build();
            } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
                e.printStackTrace();
            }
        }

        return border;
    }

    public static Entity getCloth() {
        if(cloth == null) {
            try {
                cloth = new EntityBuilder()
                        .setLocation(new Location2D(100, 100))
                        .setColour(Color.GREEN)
                        .setDimension(new Dimension(500, 250))
                        .setImmovable(true)
                        .setInteractable(false)
                        .setHollow(false)
                        .build();
            } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
                e.printStackTrace();
            }
        }

        return cloth;
    }

}
