package me.dnorris.pool.game;

import me.dnorris.pool.arena.Entity;
import me.dnorris.pool.arena.entity.EntityBuilder;
import me.dnorris.pool.arena.entity.EntityType;
import me.dnorris.pool.arena.entity.compound.shape.HollowRectangle;
import me.dnorris.pool.arena.entity.compound.shape.PercentageBar;
import me.dnorris.pool.arena.entity.shape.ArcShape;
import me.dnorris.pool.arena.entity.shape.LineEntity;
import me.dnorris.pool.arena.entity.shape.TextEntity;
import me.dnorris.pool.data.Colour;
import me.dnorris.pool.data.location.implementation.Location2D;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;

/**
 *
 * Class for keeping all important entities in relation to the Pool Game
 *
 * @author https://github.com/danorris709
 */
public class GameEntity {

    private final Entity BORDER = new HollowRectangle.Builder()
            .setLocation(new Location2D(100, 100))
            .setColour(Colour.BROWN)
            .setInteractable(true)
            .setImmovable(true)
            .setType(EntityType.HOLLOW_RECTANGLE)
            .setDimension(new Dimension(1000, 500))
            .setOutsideWidth(20)
            .build();

    private final ArcShape.Builder HOLE_TEMPLATE = new ArcShape.Builder()
            .setType(EntityType.ARC)
            .setColour(Color.BLACK)
            .setDimension(new Dimension(60, 60))
            .setHollow(false)
            .setImmovable(true)
            .setInteractable(true);

    private final Entity TOP_RIGHT_CORNER_HOLE = HOLE_TEMPLATE.clone()
            .setLocation(new Location2D(100, 100))
            .setStartAngle(0)
            .setArcAngle(-90)
            .build();

    private final Entity TOP_LEFT_CORNER_HOLE = HOLE_TEMPLATE.clone()
            .setLocation(new Location2D(100, 600))
            .setStartAngle(0)
            .setArcAngle(90)
            .build();

    private final Entity TOP_MIDDLE_HOLE = HOLE_TEMPLATE.clone()
            .setLocation(new Location2D(625, 100))
            .setDimension(new Dimension(50, 50))
            .setStartAngle(180)
            .setArcAngle(180)
            .build();

    private final Entity BOTTOM_MIDDLE_HOLE = HOLE_TEMPLATE.clone()
            .setLocation(new Location2D(625, 600))
            .setDimension(new Dimension(50, 50))
            .setStartAngle(0)
            .setArcAngle(180)
            .build();

    private final Entity BOTTOM_RIGHT_CORNER_HOLE = HOLE_TEMPLATE.clone()
            .setLocation(new Location2D(1100, 100))
            .setStartAngle(180)
            .setArcAngle(90)
            .build();

    private final Entity BOTTOM_LEFT_CORNER_HOLE = HOLE_TEMPLATE.clone()
            .setLocation(new Location2D(1100, 600))
            .setStartAngle(90)
            .setArcAngle(90)
            .build();

    private final LineEntity POINTER = new LineEntity.Builder()
            .setColour(Color.WHITE)
            .setType(EntityType.LINE)
            .setLocation(new Location2D(100 + (1000 / 4.0), 100 + 250))
            .setEndPoint(new Location2D(100 + (1000 / 4.0) + 750, 100 + 250))
            .build();

    private final TextEntity TURN_IDENTIFIER = new TextEntity.Builder()
            .setLocation(new Location2D(100, 50))
            .setBold(true)
            .setItalic(false)
            .setSize(20)
            .setColour(Color.WHITE)
            .setText("PLAYER 1's TURN")
            .setFont("SansSerif")
            .build();

    private Entity cloth;
    private Entity headString;
    private Entity blackBallSpot;
    private Entity cueBall;
    private Entity blackBall;
    private PercentageBar percentageBar;

    public Entity getBorder() {
        return BORDER;
    }

    public Entity getTopRightCornerHole() {
        return TOP_RIGHT_CORNER_HOLE;
    }

    public Entity getTopLeftCornerHole() {
        return TOP_LEFT_CORNER_HOLE;
    }

    public Entity getTopMiddleHole() {
        return TOP_MIDDLE_HOLE;
    }

    public Entity getBottomMiddleHole() {
        return BOTTOM_MIDDLE_HOLE;
    }

    public Entity getBottomRightCornerHole() {
        return BOTTOM_RIGHT_CORNER_HOLE;
    }

    public Entity getBottomLeftCornerHole() {
        return BOTTOM_LEFT_CORNER_HOLE;
    }

    public TextEntity getTurnIdentifier() {
        return TURN_IDENTIFIER;
    }

    public Entity getCloth() {
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

    public Entity getHeadString() {
        if(headString == null) {
            try {
                headString = new EntityBuilder()
                        .setLocation(new Location2D(100 + (1000 / 4.0), 100))
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

    public Entity getBlackBallSpot() {
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

    public Entity getCueBall() {
        if(cueBall == null) {
            try {
                cueBall = new EntityBuilder()
                        .setLocation(new Location2D(100 + (1000.0 / 4), 100 + 250))
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

    public Entity getBlackBall() {
        if(blackBall == null) {
            try {
                blackBall = new EntityBuilder()
                        .setLocation(new Location2D(850, 100 + 250))
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

    public PercentageBar getPercentageBar() {
        if(percentageBar == null) {
            percentageBar = new PercentageBar(new Location2D(1130, 100), Color.RED, null, new Dimension(25, 500), 500, true, false);
        }

        return percentageBar;
    }

    public LineEntity getPointer() {
        return POINTER;
    }
}
