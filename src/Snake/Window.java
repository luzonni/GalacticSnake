package Snake;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Window extends Canvas{
	
	
	private static final long serialVersionUID = 1L;
	
	public static JFrame frame;
	
	private static int Width;
	private static int Height;

	public Window(int w, int h) {
		Width = w;
		Height = h;
		initFrame();
		addKeyListener(Engine.snake);
		addMouseMotionListener(Engine.snake);
	}

	public void initFrame() {
		frame = new JFrame("Cobrinha");
		frame.add(this);
		this.setPreferredSize(new Dimension(Width, Height));
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
		createBufferStrategy(2);
	}
}
