package Snake;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Cherry {
	
	public int X;
	public int Y;
	
	public static boolean SetOnMouse;
	
	public static boolean Stars;
	
	public Random rand = new Random();
	
	public static Color color = new Color(255,255,255);
	
	public Cherry() {
		this.X = rand.nextInt(Engine.GameImage.getWidth()-1);
		this.Y = rand.nextInt(Engine.GameImage.getHeight()-1);
		color = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
	}
	
	public void collision(int x, int y) {
		if(x == this.X && y == this.Y) {
			if(SetOnMouse) {
				this.X = Engine.XMouse;
				this.Y = Engine.YMouse;
			}else {
				this.X = rand.nextInt(Engine.GameImage.getWidth());
				this.Y = rand.nextInt(Engine.GameImage.getHeight());
			}
			Snake.Points++;
			color = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
			Snake.color = color;
		}
		 if(Stars) {
		 	this.X = rand.nextInt(Engine.GameImage.getWidth());
			this.Y = rand.nextInt(Engine.GameImage.getHeight());
		}
		if(SetOnMouse) {
			this.X = Engine.XMouse;
			this.Y = Engine.YMouse;
		}
	}
	
	public int getX() {
		return this.X;
	}
	
	public int getY() {
		return this.Y;
	}
	
	public void render(Graphics g) {
		g.setColor(color);
		g.fillRect(X, Y, 1, 1);
	}


}
