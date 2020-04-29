package me.dnorris.pool.arena.entity.compound.shape;

import com.google.common.collect.Lists;
import me.dnorris.pool.arena.Entity;
import me.dnorris.pool.arena.entity.EntityBuilder;
import me.dnorris.pool.arena.entity.EntityType;
import me.dnorris.pool.arena.entity.compound.CompoundEntity;
import me.dnorris.pool.data.location.Location;
import me.dnorris.pool.data.location.implementation.Location2D;
import me.dnorris.pool.data.vector.Vector;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class PercentageBar extends CompoundEntity {

    private int filledPercentage;
    private Entity emptyBar;
    private Entity filledBar;

    public PercentageBar(Location centerLocation, Color colour, Vector motion, Dimension dimension, int outsideWidth, boolean immovable, boolean interactable) {
        super(centerLocation, colour, motion, null, createEntities(centerLocation, colour, dimension, outsideWidth, immovable, interactable));

        this.emptyBar = this.entities.get(0);
        this.filledBar = this.entities.get(1);
    }

    private static Entity[] createEntities(Location centerPoint, Color colour, Dimension dimension, int outsideWidth, boolean immovable, boolean interactable) {
        List<Entity> entities = Lists.newArrayList();

        entities.add(createEmptyPowerBar());
        entities.add(createFullPowerBar());

        return entities.toArray(new Entity[0]);
    }

    public static Entity createEmptyPowerBar() {
        try {
            return new EntityBuilder()
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

        return null;
    }

    private static Entity createFullPowerBar() {
        try {
            return new EntityBuilder()
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

        return null;
    }

    public int getFilledPercentage() {
        return this.filledPercentage;
    }

    public void addPercentage(int delta) {
        this.filledPercentage += delta;

        this.updateFilledPercentage(delta);
        this.updateEmptyPercentage(-delta);
    }

    public void takePercentage(int delta) {
        this.filledPercentage -= delta;

        this.updateFilledPercentage(-delta);
        this.updateEmptyPercentage(delta);
    }

    private void updateFilledPercentage(int change) {
        Dimension fullDimensions = this.filledBar.getHitbox().getDimensions();

        this.filledBar.setLocation(this.filledBar.getLocation().subtract(0, change, 0));
        fullDimensions.setSize(
                fullDimensions.getWidth(),
                fullDimensions.getHeight() + change
        );
        this.filledBar.setDirty(true);
    }

    private void updateEmptyPercentage(int change) {
        Dimension emptyDimensions = this.emptyBar.getHitbox().getDimensions();

        this.emptyBar.getHitbox().getDimensions().setSize(
                emptyDimensions.getWidth(),
                emptyDimensions.getHeight() - 10
        );
        this.emptyBar.setDirty(true);
    }
}
