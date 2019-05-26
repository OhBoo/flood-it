package swaptch;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFrame;

public class Gameboard extends GraphicsComponent {

	/**
	 * gameboard provides Gameboard
	 * upperLeftGroup is the blob player needs to change to get one color
	 */
	private Square[][] gameboard; 
	Collection<Square> upperLeftGroup = new HashSet<Square>();
	private List<Color> colors = new ArrayList<Color>();
	
	public static final Color RED = new Color(255, 0, 0);
    public static final Color ORANGE = new Color(255, 165, 0);
    public static final Color YELLOW = new Color(255, 250, 0);
    public static final Color GREEN = new Color(83, 214, 87);
    public static final Color BLUE = new Color(30, 116, 255);
    public static final int NUM_OF_COLORS = 5;
    
    private int movesTaken = 0;

	public Gameboard() {
		
	}
    
    public Gameboard(int width, int height) {
		gameboard = new Square[width][height];
		
		upperLeftGroup.add(gameboard[0][0]);
	}

    public void Gamebord(int width, int height, int numColors) {
        initColors(numColors);
        gameboard = new Square[width][height];
        initSquares();
        initUpperLeftGroup();
      } 
    
    private void initSquares() {
       for (int x = 0; x < getWidth(); x++) {
    	   for (int y = 0; y < getHeight(); y++) {
        	  gameboard[x][y] = new Square(set(colors));
    	   }
       }
    }
    
    private void initColors(int numColors) {
        List<Color> allColors = Square.colors();
        Collections.shuffle(allColors);

        for (int i = 0; i < numColors; i++) {
        	colors.add(allColors.get(i));
        }
    }
    
	private double set(List<Color> colors2) {
		return 0;
	}
	
	private void initUpperLeftGroup() {
	    upperLeftGroup.clear();
	    upperLeftGroup.add(get(0, 0));
	    expandUpperLeftGroup();
	}
	
	public Color getUpperLeftColor() {
	    return get(0, 0).getColor();
	  }
	

	/**
	 * @return size of upperLeftGroup
	 */
	public int getNumInUpperLeftGroup() {
		return upperLeftGroup.size();
	}

	/**
	 * @param color to change to 
	 */
	public void changeUpperLeftGroupToColor(Color color) {
		for (Square square : upperLeftGroup) {
			square.set(color);
		}
		expandUpperLeftGroup();
		movesTaken++;
	}

	/**
	 * add a square if it's the same color as upperLeftGroup AND
	 * if upperLeftGroup does not already contain the square AND
	 * if the square neighbors upperLeftGroup (else, squareWasAdded remains false) 
	 */
	private void expandUpperLeftGroup() {
		boolean squareWasAdded = true;
		do {
			squareWasAdded = false;
			for (int x = 0; x < getWidth(); x++) {
				for (int y = 0; y < getHeight(); y++) {
					Square square = get(x, y);
					for (Square neighbor : getNeighbors(x, y)) {
						if (upperLeftGroup.contains(square) 
								&& neighbor.color.equals(square.color)
								&& !upperLeftGroup.contains(neighbor)) {
							upperLeftGroup.add(neighbor);
							squareWasAdded = true;
						}
					}
				}
			}
		} while (squareWasAdded);
	}

	/**
	 * @return all the squares adjacent to the square at (x, y).
	 */
	private List<Square> getNeighbors(int x, int y) {
	    List<Square> neighbors = new ArrayList<Square>();
		int startPosX = (x - 1 < 0) ? x : x - 1;
	    int startPosY = (y - 1 < 0) ? y : y - 1;
	    int endPosX =   (x + 1 > 0) ? x : x + 1;
	    int endPosY =   (y + 1 > 0) ? y : y + 1;
	    for (int row = startPosX; row <= endPosX; row++) {
	        for (int col = startPosY; col <= endPosY; col++) {
	            neighbors.add(gameboard[row][col]);
	        }
	    }
		return neighbors;
	}
	
	public static boolean exists(int x, int y) {
		return 0 <= x && x <= Gameboard.width && 0 <= y && y <= Gameboard.height;
	}
	
	public boolean containsColor(Color color) {
	    for (int x = 0; x < getWidth(); x++) {
	    	for (int y = 0; y < getHeight(); y++) {
		        if (get(x,y).getColor().equals(color)) {
		          return true;
		        }
	      	}
	    }
	    return false;
	}

	public boolean contains(List<Square> list, Point p) {
		return 0 <= p.x && p.x < ((Gameboard) list).getWidth() && 0 <= p.y && p.y < ((Gameboard) list).getHeight();
	}
	
	public void paint(Graphics g, int width, int height) {
	    int squareWidth = width / getWidth();
	    int squareHeight = height / getHeight();
	    for (int x = 0; x < getWidth(); x++) {
	    	for (int y = 0; y < getHeight(); y++) {
	    		this.get(x, y).paint(g, x * squareWidth, y * squareHeight, squareWidth,
	    				squareHeight, upperLeftGroup.contains(get(x, y)));
	      	}
	    }
	}

	/**
	 * @return if board is one color by comparing 
	 * upperLeftGroup size (which is one color) and gameboard size
	 */
	public boolean finished() {
		return getNumInUpperLeftGroup() == getWidth() * getHeight();
	} 

  /**
   * @return number of squares in a row of the grid (width)
   */
	public int getWidth() {
		return gameboard.length;
	}

  /**
   * @return number of squares in a column of the grid (height)
   */
	public int getHeight() {
		return gameboard[0].length;
	}
	

	public Square get(int x, int y) {
		return gameboard[x][y];
	}
	
 	public List<Color> getColors() {
		return getColors();
	}
	
	public Square[][] setRandomColors() {
		for (int i = 0; i < gameboard.length ; i++) {
			for (int j = 0; j < gameboard[0].length; j++) {
				int color = (int) (Math.random() * 5);
				gameboard[i][j] = convertIntToColor(color);
			}
		}
		return gameboard;
	}
	
	public Square[][] setColors(Color color) {
		for (int i = 0; i < gameboard.length ; i++) {
			for (int j = 0; j < gameboard[0].length; j++) {
				int col = (int) (Math.random() * 5);
				gameboard[i][j] = convertIntToColor(col);
			}
		}
		return gameboard;
	}

	private Square convertIntToColor(int i){
		Square square = new Square(10, RED);
		if (i == 1) square = new Square(10, ORANGE);
		else if (i == 2) square = new Square(10, YELLOW);
		else if (i == 3) square = new Square(10, GREEN);
		else if (i == 3) square = new Square(10, BLUE);
		return square; 
    }

	public void createBoard() {
		JFrame frame = new JFrame("Swaptch");
		frame.setSize(width, height);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
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
			
			Color color = RED;
			if (s.equals("O")) color = ORANGE;
			else if (s.equals("Y")) color = YELLOW;
			else if (s.equals("G")) color = GREEN;
			else if (s.equals("B")) color = BLUE;
			gameboard.changeUpperLeftGroupToColor(color);
			gc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.add(gc);
			System.out.println(movesTaken);
		}
		input.close();
	}
}

