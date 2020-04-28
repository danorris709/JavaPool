package me.dnorris.pool.data.hitbox;

import me.dnorris.pool.data.location.Location;

import java.awt.*;

public interface Hitbox {

    /**
     *
     * Center {@link Location} of the {@link Hitbox}
     *
     * @return The center of the {@link Hitbox}
     */
    Location getCenter();

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

}
