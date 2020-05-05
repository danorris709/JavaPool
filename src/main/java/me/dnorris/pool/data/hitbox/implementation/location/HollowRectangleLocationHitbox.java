package me.dnorris.pool.data.hitbox.implementation.location;

import com.google.common.collect.Lists;
import me.dnorris.pool.data.hitbox.Hitbox;
import me.dnorris.pool.data.hitbox.implementation.AbstractLocationHitbox;
import me.dnorris.pool.data.location.Location;

import java.awt.*;
import java.util.List;

public class HollowRectangleLocationHitbox extends AbstractLocationHitbox {

    private final List<Hitbox> hitboxes;
    private final int thickness;

    public HollowRectangleLocationHitbox(Location center, Dimension dimensions, int thickness, boolean immovable, boolean interactable) {
        super(10, center, dimensions, immovable, interactable);

        this.hitboxes = Lists.newArrayList();
        this.thickness = thickness;

        this.hitboxes.add(new RectangleLocationHitbox(center.add(-thickness, -thickness, 0), new Dimension(thickness, dimensions.height + 2*thickness), immovable, interactable));
        this.hitboxes.add(new RectangleLocationHitbox(center.add(-thickness, -thickness, 0), new Dimension(dimensions.width + 2*thickness, thickness), immovable, interactable));
        this.hitboxes.add(new RectangleLocationHitbox(center.add(dimensions.width, -thickness, 0), new Dimension(thickness, dimensions.height + 2*thickness), immovable, interactable));
        this.hitboxes.add(new RectangleLocationHitbox(center.add(-thickness, dimensions.height, 0), new Dimension(dimensions.width + 2*thickness, thickness), immovable, interactable));
    }

    @Override
    public boolean isColliding(Hitbox other) {
        return this.getLocation(other) != null;
    }

    @Override
    public Location getLocation(Hitbox other) {
        if(!this.isInteractable() ||!other.isInteractable()) {
            return null;
        }

        if(other.getPriority() > this.getPriority()) {
            return other.getLocation(this);
        }

        for (Hitbox hitbox : this.hitboxes) {
            if(hitbox.isColliding(other)) {
                return hitbox.getLocation(other);
            }
        }

        return null;
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
