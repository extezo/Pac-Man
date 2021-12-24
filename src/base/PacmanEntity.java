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
    public Position previousPosition;
    public boolean dead = false;

    public void draw(Graphics g, int size) {
        g.drawImage(icon,
                position.getX()* size, position.getY()*size,
                (position.getX()+1) * size, (position.getY()+1)*size,
                0, 0, 64, 64,null);
    }
    public void move(int dx, int dy) {
        position.translate(dx, dy);
    }

}
