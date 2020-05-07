package me.dnorris.pool.arena.entity.shape;

import me.dnorris.pool.arena.entity.AbstractEntity;
import me.dnorris.pool.data.hitbox.implementation.location.EmptyHitbox;
import me.dnorris.pool.data.location.Location;
import me.dnorris.pool.data.vector.implementation.Vector2D;

import java.awt.*;

public class TextEntity extends AbstractEntity {

    private String text;
    private Font font;

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
}
