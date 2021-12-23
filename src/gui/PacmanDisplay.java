package gui;

import base.PacmanKeyListener;

import javax.swing.*;
import java.awt.*;

public class PacmanDisplay extends JFrame {
    PacmanBoard board;
    PacmanBar bar;
	PacmanKeyListener kl;
    public PacmanDisplay() {
		kl = new PacmanKeyListener();
    	board = new PacmanBoard(400, 300, kl);
    	bar = new PacmanBar();
		addKeyListener(kl);
    	setDefaultCloseOperation(EXIT_ON_CLOSE);
    	setResizable(false);
    	//setBounds(100, 100, 800, 600);
    	getContentPane().add(board, BorderLayout.CENTER);
    	getContentPane().add(bar, BorderLayout.SOUTH);
    	pack();
    	setVisible(true);
	}
}
