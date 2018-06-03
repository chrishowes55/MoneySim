package com.liedssna.org.java.main.display;

import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;

import com.liedssna.org.java.main.game.Game;
import com.liedssna.org.java.main.game.Player;

public class Display extends JFrame {
	
	private Game game;
	private JLabel label;
	
	public Display(String title, Player player, Game game) {
		super(title);
		this.game = game;
		setSize(500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		KeyListener listener = new MyKeyListener();
		addKeyListener(listener);
		setFocusable(true);
		
		setLayout(new FlowLayout());
		label = new JLabel(player.getName() + " has £" + player.getMoney());
		add(label);
	}
	
	public class MyKeyListener implements KeyListener {

		@Override
		public void keyTyped(KeyEvent e) {
		}

		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				System.out.println("ESCAPE");
				game.setGameIsRunning(false);
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
		}
		
	}

}
