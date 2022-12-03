

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Window extends Canvas {

	private static final long serialVersionUID = -240840600533728354L;
	
	public Window(int width, int height, String title, Game game) {
		
		
		//Frame of our window
		JFrame frame = new JFrame(title);
		
		//Setting size of frame, along with max and min size
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		
		//Needed to make sure window exits when closed
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//window will not change size
		frame.setResizable(false);
		
		//Window starts in middle of screen with 'null'
		frame.setLocationRelativeTo(null);
		
		//Adds our "Game" class into our Frame
		frame.add(game);
		
		//Window is visible
		frame.setVisible(true);
		
		//Runs start method in Game class
		game.start();
		
	}

	

}
