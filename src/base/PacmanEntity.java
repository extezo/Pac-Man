package base;

import util.Position;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * Родительский класс всех игровых сущностей
 */
public class PacmanEntity {
    public final int SIZE = 64;
    protected BufferedImage icon;
    public Position position;
    public String direction;

    public void draw(Graphics g, int x, int y) {
        g.drawImage(icon, x, y, null);
    }
    public void move(int dx, int dy) {
        position.translate(dx, dy);
    }
}
