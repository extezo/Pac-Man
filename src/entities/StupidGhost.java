package entities;

import base.PacmanGhost;
import util.Position;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class StupidGhost extends PacmanGhost {
    public StupidGhost(String pathToIcon, Position position) throws IOException {
        icon = ImageIO.read(new File(pathToIcon));
        this.position = position;
    }
}
