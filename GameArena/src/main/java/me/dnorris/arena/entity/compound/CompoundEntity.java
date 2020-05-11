package me.dnorris.arena.entity.compound;

import com.google.common.collect.Lists;
import me.dnorris.arena.Entity;
import me.dnorris.arena.entity.AbstractEntity;
import me.dnorris.data.hitbox.Hitbox;
import me.dnorris.data.location.Location;
import me.dnorris.data.vector.Vector;

import java.awt.*;
import java.util.List;

/**
 *
 * Abstract implementation of a compound {@link Entity} to reduce amount of repeated code in lower classes
 *
 * @author https://github.com/danorris709
 */
public abstract class CompoundEntity extends AbstractEntity {

    protected List<Entity> entities;

    /**
     *
     * Basic constructor accepting an array of entities using varargs
     *
     * @param centerLocation Center of the compound entity
     * @param colour Colour of the sub entities
     * @param motion Motion of the entities
     * @param hitbox Hitbox of the entities
     * @param entities array of sub entities
     */
    public CompoundEntity(Location centerLocation, Color colour, Vector motion, Hitbox hitbox, Entity... entities) {
        super(colour, centerLocation, hitbox, motion, false);

        this.entities = Lists.newArrayList(entities);
    }

    @Override
    public void setColour(Color colour) {
        super.setColour(colour);

        for (Entity entity : this.entities) {
            entity.setColour(colour);
        }
    }

    @Override
    public void setMotion(Vector motion) {
        super.setMotion(motion);

        for (Entity entity : this.entities) {
            entity.setMotion(motion);
        }
    }

    @Override
    public void paint(Graphics2D graphics) {
        for (Entity entity : this.entities) {
            entity.paint(graphics);
        }
    }
}
