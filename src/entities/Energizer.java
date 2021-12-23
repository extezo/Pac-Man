package entities;

import base.PacmanEntity;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Energizer extends PacmanEntity {
    public Energizer(String pathToIcon) throws IOException {
        icon = ImageIO.read(new File(pathToIcon));
    }
}
