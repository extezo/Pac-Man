package entities;

import base.PacmanGhost;
import util.Position;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class CleverGhost extends PacmanGhost {
    public CleverGhost(String pathToIcon, Position position) throws IOException {
        icon = ImageIO.read(new File(pathToIcon));
        this.position = position;
        switch ((int)Math.floor(Math.random() * 3)) {
            case 0:
                direction = "left";
                break;
            case 1:
                direction = "up";
                break;
            case 2:
                direction = "right";
                break;
            case 3:
                direction = "down";
                break;
        }
    }
}
