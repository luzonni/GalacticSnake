package Snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class Snake implements KeyListener, MouseMotionListener {
	
	public int x;
	public int y;
	
	private String walk = "right";
	
	public static boolean robo;
	
	public static int Points;
	
	public static Color color = new Color(255, 255, 255);
	
	public Snake(int lenght) {
	}
	
	public void tick() {
		
		if(robo) 
			auto();
		else
			switch(walk) {
				case "up": y--;
				break;
				case "down": y++;
				break;
				case "left": x--;
				break;
				case "right": x++;
				break;
			}
		
		if(x > Engine.GameImage.getWidth()-1) {
			x = 0;
		}else if(x < 0) {
			x = Engine.GameImage.getWidth()-1;
		}
		if(y > Engine.GameImage.getHeight()-1) {
			y = 0;
		}else if(y < 0) {
			y = Engine.GameImage.getHeight()-1;
		}
		Engine.cherry.collision(x, y);
		
	}
	
	public void auto() {
		
		if(this.x > Engine.cherry.getX() & walk != "right") {
			walk = "left";
			x--;
		}
		
		if(this.x < Engine.cherry.getX() & walk != "left") {
			walk = "right";
			x++;
		}
		
		if(this.y > Engine.cherry.getY() & walk != "down") {
			walk = "up";
			y--;
		}
		if(this.y < Engine.cherry.getY() & walk != "up") {
			walk = "down";
			y++;
		}
	}
	
	public void render(Graphics g) {
		g.setColor(color);
		g.fillRect(x, y, 1, 1);
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		
	}



	@Override
	public void keyPressed(KeyEvent e) {
		//Retorana o tipo e se foi apertada;
		if(e.getKeyCode() == KeyEvent.VK_UP && walk != "down") {
			walk = "up";
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN && walk != "up") {
			walk = "down";
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT && walk != "right") {
			walk = "left";
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT && walk != "left") {
			walk = "right";
		}
		if(e.getKeyCode() == KeyEvent.VK_2 && !robo) {
			robo = true;
		}else if(e.getKeyCode() == KeyEvent.VK_2 && robo) {
			robo = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_3 && !Cherry.SetOnMouse) {
			Cherry.SetOnMouse = true;
		}else if(e.getKeyCode() == KeyEvent.VK_3 && Cherry.SetOnMouse) {
			Cherry.SetOnMouse = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_1 && !Cherry.Stars) {
			Cherry.Stars = true;
		}else if(e.getKeyCode() == KeyEvent.VK_1 && Cherry.Stars) {
			Cherry.Stars = false;
		}
	}



	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		Engine.XMouse = e.getX()/Engine.Scale;
		Engine.YMouse = e.getY()/Engine.Scale;
	}

}
