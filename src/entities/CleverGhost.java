package entities;

import base.PacmanGhost;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class CleverGhost extends PacmanGhost {
    public CleverGhost(String pathToIcon) throws IOException {
        icon = ImageIO.read(new File(pathToIcon));
    }
}
