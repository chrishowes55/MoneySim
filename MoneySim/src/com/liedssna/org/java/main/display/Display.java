package com.liedssna.org.java.main.display;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.liedssna.org.java.main.game.Game;
import com.liedssna.org.java.main.game.Player;

public class Display extends JFrame {

	private Game game;
	private Player player;
	private JLabel label1, label2, noLabel1, noLabel2;
	private JPanel panel1, panel2, normalPanel, upgradesPanel;
	private JButton upgradesButton, exitUpgradesButton;
	private ArrayList<JLabel> labels1 = new ArrayList<JLabel>(), labels2 = new ArrayList<JLabel>();
	private ArrayList<JButton> buttons1 = new ArrayList<JButton>(), buttons2 = new ArrayList<JButton>();

	public Display(String title, Player player, Game game) {
		super(title);
		this.player = player;
		this.game = game;
		setSize(500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		KeyListener listener = new MyKeyListener();
		addKeyListener(listener);
		setFocusable(true);

		createPanels();
		setContentPane(normalPanel);
	}

	public void createPanels() {
		// NORMAL PANEL
		normalPanel = new JPanel();
		normalPanel.setLayout(new GridLayout(0, 1));
		label1 = new JLabel(player.getName() + " has £" + convertToNamed(player.getMoney()));
		normalPanel.add(label1);

		noLabel1 = new JLabel("You cannot buy that");
		noLabel1.setVisible(false);
		normalPanel.add(noLabel1);

		panel1 = new JPanel();
		panel1.setLayout(new GridBagLayout());
		GridBagConstraints c1 = new GridBagConstraints();
		c1.fill = GridBagConstraints.HORIZONTAL;
		for (int i = 0; i < game.getBusinesses().size(); i++) {
			labels1.add(new JLabel(game.getBusinesses().get((Integer) i).getName() + ": Time:"
					+ game.getBusinesses().get((Integer) i).getTime() + ", Num:"
					+ game.getBusinesses().get((Integer) i).getNum()));
			c1.gridx = 0;
			c1.gridwidth = 2;
			c1.gridy = i + 1;
			panel1.add(labels1.get(i), c1);
			c1.gridx = 2;
			c1.gridy = i + 1;
			JButton button = new JButton(
					"Buy x1 for " + convertToNamed(game.getBusinesses().get((Integer) i).getDisplayPrice()));
			button.addMouseListener(new ButtonMouseListener(i, game, true));
			buttons1.add(button);
			panel1.add(button, c1);
		}
		normalPanel.add(panel1);

		upgradesButton = new JButton("Upgrades");
		upgradesButton.addMouseListener(new UpgradesButtonMouseListener(this));
		normalPanel.add(upgradesButton);

		// UPGRADES PANEL
		upgradesPanel = new JPanel();
		upgradesPanel.setLayout(new GridLayout(0, 1));
		label2 = new JLabel(player.getName() + " has £" + convertToNamed(player.getMoney()));
		upgradesPanel.add(new JLabel("UPGRADES"));
		upgradesPanel.add(label2);

		noLabel2 = new JLabel("You cannot buy that");
		noLabel2.setVisible(false);
		upgradesPanel.add(noLabel2);

		panel2 = new JPanel();
		panel2.setLayout(new GridBagLayout());
		GridBagConstraints c2 = new GridBagConstraints();
		c2.fill = GridBagConstraints.HORIZONTAL;
		for (int i = 0; i < 5; i++) {
			labels2.add(new JLabel(game.getUpgrades().get(i).getName()));
			c2.gridx = 0;
			c2.gridwidth = 2;
			c2.gridy = i + 1;
			panel2.add(labels2.get(i), c2);
			c2.gridx = 2;
			c2.gridy = i + 1;
			JButton button = new JButton("Buy for " + convertToNamed(game.getUpgrades().get(i).getPrice()));
			button.addMouseListener(new ButtonMouseListener(i, game, false));
			buttons2.add(button);
			panel2.add(button, c2);
		}
		upgradesPanel.add(panel2);

		exitUpgradesButton = new JButton("Back");
		exitUpgradesButton.addMouseListener(new ExitUpgradesButtonMouseListener(this));
		upgradesPanel.add(exitUpgradesButton);
	}

	public void updateDisplay() {
		label1.setText(player.getName() + " has £" + player.getMoney());
		label2.setText(player.getName() + " has £" + player.getMoney());
	}

	public void updateTimes() {
		for (int i = 0; i < labels1.size(); i++) {
			labels1.get(i)
					.setText(game.getBusinesses().get((Integer) i).getName() + ": Time:"
							+ game.getBusinesses().get((Integer) i).getTime() + ", Num:"
							+ game.getBusinesses().get((Integer) i).getNum());
		}
	}

	public void updatePrices(int i) {
		buttons1.get(i).setText("Buy x1 for " + game.getBusinesses().get((Integer) i).getDisplayPrice());
	}

	public void updateUpgrades() {
		for (int i = 0; i < 5; i++) {
			if (i >= game.getUpgrades().size()) {
				labels2.get(i).setText("No upgrades are left");
				buttons2.get(i).setVisible(false);
			} else {
				labels2.get(i).setText(game.getUpgrades().get(i).getName());
				buttons2.get(i).setText("Buy for " + game.getUpgrades().get(i).getPrice());
			}
		}
	}

	public void noBuyBusiness() {
		noLabel1.setVisible(true);
		int delay = 1000; // milliseconds
		ActionListener taskPerformer = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				noLabel1.setVisible(false);
			}
		};
		javax.swing.Timer tick = new javax.swing.Timer(delay, taskPerformer);
		tick.setRepeats(false);
		tick.start();
	}

	public void noBuyUpgrade() {
		noLabel2.setVisible(true);
		int delay = 1000; // milliseconds
		ActionListener taskPerformer = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				noLabel2.setVisible(false);
			}
		};
		javax.swing.Timer tick = new javax.swing.Timer(delay, taskPerformer);
		tick.setRepeats(false);
		tick.start();
	}

	public void showUpgrades() {
		remove(normalPanel);
		setContentPane(upgradesPanel);
		validate();
		repaint();
	}

	public void showNormal() {
		remove(upgradesPanel);
		setContentPane(normalPanel);
		validate();
		repaint();
	}

	private int convertToNamed(int num) {
		/*
		 * String newNum = ""; if (num / 1000000000 >= 1) { newNum = (String) (num /
		 * 1000000000) + "Billion"; } else if (num / 1000000 >= 1){ newNum = (String)
		 * (num / 1000000) + "Million"; } else { newNum = (String) num; } return newNum;
		 */
		return num;
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
		private boolean business;

		public ButtonMouseListener(int index, Game game, boolean business) {
			this.index = index;
			this.game = game;
			this.business = business;
		}

		public void mousePressed(MouseEvent e) {
			if (e.getButton() == MouseEvent.BUTTON1) {
				if (business) {
					game.increaseNumOfBusiness((Integer) index, 1);
				} else {
					game.buyUpgrade(index);
				}
			}
		}

	}

	public class UpgradesButtonMouseListener extends MouseAdapter {

		private Display display;

		public UpgradesButtonMouseListener(Display display) {
			this.display = display;
		}

		public void mousePressed(MouseEvent e) {
			if (e.getButton() == MouseEvent.BUTTON1) {
				display.showUpgrades();
			}
		}

	}

	public class ExitUpgradesButtonMouseListener extends MouseAdapter {

		private Display display;

		public ExitUpgradesButtonMouseListener(Display display) {
			this.display = display;
		}

		public void mousePressed(MouseEvent e) {
			if (e.getButton() == MouseEvent.BUTTON1) {
				display.showNormal();
			}
		}

	}

}
