package me.dnorris.pool.arena.entity.shape;

import me.dnorris.pool.arena.entity.AbstractEntity;
import me.dnorris.pool.arena.entity.EntityBuilder;
import me.dnorris.pool.arena.entity.EntityType;
import me.dnorris.pool.data.hitbox.Hitbox;
import me.dnorris.pool.data.hitbox.implementation.location.EmptyHitbox;
import me.dnorris.pool.data.location.Location;
import me.dnorris.pool.data.vector.Vector;
import me.dnorris.pool.data.vector.implementation.Vector2D;

import java.awt.*;

/**
 *
 * Represents text on the {@link me.dnorris.pool.arena.GameArena}
 *
 * @author https://github.com/danorris709
 */
public class TextEntity extends AbstractEntity {

    private String text;
    private Font font;

    /**
     *
     * Default constructor
     * Protected so only accessible from classes extending this, and the builder
     *
     * @param colour Colour of the text
     * @param location Location of the text
     * @param text Text to display
     * @param font Font of the text
     * @param size Size of the font
     * @param bold If the font is bold
     * @param italic If the font is italic
     */
    protected TextEntity(Color colour, Location location, String text, String font, int size, boolean bold, boolean italic) {
        super(colour, location, new EmptyHitbox(location, true), Vector2D.NONE, false);

        this.text = text;
        this.initFont(font, size, bold, italic);
    }

    private void initFont(String font, int size, boolean bold, boolean italic) {
        int style = Font.PLAIN;

        if(bold && italic) {
            style = Font.BOLD | Font.ITALIC;
        }else if(!italic) {
            style = Font.BOLD;
        }else {
            style = Font.ITALIC;
        }

        this.font = new Font(font, style, size);
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Font getFont() {
        return this.font;
    }

    @Override
    public void paint(Graphics2D graphics) {
        graphics.setFont(this.font);
        graphics.setColor(this.getColour());
        graphics.drawString(this.text, (float) this.getLocation().getX(), (float) this.getLocation().getY());
    }


    /**
     *
     * Builder class for Text as it has custom flags (i.e. font & text related)
     *
     * @author https://github.com/danorris709
     */
    public static class Builder extends EntityBuilder {

        private String text;
        private String font;
        private int size;
        private boolean bold;
        private boolean italic;

        public Builder() { super(); }

        @Override
        public Builder setColour(Color colour) {
            return (Builder) super.setColour(colour);
        }

        @Override
        public Builder setLocation(Location location) {
            return (Builder) super.setLocation(location);
        }

        @Override
        public Builder setHitbox(Hitbox hitbox) {
            return (Builder) super.setHitbox(hitbox);
        }

        @Override
        public Builder setMotion(Vector motion) {
            return (Builder) super.setMotion(motion);
        }

        @Override
        public Builder setHollow(boolean hollow) {
            return (Builder) super.setHollow(hollow);
        }

        @Override
        public Builder setType(EntityType type) {
            return (Builder) super.setType(type);
        }

        @Override
        public Builder setImmovable(boolean immovable) {
            return (Builder) super.setImmovable(immovable);
        }

        @Override
        public Builder setInteractable(boolean interactable) {
            return (Builder) super.setInteractable(interactable);
        }

        @Override
        public Builder setDimension(Dimension dimension) {
            return (Builder) super.setDimension(dimension);
        }

        public Builder setText(String text) {
            this.text = text;
            return this;
        }

        public Builder setFont(String font) {
            this.font = font;
            return this;
        }

        public Builder setSize(int size) {
            this.size = size;
            return this;
        }

        public Builder setBold(boolean bold) {
            this.bold = bold;
            return this;
        }

        public Builder setItalic(boolean italic) {
            this.italic = italic;
            return this;
        }

        @Override
        public TextEntity build() {
            return new TextEntity(
                    this.colour,
                    this.location,
                    this.text,
                    this.font,
                    this.size,
                    this.bold,
                    this.italic
            );
        }

        @Override
        public Builder clone() {
            Builder newBuilder = new Builder();

            if (this.hitbox != null) {
                newBuilder.hitbox = this.hitbox.clone();
            }

            if (this.location != null) {
                newBuilder.location = this.location.clone();
            }

            if (this.dimension != null) {
                newBuilder.dimension = new Dimension(this.dimension.width, this.dimension.height);
            }

            newBuilder.colour = this.colour;
            newBuilder.motion = this.motion;
            newBuilder.hollow = this.hollow;
            newBuilder.immovable = this.immovable;
            newBuilder.interactable = this.interactable;
            newBuilder.type = this.type;

            newBuilder.text = this.text;
            newBuilder.font = this.font;
            newBuilder.size = this.size;
            newBuilder.bold = this.bold;
            newBuilder.italic = this.italic;

            return newBuilder;
        }
    }
}
