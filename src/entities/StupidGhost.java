package entities;

import base.GhostEntity;
import util.Position;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class StupidGhost extends GhostEntity {
    public StupidGhost(String pathToIcon, Position position) throws IOException {
        icon = ImageIO.read(new File(pathToIcon));
        this.position = position;
    }

    public void update(boolean[][] board) {
        previousPosition = position;
        boolean canMoveInPrevDirection = false;
        do {
            switch (direction) {
                case "left":
                    if (!board[position.getX() - 1][position.getY()]) {
                        canMoveInPrevDirection = true;
                        move(-1, 0);
                    }
                    break;
                case "right":
                    if (!board[position.getX() + 1][position.getY()]) {
                        canMoveInPrevDirection = true;
                        move(1, 0);
                    }
                    break;
                case "up":
                    if (!board[position.getX()][position.getY() - 1]) {
                        canMoveInPrevDirection = true;
                        move(0, -1);
                    }
                    break;
                case "down":
                    if (!board[position.getX()][position.getY() + 1]) {
                        canMoveInPrevDirection = true;
                        move(0, 1);
                    }
                    break;
            }
            if (!canMoveInPrevDirection) {
                ArrayList<String> availableDirections = new ArrayList<>();
                if (!board[position.getX() - 1][position.getY()])
                    availableDirections.add("left");
                if (!board[position.getX() + 1][position.getY()])
                    availableDirections.add("right");
                if (!board[position.getX()][position.getY() - 1])
                    availableDirections.add("up");
                if (!board[position.getX()][position.getY() + 1])
                    availableDirections.add("down");
                int rnd = (int) Math.floor(Math.random() * (availableDirections.size() - 1));
                direction = availableDirections.get(rnd);
            }
        } while (!canMoveInPrevDirection);
    }
}
