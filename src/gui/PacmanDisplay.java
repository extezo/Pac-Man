package gui;

import base.PacmanKeyListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PacmanDisplay extends JFrame {
    PacmanBoard board;
    PacmanBar bar;
    PacmanKeyListener kl;
    Timer t;
    int currentLives = 3;

    public PacmanDisplay() {
        kl = new PacmanKeyListener();
        bar = new PacmanBar(400, 64);
        board = new PacmanBoard(400, 300, kl, bar);

        addKeyListener(kl);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        add(board, BorderLayout.CENTER);
        add(bar, BorderLayout.SOUTH);
        pack();
        setVisible(true);
        startGameTimer();
    }

    private void startGameTimer() {
        t = new Timer(500, e -> {
            board.update();
            bar.score.setText("" + board.getScore());
            if (bar.lives < currentLives) {
                if (bar.lives < 0)
                    retry();
                currentLives = bar.lives;
                if (Integer.parseInt(bar.bestScore.getText()) < Integer.parseInt(bar.score.getText()))
                    bar.bestScore.setText(bar.score.getText());
                bar.score.setText("0");
                remove(board);
                board = new PacmanBoard(400, 300, kl, bar);
                add(board, BorderLayout.CENTER);
            }
            repaint();
        });
        t.start();
    }

    void retry() {
        switch (JOptionPane.showConfirmDialog(null, "Retry?", "Retry?", JOptionPane.YES_NO_OPTION)) {
            case 0:
                String bestScore = bar.bestScore.getText();
                remove(board);
                remove(bar);
                bar = new PacmanBar(400, 64);
                bar.bestScore.setText(bestScore);
                board = new PacmanBoard(400, 300, kl, bar);
                add(board, BorderLayout.CENTER);
                add(bar, BorderLayout.SOUTH);
                break;
            default:
                System.exit(0);
        }
    }
}
