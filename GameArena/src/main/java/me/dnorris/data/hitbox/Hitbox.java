package me.dnorris.data.hitbox;

import me.dnorris.data.location.Location;

import java.awt.*;

/**
 *
 * Hitbox interface for determining what entities have what physical space
 *
 * @author https://github.com/danorris709
 */
public interface Hitbox {

    /**
     *
     * Each {@link Hitbox} type will have a priority which determines which {@link Hitbox} should handle the collision.
     * Therefore, higher priority {@link Hitbox}s will handle the collisions.
     *
     * @return The priority of the hitbox
     */
    int getPriority();

    /**
     *
     * Center {@link Location} of the {@link Hitbox}
     *
     * @return The center of the {@link Hitbox}
     */
    Location getCenter();

    /**
     *
     * Sets the center {@link Location} of the {@link Hitbox}
     *
     * @param location New center point
     */
    void setCenter(Location location);

    /**
     *
     * Get collision point
     *
     * @param other Other {@link Hitbox}
     * @return point of collision between the two
     */
    Location getLocation(Hitbox other);

    /**
     *
     * The height and width of the {@link Hitbox}
     *
     * @return The {@link Dimension} for the {@link Hitbox}
     */
    Dimension getDimensions();

    /**
     *
     * Determines if the {@link Hitbox} can be moved
     *
     * @return If the {@link Hitbox} is move-able
     */
    boolean isImmovable();

    /**
     *
     * Update if the {@link Hitbox} is move-able
     *
     * @param immovable Can the {@link Hitbox} be moved
     */
    void setImmovable(boolean immovable);

    /**
     *
     * Determines if the {@link Hitbox} can be interacted with by other
     * {@link Hitbox}
     *
     * @return If the {@link Hitbox} can be hit
     */
    boolean isInteractable();

    /**
     *
     * Update if the {@link Hitbox} can be interacted with by other {@link Hitbox}
     *
     * @param interactable Can the {@link Hitbox} be hit
     */
    void setInteractable(boolean interactable);

    /**
     *
     * Determine if the two {@link Hitbox} are interacting with eachother
     *
     * @param other The other {@link Hitbox} being checked
     * @return If the two {@link Hitbox} have collided with eachother
     */
    boolean isColliding(Hitbox other);

    /**
     *
     * Creates a direct copy of the object
     *
     * @return New version of the {@link Hitbox}
     */
    Hitbox clone();

}
