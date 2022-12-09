package erq.letsbuildagame.etc;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import erq.letsbuildagame.etc.Game.STATE;

//Handles input from user
public class KeyInput extends KeyAdapter {

	private Handler handler;
	private boolean[] keyDown = new boolean[4];

	Game game;

	// handles user input
	public KeyInput(Handler handler, Game game) {
		this.handler = handler;
		this.game = game;

		// Keeps track if key is pressed or not pressed, should get rid of sticky keys
		keyDown[0] = false;
		keyDown[1] = false;
		keyDown[2] = false;
		keyDown[3] = false;
	}

	// If w,s,a,d, keys are pressed, object with "player id" will move with set
	// velocity
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		// reads through all objects looking for one with "player id"
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

			if (tempObject.getID() == ID.Player) {
				// keyevents for player 1

				if (key == KeyEvent.VK_W) {
					tempObject.setVelY(-5);
					keyDown[0] = true;
				}
				if (key == KeyEvent.VK_S) {
					tempObject.setVelY(5);
					keyDown[1] = true;
				}
				if (key == KeyEvent.VK_D) {
					tempObject.setVelX(5);
					keyDown[2] = true;
				}
				if (key == KeyEvent.VK_A) {
					tempObject.setVelX(-5);
					keyDown[3] = true;
				}
			}

		}
		// used so if you press escape you close program
		if (key == KeyEvent.VK_P) {

			if (game.gameState == STATE.Game) {
				if (Game.paused)
					Game.paused = false;
				else
					Game.paused = true;
			}
		}

		if (key == KeyEvent.VK_ESCAPE)
			System.exit(1);
	}

	// when w,s,a,d, keys are released, it stops object with
	// "Player ID" from moving, setting velocity to 0
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		// reads through all objects looking for one with "player id"
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

			if (tempObject.getID() == ID.Player) {
				// keyevents for player 1

				if (key == KeyEvent.VK_W)
					keyDown[0] = false;// tempObject.setVelY(0);
				if (key == KeyEvent.VK_S)
					keyDown[1] = false;// tempObject.setVelY(0);
				if (key == KeyEvent.VK_D)
					keyDown[2] = false;// tempObject.setVelX(0);
				if (key == KeyEvent.VK_A)
					keyDown[3] = false;// tempObject.setVelX(0);

				// Should help with fixing sticky keys

				// vertical movement
				if (!keyDown[0] && !keyDown[1]) {
					tempObject.setVelY(0);
				}

				// horizontal movement
				if (!keyDown[2] && !keyDown[3]) {
					tempObject.setVelX(0);
				}
			}

		}
	}

}
