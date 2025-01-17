package me.dnorris.arena;

import me.dnorris.data.hitbox.Hitbox;
import me.dnorris.data.location.Location;
import me.dnorris.data.vector.Vector;

import java.awt.*;

/**
 *
 * An interface that represents anything that can be added to the {@link GameArena}
 *
 * @author https://github.com/danorris709
 */
public interface Entity {

    /**
     *
     * Gets the {@link GameArena} with which the entity is associated
     *
     * @return The {@link GameArena} where the entity lives
     */
    GameArena getArena();

    /**
     *
     * Sets the {@link GameArena} with which the entity is associated
     *
     * @param arena The new {@link GameArena} where the entity lives
     */
    void setArena(GameArena arena);

    /**
     *
     * Gets the {@link Color} of the {@link Entity} on the {@link GameArena}
     *
     * @return The {@link Color} of the {@link Entity}
     */
    Color getColour();

    /**
     *
     * Gets the {@link Color} of the {@link Entity}
     *
     * @param colour {@link Color} of the {@link Entity}
     */
    void setColour(Color colour);

    /**
     *
     * Get the exact point at which the {@link Entity} lies
     *
     * @return The {@link Location} center of the {@link Entity}
     */
    Location getLocation();

    /**
     *
     * Set the exact point at which the {@link Entity} lies
     *
     * @param location The new {@link Location} center of the {@link Entity}
     */
    void setLocation(Location location);

    /**
     *
     * The physical representation of the {@link Entity} for when moving around the {@link GameArena}
     *
     * @return The {@link Hitbox} of the {@link Entity}
     */
    Hitbox getHitbox();

    /**
     *
     * The representation of the motion of the {@link Entity}
     *
     * @return The {@link Hitbox} of the {@link Entity}
     */
    Vector getMotion();

    /**
     *
     * Set the representation of the motion of the {@link Entity}
     *
     * @param motion The {@link Hitbox} of the {@link Entity}
     */
    void setMotion(Vector motion);

    /**
     *
     * Draws the entity onto the {@link GameArena}
     *
     * @param graphics The {@link Graphics2D} from the {@link GameArena#tick(Graphics2D)}
     */
    void paint(Graphics2D graphics);

    /**
     *
     * Handles the collision between the two entities
     *
     * @param other The entity being collided with
     */
    void collide(Entity other);

    /**
     *
     * Updates the details of the {@link Entity} and is called every frame
     * For example, updates the location of the {@link Entity} using the motion
     *
     */
    void tick();

}
