package me.dnorris.arena.entity.compound.shape;

import com.google.common.collect.Lists;
import me.dnorris.arena.Entity;
import me.dnorris.arena.GameArena;
import me.dnorris.arena.entity.EntityBuilder;
import me.dnorris.arena.entity.EntityType;
import me.dnorris.arena.entity.compound.CompoundEntity;
import me.dnorris.data.hitbox.implementation.location.EmptyHitbox;
import me.dnorris.data.location.Location;
import me.dnorris.data.vector.Vector;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 *
 * Represents a percentage bar on the {@link GameArena}
 *
 * @author https://github.com/danorris709
 */
public class PercentageBar extends CompoundEntity {

    private Entity emptyBar;
    private Entity filledBar;

    private int filledPercentage;
    private int maxHeight;

    /**
     *
     * Default constructor publicly accessible
     *
     * @param centerLocation Center point of the percentage bar
     * @param colour Colour of the percentage bar
     * @param motion initial motion
     * @param dimension dimensions
     * @param maxHeight maximum height
     * @param immovable if the percentage bar can be moved
     * @param interactable if the percentage bar can be interacted with
     */
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

    /**
     *
     * Used for constructing the entity during passing the arguments to the parent constructor
     *
     * @param centerLocation Center point of the percentage bar
     * @param colour Colour of the percentage bar
     * @param dimension dimensions
     * @param interactable if the percentage bar can be interacted with
     * @param immovable if the percentage bar can be moved
     * @return The hollow version to show the power bar as not-full
     */
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

    /**
     *
     * Used for constructing the entity during passing the arguments to the parent constructor
     *
     * @param centerLocation Center point of the percentage bar
     * @param colour Colour of the percentage bar
     * @param interactable if the percentage bar can be interacted with
     * @param immovable if the percentage bar can be moved
     * @return The filled version to show the power bar as full
     */
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

    /**
     *
     * Gets the percentage of the {@link PercentageBar} out of 100
     *
     * @return percentage out of 100
     */
    public int getFilledPercentage() {
        return this.filledPercentage;
    }

    /**
     *
     * Gets the filled percentage as a decimal
     *
     * @return percentage as a decimal [0, 1.0]
     */
    public double getPercentage() {
        return this.filledPercentage / 100.00;
    }

    /**
     *
     * Adds the percentage to the bar and updates the display
     *
     * @param delta The amount to change the percentage by
     */
    public void addPercentage(int delta) {
        if((this.filledPercentage + delta) > 100) {
            delta = 100 - this.filledPercentage;
        }

        this.filledPercentage += delta;

        int pixelPercent = (int) ((delta / 100.0) * this.maxHeight);

        this.updateFilledPercentage(pixelPercent);
        this.updateEmptyPercentage(pixelPercent);
    }

    /**
     *
     * Takes the percentage to the bar and updates the display
     *
     * @param delta The amount to change the percentage by
     */
    public void takePercentage(int delta) {
        if((this.filledPercentage - delta) < 0) {
            delta = this.filledPercentage;
        }

        this.filledPercentage -= delta;

        int pixelPercent = (int) ((delta / 100.0) * this.maxHeight);

        this.updateFilledPercentage(-pixelPercent);
        this.updateEmptyPercentage(-pixelPercent);
    }

    /**
     *
     * Updates the filled {@link Entity}
     *
     * @param change The amount it's changing by
     */
    private void updateFilledPercentage(int change) {
        Dimension fullDimensions = this.filledBar.getHitbox().getDimensions();

        this.filledBar.setLocation(this.filledBar.getLocation().subtract(0, change, 0));
        fullDimensions.setSize(
                fullDimensions.getWidth(),
                fullDimensions.getHeight() + change
        );
    }

    /**
     *
     * Updates the unfilled {@link Entity}
     *
     * @param change The amount it's changing by
     */
    private void updateEmptyPercentage(int change) {
        Dimension emptyDimensions = this.emptyBar.getHitbox().getDimensions();

        this.emptyBar.getHitbox().getDimensions().setSize(
                emptyDimensions.getWidth(),
                emptyDimensions.getHeight() - change
        );
    }
}
