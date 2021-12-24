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
        previousPosition = position;
    }

    public void draw(Graphics g, int size, String direction) {
        this.direction = direction;
        super.draw(g, size);
    }

    public void update(boolean[][] board, String direction) {
        previousPosition = position;
        Position currentPosition = position.clone();
        switch (direction) {
            case "left":
                currentPosition.translate(-1, 0);
                break;
            case "right":
                currentPosition.translate(1, 0);
                break;
            case "up":
                currentPosition.translate(0, -1);
                break;
            case "down":
                currentPosition.translate(0, 1);
                break;
        }

        if (!board[currentPosition.getX()][currentPosition.getY()])
            position = currentPosition;
    }
}
