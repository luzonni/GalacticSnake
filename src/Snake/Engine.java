package Snake;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Engine implements Runnable{
	
	public static Thread thread;
	public static boolean Game_Running;
	
	public static BufferedImage GameImage;
	
	public static Engine engine;
	
	public static Window window;
	public static BufferStrategy buffer;
	
	public static Snake snake;
	public static Cherry cherry;
	
	public static int Scale = 3;
	
	public static int XMouse;
	public static int YMouse;
	
	public Engine() {
		//Local de criação
		snake = new Snake(15);
		window = new Window(800, 800);
		GameImage = new BufferedImage(window.getWidth()/Scale, window.getHeight()/Scale, BufferedImage.TYPE_INT_RGB);
		cherry = new Cherry();
		Start();
	}
	
	public synchronized void Start() { 
		thread = new Thread(this);
		Game_Running = true;
		thread.start();
	}
	
	public synchronized void Stop() {
		Game_Running = true;
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void tick() {
		snake.tick();
	}
	
	public void render() {
		if(buffer == null) {
    		buffer = window.getBufferStrategy();
    	}
		
		Graphics g = GameImage.getGraphics();
		
    	g.setColor(new Color(0,0,0,10));
    	g.fillRect(0, 0, window.getWidth(), window.getHeight());
    	
    	//Ui
    	renderUi(g);
    	
    	cherry.render(g);
    	snake.render(g);
    	
    	g = buffer.getDrawGraphics();
    	
    	g.drawImage(GameImage, 0, 0, window.getWidth(), window.getHeight(), null);
    	
    	g.dispose();
    	buffer.show();
	}
	
	private void renderUi(Graphics g) {
		//Star
		g.setColor(new Color(100,100,100, 10));
		g.setFont(new Font("Aria",Font.BOLD, 10));
		g.drawString("Points: "+Snake.Points, 0, 8);
		g.drawString("Stars: "+Cherry.Stars+" / Follow: "+Snake.robo+" / MouseSet: "+Cherry.SetOnMouse , 0, GameImage.getHeight()-1);
	}
	
	public static void main(String[] args) {
		engine = new Engine();
	}

	@Override
	public void run() {
		window.requestFocus();
		//Run
		long lastTime = System.nanoTime();
		double amountOfTicks = 144.0;
		double ns = 1000000000 / amountOfTicks; // 1000000000
		double delta = 0;
		window.requestFocus();
		while(Game_Running) {
			long now = System.nanoTime();
			delta+= (now - lastTime) / ns;
			lastTime = now;
			if(delta >= 1 && delta <= 10) {
				tick();
				render();
				delta--;
			}else {
				while(delta >= 1){
					tick();
					delta--;
				}
			}
		}
		Stop();
	}
	
	

}
