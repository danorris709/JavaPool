package me.dnorris.pool.data.hitbox.implementation.location;

import me.dnorris.pool.data.hitbox.Hitbox;
import me.dnorris.pool.data.hitbox.implementation.AbstractLocationHitbox;
import me.dnorris.pool.data.location.Location;

import java.awt.*;

public class HollowRectangleLocationHitbox extends AbstractLocationHitbox {

    private int thickness;

    public HollowRectangleLocationHitbox(Location center, Dimension dimensions, int thickness, boolean immovable, boolean interactable) {
        super(10, center, dimensions, immovable, interactable);

        this.thickness = thickness;
    }

    @Override
    public boolean isColliding(Hitbox other) {
        if(!(this.isInteractable() && other.isInteractable())) {
            return false;
        }

        if(other.getPriority() > this.getPriority()) {
            return other.isColliding(this);
        }

        double topSide = this.getCenter().getY();
        double otherHeightTop = other.getCenter().getY() - other.getDimensions().getHeight();

        if(topSide >= otherHeightTop && otherHeightTop >= (topSide - thickness)) {
            return true;
        }

        double bottomSide = this.getCenter().getY() + this.getDimensions().getHeight();
        double otherSideBottom = other.getCenter().getY() + other.getDimensions().getHeight();

        if(bottomSide <= otherSideBottom && otherSideBottom <= (bottomSide + thickness)) {
            return true;
        }

        double thisSide = this.getCenter().getX();
        double otherSideLeft = other.getCenter().getX() - other.getDimensions().getWidth();

        if(thisSide >= otherSideLeft && otherSideLeft >= (thisSide + thickness)) {
            return true;
        }

        double farSide = this.getCenter().getX() + this.getDimensions().getWidth();
        double otherSideRight = other.getCenter().getX() + other.getDimensions().getWidth();

        return farSide <= otherSideRight && otherSideRight <= (farSide + thickness);
    }

    @Override
    public Hitbox clone() {
        return new HollowRectangleLocationHitbox(
                this.getCenter().clone(),
                new Dimension(this.getDimensions().width, this.getDimensions().height),
                this.thickness,
                this.isImmovable(),
                this.isInteractable()
        );
    }
}
