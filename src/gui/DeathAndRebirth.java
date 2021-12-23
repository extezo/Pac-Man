package gui;
// Класс описания окна при окончательной смерти и всех взаимодействий после нажатия кнопки "Retry"

import javax.swing.JButton;
import javax.swing.JFrame;

public class DeathAndRebirth extends JFrame {
	
	if (lives==0)
	{
		JFrame death = new JFrame("Death");
		JButton retry = new JButton("Retry");
		JButton quit = new JButton("Quit");
		setResizable(false);
		retry.setBounds(40, 90, 85, 20);
		quit.setBounds(40, 90, 85, 20);
		death.add(retry);
		death.add(quit);
		death.setSize(300, 300);
		death.setLayout(null);
		death.setVisible(true);
		retry.addActionListener(e -> {

		});
		quit.addActionListener(e -> {
			return;
		});}
}
