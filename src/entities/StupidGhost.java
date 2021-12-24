package entities;

import base.PacmanEntity;
import util.Position;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class StupidGhost extends PacmanEntity {
    public StupidGhost(String pathToIcon, Position position) throws IOException {
        icon = ImageIO.read(new File(pathToIcon));
        this.position = position;
        this.direction = "up";
    }

    public void update(boolean[][] board) {
        previousPosition = position.clone();
        boolean canMoveInPrevDirection = false;
        do {
            switch (direction) {
                case "left":
                    if (position.getX() != 0 && !board[position.getX() - 1][position.getY()]) {
                        canMoveInPrevDirection = true;
                        move(-1, 0);
                    }
                    break;
                case "right":
                    if (position.getX() != (board.length - 1) && !board[position.getX() + 1][position.getY()]) {
                        canMoveInPrevDirection = true;
                        move(1, 0);
                    }
                    break;
                case "up":
                    if (position.getY() != 0 && !board[position.getX()][position.getY() - 1]) {
                        canMoveInPrevDirection = true;
                        move(0, -1);
                    }
                    break;
                case "down":
                    if (position.getY() != (board[0].length - 1) && !board[position.getX()][position.getY() + 1]) {
                        canMoveInPrevDirection = true;
                        move(0, 1);
                    }
                    break;
            }
            if (!canMoveInPrevDirection) {
                ArrayList<String> availableDirections = new ArrayList<>();
                if (position.getX() != 0 &&!board[position.getX() - 1][position.getY()])
                    availableDirections.add("left");
                if (position.getX() != (board.length - 1) && !board[position.getX() + 1][position.getY()])
                    availableDirections.add("right");
                if (position.getY() != 0 && !board[position.getX()][position.getY() - 1])
                    availableDirections.add("up");
                if (position.getY() != (board[0].length - 1) && !board[position.getX()][position.getY() + 1])
                    availableDirections.add("down");
                int rnd = (int) Math.floor(Math.random() * (availableDirections.size() - 1));
                direction = availableDirections.get(rnd);
            }
        } while (!canMoveInPrevDirection);
    }
}
