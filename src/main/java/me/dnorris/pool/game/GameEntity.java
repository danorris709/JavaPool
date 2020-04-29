package me.dnorris.pool.game;

import me.dnorris.pool.arena.Entity;
import me.dnorris.pool.arena.entity.EntityBuilder;
import me.dnorris.pool.arena.entity.EntityType;
import me.dnorris.pool.arena.entity.compound.shape.HollowRectangle;
import me.dnorris.pool.arena.entity.shape.ArcShape;
import me.dnorris.pool.data.Colour;
import me.dnorris.pool.data.location.implementation.Location2D;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;

public class GameEntity {

    private static final Entity BORDER = new HollowRectangle.Builder()
            .setLocation(new Location2D(100, 100))
            .setColour(Colour.BROWN)
            .setInteractable(true)
            .setImmovable(true)
            .setType(EntityType.HOLLOW_RECTANGLE)
            .setDimension(new Dimension(1000, 500))
            .setOutsideWidth(20)
            .build();

    private static final Entity TOP_CORNER_HOLE = new ArcShape.Builder()
            .setType(EntityType.ARC)
            .setColour(Color.BLACK)
            .setDimension(new Dimension(60, 60))
            .setHollow(false)
            .setImmovable(true)
            .setInteractable(false)
            .setLocation(new Location2D(70, 70))
            .setStartAngle(0)
            .setArcAngle(-90)
            .build();

    private static Entity cloth;
    private static Entity headString;

    public static Entity getBorder() {
        return BORDER;
    }

    public static Entity getTopCornerHole() {
        return TOP_CORNER_HOLE;
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

    public static Entity getHeadString() {
        if(headString == null) {
            try {
                headString = new EntityBuilder()
                        .setLocation(new Location2D(100 + (1000 / 4), 100))
                        .setColour(Color.WHITE)
                        .setDimension(new Dimension(1, 500))
                        .setInteractable(false)
                        .setImmovable(true)
                        .setHollow(true)
                        .setType(EntityType.RECTANGLE)
                        .build();
            } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
                e.printStackTrace();
            }
        }

        return headString;
    }
}
