package entities;

import base.GhostEntity;
import util.Position;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class CleverGhost extends GhostEntity {
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

    public void update(boolean[][] board, Position pacmanPos) {
        previousPosition = position;
        findDirToPacman(pacmanPos, board);
        switch (direction) {
            case "left":
                move(-1, 0);
            case "right":
                move(1, 0);
            case "up":
                move(0, -1);
            case "down":
                move(0, 1);
        }
    }

    public void findDirToPacman(Position pacmanPos, boolean[][] walls) {
        int[][] routes = new int[walls.length][walls[0].length];
        routes[position.getX()][position.getY()] = 0;
        for (int i = 0; i < walls.length; i++)
            for (int j = 0; j < walls[0].length; j++)
                routes[i][j] = -1;
        calcRoutes(routes, position.getX(), position.getY(), 0);
        Position nextPos = findNextStep(routes, pacmanPos.getX(), pacmanPos.getY());
        if (nextPos.getX() < position.getX())
            direction = "left";
        if (nextPos.getX() > position.getX())
            direction = "right";
        if (nextPos.getY() < position.getY())
            direction = "up";
        if (nextPos.getY() > position.getY())
            direction = "down";


    }

    private Position findNextStep(int[][] routes, int x, int y) {
        Position result = new Position(x, y);
        do {
            result = new Position(x, y);
            if (routes[x][y]-routes[x-1][y] == 1) {
                x--;
                continue;
            }
            if (routes[x][y]-routes[x+1][y] == 1) {
                x++;
                continue;
            }
            if (routes[x][y]-routes[x][y-1] == 1) {
                y--;
                continue;
            }
            if (routes[x][y]-routes[x][y+1] == 1) {
                y++;
                continue;
            }
        } while (x != position.getX() || y != position.getY());
        return result;
    }

    private void calcRoutes(int[][] routes, int x, int y, int dist) {
        if (routes[x][y] != 1 || x >= routes.length || x < 0 || y >= routes[0].length || y < 0)
            return;
        routes[x][y] = dist+1;
        calcRoutes(routes, x-1, y, dist+1);
        calcRoutes(routes, x+1, y, dist+1);
        calcRoutes(routes, x, y-1, dist+1);
        calcRoutes(routes, x, y+1, dist+1);
    }
}
