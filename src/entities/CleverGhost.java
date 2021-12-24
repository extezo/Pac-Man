package entities;

import base.GhostEntity;
import util.Position;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class CleverGhost extends GhostEntity {
    public CleverGhost(String pathToIcon, Position position) throws IOException {
        icon = ImageIO.read(new File(pathToIcon));
        this.direction = "up";
        this.position = position;
        switch ((int) Math.floor(Math.random() * 3)) {
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
        previousPosition = position.clone();
        findDirToPacman(pacmanPos, board);

        switch (direction) {
            case "left":
                move(-1, 0);
                break;
            case "right":
                move(1, 0);
                break;
            case "up":
                move(0, -1);
                break;
            case "down":
                move(0, 1);
                break;
        }
    }

    public void findDirToPacman(Position pacmanPos, boolean[][] walls) {
        int[][] routes = new int[walls.length][walls[0].length];

        for (int i = 0; i < walls.length; i++)
            for (int j = 0; j < walls[0].length; j++)
                if (walls[i][j])
                    routes[i][j] = -1;
                else routes[i][j] = -2;
        routes[position.getX()][position.getY()] = 0;
        calcRoutes(routes, pacmanPos);
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
            if (x != 0 && routes[x][y] - routes[x - 1][y] == 1) {
                x--;
                continue;
            }
            if (x != routes.length - 1 && routes[x][y] - routes[x + 1][y] == 1) {
                x++;
                continue;
            }
            if (y != 0 && routes[x][y] - routes[x][y - 1] == 1) {
                y--;
                continue;
            }
            if (y != routes[0].length - 1 && routes[x][y] - routes[x][y + 1] == 1) {
                y++;
                continue;
            }
        } while (x != position.getX() || y != position.getY());
        return result;
    }

    private void calcRoutes(int[][] routes, Position finish) {
        int d = 0;
        do {
            for (int x = 0; x < routes.length; x++) {
                for (int y = 0; y < routes[0].length; y++) {
                    if (routes[x][y] == d) {
                        if (x > 0 && routes[x - 1][y] == -2)
                            routes[x - 1][y] = d + 1;
                        if(x<routes.length-1&& routes[x + 1][y] == -2)
                            routes[x + 1][y] = d + 1;
                        if(y>0&& routes[x][y - 1] == -2)
                            routes[x][y - 1] = d + 1;
                        if(y<routes[0].length-1&&  routes[x][y + 1] == -2)
                            routes[x][y + 1] = d + 1;

                    }
                }
            }
            boolean done = true;
            for (int x = 0; x < routes.length; x++)
                for (int y = 0; y < routes[0].length; y++)
                    if (routes[x][y] < 0)
                        done = false;
            if (done) return;
            d++;
            /*System.out.println();
            for (int x = 0; x < routes.length; x++)
                System.out.println(Arrays.toString(routes[x]));*/
        } while (routes[finish.getX()][finish.getY()] == -2);
    }
}
