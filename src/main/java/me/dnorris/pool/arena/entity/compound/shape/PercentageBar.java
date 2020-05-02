package me.dnorris.pool.arena.entity.compound.shape;

import com.google.common.collect.Lists;
import me.dnorris.pool.arena.Entity;
import me.dnorris.pool.arena.entity.EntityBuilder;
import me.dnorris.pool.arena.entity.EntityType;
import me.dnorris.pool.arena.entity.compound.CompoundEntity;
import me.dnorris.pool.data.hitbox.implementation.location.EmptyHitbox;
import me.dnorris.pool.data.location.Location;
import me.dnorris.pool.data.vector.Vector;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class PercentageBar extends CompoundEntity {

    private Entity emptyBar;
    private Entity filledBar;

    private int filledPercentage;
    private int maxHeight;

    public PercentageBar(Location centerLocation, Color colour, Vector motion, Dimension dimension, int maxHeight, boolean immovable, boolean interactable) {
        super(centerLocation, colour, motion, new EmptyHitbox(centerLocation, immovable), createEntities(centerLocation, colour, dimension, maxHeight, immovable, interactable));

        this.maxHeight = maxHeight + 2;
        this.emptyBar = this.entities.get(0);
        this.filledBar = this.entities.get(1);
    }

    private static Entity[] createEntities(Location centerPoint, Color colour, Dimension dimension, int maxHeight, boolean immovable, boolean interactable) {
        List<Entity> entities = Lists.newArrayList();

        entities.add(createEmptyPowerBar(centerPoint, colour, dimension, immovable, interactable));
        entities.add(createFullPowerBar(centerPoint, colour, maxHeight, immovable, interactable));

        return entities.toArray(new Entity[0]);
    }

    public static Entity createEmptyPowerBar(Location centerLocation, Color colour, Dimension dimension, boolean interactable, boolean immovable) {
        try {
            return new EntityBuilder()
                    .setLocation(centerLocation)
                    .setColour(colour)
                    .setDimension(dimension)
                    .setInteractable(interactable)
                    .setImmovable(immovable)
                    .setHollow(true)
                    .setType(EntityType.RECTANGLE)
                    .build();
        } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static Entity createFullPowerBar(Location centerLocation, Color colour, int maxHeight, boolean interactable, boolean immovable) {
        try {
            return new EntityBuilder()
                    .setLocation(centerLocation.add(0, maxHeight, 0))
                    .setColour(colour)
                    .setDimension(new Dimension(25, 0))
                    .setInteractable(interactable)
                    .setImmovable(immovable)
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

    public double getPercentage() {
        return this.filledPercentage / 100.00;
    }

    public void addPercentage(int delta) {
        if((this.filledPercentage + delta) > 100) {
            delta = 100 - this.filledPercentage;
        }

        this.filledPercentage += delta;

        int pixelPercent = (int) ((delta / 100.0) * this.maxHeight);

        this.updateFilledPercentage(pixelPercent);
        this.updateEmptyPercentage(pixelPercent);
    }

    public void takePercentage(int delta) {
        if((this.filledPercentage - delta) < 0) {
            delta = this.filledPercentage;
        }

        this.filledPercentage -= delta;

        int pixelPercent = (int) ((delta / 100.0) * this.maxHeight);

        this.updateFilledPercentage(-pixelPercent);
        this.updateEmptyPercentage(-pixelPercent);
    }

    private void updateFilledPercentage(int change) {
        Dimension fullDimensions = this.filledBar.getHitbox().getDimensions();

        this.filledBar.setLocation(this.filledBar.getLocation().subtract(0, change, 0));
        fullDimensions.setSize(
                fullDimensions.getWidth(),
                fullDimensions.getHeight() + change
        );
    }

    private void updateEmptyPercentage(int change) {
        Dimension emptyDimensions = this.emptyBar.getHitbox().getDimensions();

        this.emptyBar.getHitbox().getDimensions().setSize(
                emptyDimensions.getWidth(),
                emptyDimensions.getHeight() - change
        );
    }
}
