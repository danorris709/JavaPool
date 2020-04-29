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

    private static final ArcShape.Builder HOLE_TEMPLATE = new ArcShape.Builder()
            .setType(EntityType.ARC)
            .setColour(Color.BLACK)
            .setDimension(new Dimension(60, 60))
            .setHollow(false)
            .setImmovable(true)
            .setInteractable(false);

    private static final Entity TOP_RIGHT_CORNER_HOLE = HOLE_TEMPLATE.clone()
            .setLocation(new Location2D(70, 70))
            .setStartAngle(0)
            .setArcAngle(-90)
            .build();

    private static final Entity TOP_LEFT_CORNER_HOLE = HOLE_TEMPLATE.clone()
            .setLocation(new Location2D(70, 570))
            .setStartAngle(0)
            .setArcAngle(90)
            .build();

    private static final Entity TOP_MIDDLE_HOLE = HOLE_TEMPLATE.clone()
            .setLocation(new Location2D(100 + 500, 75))
            .setDimension(new Dimension(50, 50))
            .setStartAngle(180)
            .setArcAngle(180)
            .build();

    private static final Entity BOTTOM_MIDDLE_HOLE = HOLE_TEMPLATE.clone()
            .setLocation(new Location2D(100 + 500, 575))
            .setDimension(new Dimension(50, 50))
            .setStartAngle(0)
            .setArcAngle(180)
            .build();

    private static final Entity BOTTOM_RIGHT_CORNER_HOLE = HOLE_TEMPLATE.clone()
            .setLocation(new Location2D(70 + 1000, 70))
            .setStartAngle(180)
            .setArcAngle(90)
            .build();

    private static final Entity BOTTOM_LEFT_CORNER_HOLE = HOLE_TEMPLATE.clone()
            .setLocation(new Location2D(70 + 1000, 570))
            .setStartAngle(90)
            .setArcAngle(90)
            .build();

    private static Entity cloth;
    private static Entity headString;
    private static Entity blackBallSpot;
    private static Entity cueBall;
    private static Entity blackBall;
    private static Entity emptyPowerBar;
    private static Entity fullPowerBar;

    public static Entity getBorder() {
        return BORDER;
    }

    public static Entity getTopRightCornerHole() {
        return TOP_RIGHT_CORNER_HOLE;
    }

    public static Entity getTopLeftCornerHole() {
        return TOP_LEFT_CORNER_HOLE;
    }

    public static Entity getTopMiddleHole() {
        return TOP_MIDDLE_HOLE;
    }

    public static Entity getBottomMiddleHole() {
        return BOTTOM_MIDDLE_HOLE;
    }

    public static Entity getBottomRightCornerHole() {
        return BOTTOM_RIGHT_CORNER_HOLE;
    }

    public static Entity getBottomLeftCornerHole() {
        return BOTTOM_LEFT_CORNER_HOLE;
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

    public static Entity getBlackBallSpot() {
        if(blackBallSpot == null) {
            try {
                blackBallSpot = new EntityBuilder()
                        .setLocation(new Location2D(850, 100 + 250))
                        .setColour(Color.WHITE)
                        .setDimension(new Dimension(4, 4))
                        .setInteractable(false)
                        .setImmovable(true)
                        .setHollow(false)
                        .setType(EntityType.CIRCLE)
                        .build();
            } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
                e.printStackTrace();
            }
        }

        return blackBallSpot;
    }

    public static Entity getCueBall() {
        if(cueBall == null) {
            try {
                cueBall = new EntityBuilder()
                        .setLocation(new Location2D(100 + (1000 / 4) - 10, 100 + 250 - 10))
                        .setColour(Color.WHITE)
                        .setDimension(new Dimension(20, 20))
                        .setInteractable(true)
                        .setImmovable(false)
                        .setHollow(false)
                        .setType(EntityType.CIRCLE)
                        .build();
            } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
                e.printStackTrace();
            }
        }

        return cueBall;
    }

    public static Entity getBlackBall() {
        if(blackBall == null) {
            try {
                blackBall = new EntityBuilder()
                        .setLocation(new Location2D(850 - 10, 100 + 250 - 10))
                        .setColour(Color.BLACK)
                        .setDimension(new Dimension(20, 20))
                        .setInteractable(true)
                        .setImmovable(false)
                        .setHollow(false)
                        .setType(EntityType.CIRCLE)
                        .build();
            } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
                e.printStackTrace();
            }
        }

        return blackBall;
    }

    public static Entity getEmptyPowerBar() {
        if(emptyPowerBar == null) {
            try {
                emptyPowerBar = new EntityBuilder()
                        .setLocation(new Location2D(1120, 81))
                        .setColour(Color.RED)
                        .setDimension(new Dimension(25, 538))
                        .setInteractable(false)
                        .setImmovable(true)
                        .setHollow(true)
                        .setType(EntityType.RECTANGLE)
                        .build();
            } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
                e.printStackTrace();
            }
        }

        return emptyPowerBar;
    }

    public static Entity getFullPowerBar() {
        if(fullPowerBar == null) {
            try {
                fullPowerBar = new EntityBuilder()
                        .setLocation(new Location2D(1120, 619))
                        .setColour(Color.RED)
                        .setDimension(new Dimension(25, 0))
                        .setInteractable(false)
                        .setImmovable(true)
                        .setHollow(false)
                        .setType(EntityType.RECTANGLE)
                        .build();
            } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
                e.printStackTrace();
            }
        }

        return fullPowerBar;
    }
}
