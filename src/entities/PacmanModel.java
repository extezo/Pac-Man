package entities;

import base.PacmanEntity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Класс, описывающий игрового персонажа
 */

public class PacmanModel extends PacmanEntity {
    public String direction;
    public PacmanModel(String pathToIcon) throws IOException {
        icon = ImageIO.read(new File(pathToIcon));
    }

    public void draw(Graphics g, int x, int y, String direction) {
        this.direction = direction;
        super.draw(g, x, y);
    }
}
