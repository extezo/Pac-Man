package entities;

import base.PacmanEntity;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Coin extends PacmanEntity {
    public Coin(String pathToIcon) throws IOException {
        icon = ImageIO.read(new File(pathToIcon));
    }
}
