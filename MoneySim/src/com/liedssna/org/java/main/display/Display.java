package com.liedssna.org.java.main.display;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;

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

		setLayout(new GridLayout(0, 1));
		label = new JLabel(player.getName() + " has £" + player.getMoney());
		add(label);

		panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		for (int i = 0; i < game.getBusinesses().size(); i++) {
			labels.add(new JLabel(game.getBusinesses().get((Integer)i).getName() + ": Time:" + game.getBusinesses().get((Integer)i).getTime() + ", Num:"
					+ game.getBusinesses().get((Integer)i).getNum()));
			c.gridx = 0;
			c.gridwidth = 2;
			c.gridy = i + 1;
			panel.add(labels.get(i), c);
			c.gridx = 2;
			c.gridy = i + 1;
			JButton button = new JButton("Buy x1");
			button.addMouseListener(new ButtonMouseListener(i, game));
			panel.add(button, c);
		}
		add(panel);
	}

	public void updateDisplay() {
		label.setText(player.getName() + " has £" + player.getMoney());
	}
	
	public void updateTimes() {
		for (int i = 0; i < labels.size(); i++) {
			labels.get(i).setText(game.getBusinesses().get((Integer)i).getName() + ": Time:" + game.getBusinesses().get((Integer)i).getTime() + ", Num:"
					+ game.getBusinesses().get((Integer)i).getNum());
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
	
	public class ButtonMouseListener extends MouseAdapter {
	
		private int index;
		private Game game;
		
		public ButtonMouseListener(int index, Game game) {
			this.index = index;
			this.game = game;
		}
		
		
		public void mousePressed(MouseEvent e) {
			if (e.getButton() == MouseEvent.BUTTON1) {
				game.increaseNumOfBusiness((Integer)index, 1);
			}
		}
		
	}

}

