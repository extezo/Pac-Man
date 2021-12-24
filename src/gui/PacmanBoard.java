package gui;

import base.PacmanEntity;
import base.PacmanKeyListener;
import entities.CleverGhost;
import entities.PacmanModel;
import entities.StupidGhost;
import util.Boards;
import util.Position;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Класс, описывющий игровое поле
 */
public class PacmanBoard extends JComponent {
    boolean[][] board;
    boolean[][] coins;
    public static int cellSize = 20;
    PacmanKeyListener kl;
    Point size;
    ArrayList<PacmanEntity> entities;
    Timer t;
    PacmanBar bar;

    int score = 0;

    public PacmanBoard(int width, int height, PacmanKeyListener kl, PacmanBar bar) {
        size = new Point(width, height);
        //board = new boolean[width / cellSize][height / cellSize];
        board = Boards.defaultBoard;
        coins = new boolean[width / cellSize][height / cellSize];
        this.bar = bar;

        this.kl = kl;
        initEntities();
        super.setPreferredSize(new Dimension(width, height));
        setVisible(true);
        repaint();
    }


    private void initEntities() {
        entities = new ArrayList<>();
        try {
            entities.add(new PacmanModel("resources/pacman.png", new Position(1, 1)));
            entities.add(new CleverGhost("resources/ghostAggressive.png", new Position(5, 13)));
            entities.add(new StupidGhost("resources/ghostStupid.png", new Position(11, 1)));
            //placeEntityOnBoard()
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int x = 0; x < coins.length; x++)
            for (int y = 0; y < coins[0].length; y++)
                if (!board[x][y])
                    coins[x][y] = true;
    }

    private Position placeEntityOnBoard() {
        Position pos = new Position(0, 0);
        boolean used;
        for (int x = 0; x < board.length; x++)
            for (int y = 0; y < board[0].length; y++) {
                used = false;
                if (!board[x][y])
                    for (PacmanEntity e : entities)
                        if (Position.equals(e.position, pos))
                            used = true;
                if (!used) return pos;
            }
        return null;
    }

    @Override
    protected void paintComponent(Graphics g) {
        for (int x = 0; x < board.length; x++)
            for (int y = 0; y < board[0].length; y++) {
                if (board[x][y])
                    g.fillRect(x * cellSize, y * cellSize, cellSize, cellSize);
                if (coins[x][y]) {
                    try {
                        g.drawImage(ImageIO.read(new File("resources/coin.png")),
                                x * cellSize, y * cellSize,
                                (x + 1) * cellSize, (y + 1) * cellSize,
                                0, 0, 64, 64, null);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        ((PacmanModel) entities.get(0)).draw(g, cellSize, kl.getDirection());
        if (!entities.get(1).dead)
            entities.get(1).draw(g, cellSize);
        if (!entities.get(2).dead)
            entities.get(2).draw(g, cellSize);
        for (PacmanEntity e : entities)
            if (!e.dead)
                e.draw(g, cellSize);
        //System.out.println(entities.get(1).position.getX() + " " + entities.get(1).position.getY());
        super.paintComponent(g);
    }

    public void update() {
        //Pacman's logic
        updatePacman();
        //Ghosts' logic
        updateGhosts();

        checkCollisions();
    }


    private void checkCollisions() {
        Position pacmanPos = entities.get(0).position;
        PacmanEntity cg = entities.get(1);
        PacmanEntity sg = entities.get(2);
        Position cgP = cg.position;
        Position sgP = sg.position;
        for (int x = 0; x < coins.length; x++)
            for (int y = 0; y < coins[0].length; y++)
                if (coins[x][y] && Position.equals(entities.get(0).position, new Position(x, y))) {
                    coins[x][y] = false;
                    score += 100;
                }
        String pacmanDir = entities.get(0).direction;
        //System.out.println(pacmanDir + " " + pacmanPos.getX() + " " + pacmanPos.getY() + " " + cg.direction + " " + cgP.getX() + " " + cgP.getY());
        if (!cg.dead && cgP.getX() == pacmanPos.getX() && cgP.getY() == pacmanPos.getY()) {
            if (Objects.equals(pacmanDir, "down") && Objects.equals(cg.direction, "up") ||
                    Objects.equals(pacmanDir, "up") && Objects.equals(cg.direction, "down") ||
                    Objects.equals(pacmanDir, "right") && Objects.equals(cg.direction, "left") ||
                    Objects.equals(pacmanDir, "left") && Objects.equals(cg.direction, "right")) {
                System.out.println("dead");
                score += 1000;
                cg.dead = true;
            } else {
                endOfGame();
            }
        }
        if (!sg.dead && sgP.getX() == pacmanPos.getX() && sgP.getY() == pacmanPos.getY()) {
            if (Objects.equals(pacmanDir, "down") && Objects.equals(sg.direction, "up") ||
                    Objects.equals(pacmanDir, "up") && Objects.equals(sg.direction, "down") ||
                    Objects.equals(pacmanDir, "right") && Objects.equals(sg.direction, "left") ||
                    Objects.equals(pacmanDir, "left") && Objects.equals(sg.direction, "right")) {
                System.out.println("dead");
                score += 1000;
                sg.dead = true;
            } else {
                endOfGame();
            }
        }
    }


    private void endOfGame() {
        bar.lives--;
    }

    private void updateGhosts() {
        CleverGhost cg = (CleverGhost) entities.get(1);
        StupidGhost sg = (StupidGhost) entities.get(2);
        //Stupid ghost logic
        sg.update(board);

        //Clever ghost logic
        cg.update(board, entities.get(0).position);
    }

    private void updatePacman() {
        String direction = kl.getDirection();
        ((PacmanModel) entities.get(0)).update(board, direction);
    }

    public int getScore() {
        return score;
    }
}