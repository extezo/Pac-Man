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
    boolean[][] coins;
    int cellSize = 20;
    PacmanKeyListener kl;
    Position size;
    ArrayList<PacmanEntity> entities;
    Timer t;

    public PacmanBoard(int width, int height, PacmanKeyListener kl) {
        size = new Position(width, height);
        board = new boolean[width / cellSize][height / cellSize];
        coins = new boolean[width / cellSize][height / cellSize];
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

        checkCollisions();
    }

    private void checkCollisions() {
        Position pacmanPos = entities.get(0).position;
        Position cg = entities.get(1).previousPosition;
        Position sg = entities.get(2).previousPosition;
        for (int x = 0; x < coins.length; x++)
            for (int y = 0; y < coins[0].length; y++)
                if (coins[x][y] && Position.equals(entities.get(0).position, new Position(x,y))) {
                    coins[x][y] = false;
                    //TODO прибавление счёта
                }
        String pacmanDir = entities.get(0).direction;
        if (cg.getX() == pacmanPos.getX() - 1 && cg.getY() == pacmanPos.getY() ||
                sg.getX() == pacmanPos.getX() - 1 && sg.getY() == pacmanPos.getY())
            if (pacmanDir == "right") {
                //TODO прибавление счёта
            } else {
                //TODO смэрть
            }
        if (cg.getX() == pacmanPos.getX() + 1 && cg.getY() == pacmanPos.getY() ||
                sg.getX() == pacmanPos.getX() + 1 && sg.getY() == pacmanPos.getY())
            if (pacmanDir == "left") {
                //TODO прибавление счёта
            } else {
                //TODO смэрть
            }
        if (cg.getX() == pacmanPos.getX() && cg.getY() == pacmanPos.getY() - 1||
                sg.getX() == pacmanPos.getX() && sg.getY() == pacmanPos.getY() - 1)
            if (pacmanDir == "up") {
                //TODO прибавление счёта
            } else {
                //TODO смэрть
            }
        if (cg.getX() == pacmanPos.getX() && cg.getY() == pacmanPos.getY() + 1 ||
                sg.getX() == pacmanPos.getX() && sg.getY() == pacmanPos.getY() + 1)
            if (pacmanDir == "down") {
                //TODO прибавление счёта
            } else {
                //TODO смэрть
            }


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
        ((PacmanModel)entities.get(0)).update(board, direction);
    }
}
