package com.liedssna.org.java.main.display;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Display extends JFrame {
	private JLabel label;
	
	public Display(String title) {
		super(title);
		setSize(500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setLayout(new FlowLayout());
		label = new JLabel("HELLO!");
		add(label);
	}

}
