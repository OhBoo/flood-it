package swaptch;

import java.awt.Color;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;

public class GraphicsComponent extends JFrame {
	public static int width;
	public static int height;
	
	public GraphicsComponent() {
		JButton redButton = new JButton();
		redButton.setSize(40, 40);
		redButton.setBackground(Color.RED);
		redButton.setOpaque(true);
		redButton.setBorderPainted(false);
		redButton.setLocation(0, 0);
		
		JButton orangeButton = new JButton();
		orangeButton.setSize(40, 40);
		orangeButton.setBackground(Color.ORANGE);
		orangeButton.setOpaque(true);
		orangeButton.setBorderPainted(false);
		orangeButton.setLocation(40, 0);
		
		JButton yellowButton = new JButton();
		yellowButton.setSize(40, 40);
		yellowButton.setBackground(Color.YELLOW);
		yellowButton.setOpaque(true);
		yellowButton.setBorderPainted(false);
		yellowButton.setLocation(80, 0);
		
		JButton greenButton = new JButton();
		greenButton.setSize(40, 40);
		greenButton.setBackground(Color.GREEN);
		greenButton.setOpaque(true);
		greenButton.setBorderPainted(false);
		greenButton.setLocation(120, 0);
		
		JButton blueButton = new JButton();
		blueButton.setSize(40, 4);
		blueButton.setBackground(Color.BLUE);
		blueButton.setOpaque(true);
		blueButton.setBorderPainted(false);
		blueButton.setLocation(160, 0);

		setTitle("Swaptch");
		setSize(500, 500);
		add(redButton);
		add(orangeButton);
		add(yellowButton);
		add(greenButton);
		add(blueButton);
	}
	
	public static void main(String[] args){		
		Scanner input = new Scanner(System.in);
		System.out.println("Enter desired width of gameboard");
		if (input.hasNext()) width = input.nextInt();
		while (width <= 1) {
			System.out.println("Don't be ridiculous.");
			System.out.println("Enter a number greater than 1.");
			width = input.nextInt();
		}

		System.out.println("Enter desired height of gameboard");
		if (input.hasNext()) height = input.nextInt();
		while (height <= 1) {
			System.out.println("Don't be ridiculous.");
			System.out.println("Enter a number greater than 1.");
			height = input.nextInt();
		}
	
		Gameboard gameboard = new Gameboard(width, height);
		GraphicsComponent gc = new GraphicsComponent();
		
		System.out.println("R for red");
		System.out.println("O for orange");
		System.out.println("Y for yellow");
		System.out.println("G for green");
		System.out.println("B for blue");
		while (!gameboard.finished()) {
			gc.setVisible(true);
			System.out.println("Enter color (R, O, Y, G, or B):");
			String s = input.next();
			
			Color color = Gameboard.RED;
			if (s.equals("O")) color = Gameboard.ORANGE;
			else if (s.equals("Y")) color = Gameboard.YELLOW;
			else if (s.equals("G")) color = Gameboard.GREEN;
			else if (s.equals("B")) color = Gameboard.BLUE;
			gameboard.changeUpperLeftGroupToColor(color);
			gameboard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
		input.close();
	}
}