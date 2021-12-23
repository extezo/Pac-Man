package base;

import java.awt.*;
import java.awt.geom.Dimension2D;
import java.awt.image.BufferedImage;

/**
 * Родительский класс всех игровых сущностей
 */
public class PacmanEntity {
    public final int SIZE = 64;
    public BufferedImage icon;

    public void draw(Graphics g, int x, int y) {
        g.drawImage(icon, x, y, null);
    }
}
