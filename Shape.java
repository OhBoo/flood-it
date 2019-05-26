package swaptch;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

public abstract class Shape {
	public abstract double getArea();
    public abstract double getPerimeter();
}

class Rectangle extends Shape {
    double width;
    double height;

    public Rectangle(double width) {
        this.width = this.height = width;
    }

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public double getArea() {
        return width * height;
    }

    public double getPerimeter() {
        return 2 * (width + height);
    }
}

class Square extends Rectangle {
	public Color color;
	
	public Square(double side, Color color) {
        super(side, side);   
        this.color = color;
	}
	
	public Square(double side) {
		super(side, side);
	}

	public void set(Color color) {
		// TODO Auto-generated method stub
		
	}

	public static List<Color> colors() {
		// TODO Auto-generated method stub
		return null;
	}

	public Color getColor() {
		// TODO Auto-generated method stub
		return null;
	}

	public void paint(Graphics g, int i, int j, int squareWidth, int squareHeight, boolean contains) {
		// TODO Auto-generated method stub
		
	}
}