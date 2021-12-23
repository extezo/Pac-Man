package entities;

import base.PacmanEntity;
import util.Position;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;

/**
 * Класс, описывающий игрового персонажа
 */

public class PacmanModel extends PacmanEntity {
    public PacmanModel(String pathToIcon, Position position) throws IOException {
        icon = ImageIO.read(new File(pathToIcon));
        this.position = position;
    }

    public void draw(Graphics g, int x, int y, String direction) {
        this.direction = direction;
        super.draw(g, x, y);
    }
}
