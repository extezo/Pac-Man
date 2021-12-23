package gui;

import javax.swing.*;
import java.awt.*;

/**
 * Класс, описывющий игровое поле
 */
public class PacmanBoard extends JComponent {
	boolean[][] board;
	int cellSize = 20;
	Point size;

	public PacmanBoard(int width, int height) {
		size = new Point(width, height);
		board = new boolean[width / cellSize][height / cellSize];
		board[2][3] = true;
		board[0][0] = true;
		
		super.setPreferredSize(new Dimension(width, height));
		setVisible(true);
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		int indx, indy;
		for (int i = 0; i <= size.y; i++)
			for (int j = 0; j <= size.x; j++) {
				indx = j / cellSize;
				indy = i / cellSize;
				if (indx >= 1 && indy >= 1) {
					if (board[indx - 1][indy - 1] == true) {
						g.setColor(Color.white);
						g.drawLine(j, i, j, i);

					} else {
						g.setColor(Color.black);
						g.drawLine(j, i, j, i);
					}
				}
				super.paintComponent(g);

			}
	}
}