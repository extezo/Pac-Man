package gui;

import base.PacmanEntity;
import base.PacmanKeyListener;
import entities.CleverGhost;
import entities.PacmanModel;
import entities.StupidGhost;
import util.Position;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Класс, описывющий игровое поле
 */
public class PacmanBoard extends JComponent {
    boolean[][] board;
    int cellSize = 20;
    PacmanKeyListener kl;
    Position size;
    ArrayList<PacmanEntity> entities;
    Timer t;

    public PacmanBoard(int width, int height, PacmanKeyListener kl) {
        size = new Position(width, height);
        board = new boolean[width / cellSize][height / cellSize];
        this.kl = kl;
        initEntities();
        startGameTimer();
        super.setPreferredSize(new Dimension(width, height));
        setVisible(true);
        repaint();
    }

    private void startGameTimer() {
        t = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                update();
                repaint();
            }
        });
        t.start();
    }

    private void initEntities() {
        entities = new ArrayList<>();
        try {
            entities.add(new PacmanModel("resources/pacman.png", placeEntityOnBoard()));
            entities.add(new CleverGhost("resources/ghostAggressive.png", placeEntityOnBoard()));
            entities.add(new StupidGhost("resources/ghostStupid.png", placeEntityOnBoard()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Position placeEntityOnBoard() {
        Position pos = new Position(0, 0);
        boolean used;
        for (int x = 0; x < board.length; x++)
            for (int y = 0; y < board[0].length; y++) {
                used = false;
                if (!board[x][y])
                    for (PacmanEntity e : entities)
                        if (e.position == pos)
                            used = true;
                if (!used) return pos;
            }
        return null;
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(Color.black);
        for (int i = 0; i <= size.getX(); i += cellSize)
            g.drawLine(i, 0, i, size.getY());
        for (int i = 0; i <= size.getY(); i += cellSize)
            g.drawLine(0, i, size.getX(), i);
        super.paintComponent(g);
    }

    public void update() {
        //Pacman's logic
        updatePacman();
        //Ghosts' logic
        updateGhosts();
    }

    private void updateGhosts() {
        CleverGhost cg = (CleverGhost) entities.get(1);
        StupidGhost sg = (StupidGhost) entities.get(2);
        boolean canMoveInPrevDirection = false;
        do {
            switch (sg.direction) {
                case "left":
                    if (!board[sg.position.getX() - 1][sg.position.getY()]) {
                        canMoveInPrevDirection = true;
                        sg.move(-1, 0);
                    }
                    break;
                case "right":
                    if (!board[sg.position.getX() + 1][sg.position.getY()]) {
                        canMoveInPrevDirection = true;
                        sg.move(1, 0);
                    }
                    break;
                case "up":
                    if (!board[sg.position.getX()][sg.position.getY() - 1]) {
                        canMoveInPrevDirection = true;
                        sg.move(0, -1);
                    }
                    break;
                case "down":
                    if (!board[sg.position.getX()][sg.position.getY() + 1]) {
                        canMoveInPrevDirection = true;
                        sg.move(0, 1);
                    }
                    break;
            }
            if (!canMoveInPrevDirection) {
                ArrayList<String> availableDirections = new ArrayList<>();
                if (!board[sg.position.getX() - 1][sg.position.getY()])
                    availableDirections.add("left");
                if (!board[sg.position.getX() + 1][sg.position.getY()])
                    availableDirections.add("right");
                if (!board[sg.position.getX()][sg.position.getY() - 1])
                    availableDirections.add("up");
                if (!board[sg.position.getX()][sg.position.getY() + 1])
                    availableDirections.add("down");
                int rnd = (int) Math.floor(Math.random() * (availableDirections.size() - 1));
                sg.direction = availableDirections.get(rnd);
            }
        } while (!canMoveInPrevDirection);
    }

    private void updatePacman() {
        String direction = kl.getDirection();
        Position currentPosition = entities.get(0).position;
        switch (direction) {
            case "left":
                currentPosition.translate(-1, 0);
                break;
            case "right":
                currentPosition.translate(1, 0);
                break;
            case "up":
                currentPosition.translate(0, -1);
                break;
            case "down":
                currentPosition.translate(0, 1);
                break;
        }
        if (!board[currentPosition.getX()][currentPosition.getY()])
            entities.get(0).position = currentPosition;
    }
}
