package me.dnorris.pool.arena.entity.compound;

import com.google.common.collect.Lists;
import me.dnorris.pool.arena.Entity;
import me.dnorris.pool.arena.entity.AbstractEntity;
import me.dnorris.pool.data.hitbox.Hitbox;
import me.dnorris.pool.data.location.Location;
import me.dnorris.pool.data.vector.Vector;

import java.awt.*;
import java.util.List;

public abstract class CompoundEntity extends AbstractEntity {

    private List<Entity> entities;

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
