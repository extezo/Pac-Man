package gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

// Класс для расположения и указания основных алгоритмов работы с баром
public class PacmanBar extends JPanel {
    int lives = 3;
    public JLabel score, bestScore, scoreL, bestScoreL;
	public PacmanBar(int width, int height) {
        setLayout(null);
        super.setPreferredSize(new Dimension(width, height));
        score = new JLabel("0");
        score.setBounds(250, 0, 100, 32);
        score.setForeground(Color.WHITE);
        bestScore = new JLabel("0");
        bestScore.setBounds(250, 32, 100, 32);
        bestScore.setForeground(Color.WHITE);
        scoreL = new JLabel("Score: ");
        scoreL.setBounds(170, 0, 100, 32);
        scoreL.setForeground(Color.WHITE);
        bestScoreL = new JLabel("Best score:");
        bestScoreL.setBounds(170, 32, 100, 32);
        bestScoreL.setForeground(Color.WHITE);
        add(score);
        add(bestScore);
        add(scoreL);
        add(bestScoreL);


        setBackground(Color.DARK_GRAY);
        setVisible(true);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int x = 0; x < lives; x++) {
            try {
                g.drawImage(ImageIO.read(new File("resources/pacman.png")),
                        x * 50 + 16, 16,
                        x * 50 + 48, 48,
                        0, 0, 64, 64, null);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
