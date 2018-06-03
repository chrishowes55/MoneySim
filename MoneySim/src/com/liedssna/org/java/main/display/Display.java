package com.liedssna.org.java.main.display;

import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.liedssna.org.java.main.game.Game;
import com.liedssna.org.java.main.game.Player;

public class Display extends JFrame {

	private Game game;
	private Player player;
	private JLabel label;
	private JPanel panel;
	private ArrayList<JLabel> labels = new ArrayList<JLabel>();

	public Display(String title, Player player, Game game) {
		super(title);
		this.player = player;
		this.game = game;
		setSize(500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		KeyListener listener = new MyKeyListener();
		addKeyListener(listener);
		setFocusable(true);

		setLayout(new FlowLayout());
		label = new JLabel(player.getName() + " has £" + player.getMoney());
		add(label);

		panel = new JPanel();
		System.out.println(game.getBusinesses().size());
		for (int i = 0; i < game.getBusinesses().size(); i++) {
			labels.add(new JLabel(game.getBusinesses().get(game.getBusinesses().keySet().toArray()[i]).getName() + " "
					+ game.getBusinesses().get(game.getBusinesses().keySet().toArray()[i]).getNum()));
			panel.add(labels.get(i));
		}
		add(panel);
	}

	public void updateDisplay() {
		label.setText(player.getName() + " has £" + player.getMoney());
		for (int i = 0; i < labels.size(); i++) {
			labels.get(i).setText(game.getBusinesses().get(game.getBusinesses().keySet().toArray()[i]).getName() + " "
					+ game.getBusinesses().get(game.getBusinesses().keySet().toArray()[i]).getNum());
		}
	}

	public class MyKeyListener implements KeyListener {

		@Override
		public void keyTyped(KeyEvent e) {
		}

		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				System.out.println("ESCAPE");
				game.setGameIsRunning(false);
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
		}

	}

}
