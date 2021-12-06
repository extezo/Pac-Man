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
		super.setPreferredSize(new Dimension(width, height));
		setVisible(true);
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		g.setColor(Color.black);
		for (int i = 0; i <= size.x; i += cellSize)
			g.drawLine(i, 0, i, size.y);
		for (int i = 0; i <= size.y; i += cellSize)
			g.drawLine(0, i, size.x, i);
		super.paintComponent(g);
	}
}
