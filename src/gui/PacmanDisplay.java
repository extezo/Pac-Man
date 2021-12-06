package gui;

import javax.swing.*;
import java.awt.*;

public class PacmanDisplay extends JFrame {
    PacmanBoard board;
    PacmanBar bar;
    public PacmanDisplay() {
    	board = new PacmanBoard(700, 500);
    	bar = new PacmanBar();
    	setDefaultCloseOperation(EXIT_ON_CLOSE);
    	setResizable(false);
    	//setBounds(100, 100, 800, 600);
    	getContentPane().add(board, BorderLayout.CENTER);
    	getContentPane().add(bar, BorderLayout.SOUTH);
    	pack();
    	setVisible(true);
	}
}
