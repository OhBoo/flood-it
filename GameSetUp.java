package swaptch;

import java.awt.Color;

public class GameSetUp {
    public static int width;
	public static int height;
    private int maxSteps;
    private Color[] squares;

    public GameSetUp(int width, int height){
		GameSetUp.width = width;
		GameSetUp.height = height;
    }

    public int getWidth() {
    	return width;
    }

    public int getHeight() {
    	return height;
    }

    public int getNumberOfSquares(){
    	return squares.length;
    }
    
    public Color getSquareColor(int x, int y){
		int index = convertPointToIndex(x, y);
		return squares[index];
    }
    
    public int getMaxSteps() {
        return maxSteps;
    }
    
    public void setSquareColor(int x, int y, Color color){
		int index = convertPointToIndex(x, y);
    	squares[index] = color;
    }

    public int convertPointToIndex(int x, int y){
    	return y * width + x;
    }
}