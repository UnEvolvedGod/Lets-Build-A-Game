package erq.letsbuildagame.etc;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import erq.letsbuildagame.etc.Game.STATE;

public class Menu extends MouseAdapter {

	private Game game;
	private Handler handler;
	private Random r = new Random();
	private GameObject player;
	private int timer = 100;// timer used to get close, stop, then hyper speed tp player


	public Menu(Game game, Handler handler) {
		this.game = game;
		this.handler = handler;
		
		for (int i = 0; i < handler.object.size(); i++) {
			if (handler.object.get(i).getID() == ID.Player)
				player = handler.object.get(i);
		}
	}

	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();

		if (game.gameState == STATE.Menu) {

			// Play button
			if (mouseOver(mx, my, 210, 150, 200, 64)) {

				game.gameState = STATE.Game;

				handler.addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, handler));

				handler.clearEnemies();

				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy,
						handler));

			}

			// Help Button
			if (mouseOver(mx, my, 210, 250, 200, 64)) {
				game.gameState = STATE.Help;
			}

			// Quit button
			if (mouseOver(mx, my, 210, 350, 200, 64)) {
				System.exit(1);
			}

		}

		// Back Button from help screen
		if (game.gameState == STATE.Help) {
			if (mouseOver(mx, my, 410, 350, 200, 64)) {
				game.gameState = STATE.Menu;
				return;
			}
		}

	}

	public void mouseReleased(MouseEvent e) {

	}

	// Checks if mouse is hovering over a button
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		if (mx > x && mx < x + width) {
			if (my > y && my < y + height) {
				return true;
			} else
				return false;
		} else
			return false;

	}

	public void tick() {
		
	}

	public void render(Graphics g) {
		if (game.gameState == STATE.Menu) {

			Font fnt = new Font("arial", 1, 50);
			Font fnt1 = new Font("arial", 1, 30);

			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Scooby Doo", 170, 90);

			g.setFont(fnt1);
			g.setColor(Color.white);
			g.drawString("Start", 280, 190);

			g.setFont(fnt1);
			g.setColor(Color.white);
			g.drawString("Help", 280, 290);

			g.setFont(fnt1);
			g.setColor(Color.white);
			g.drawString("Quit", 280, 390);

			g.setColor(Color.white);
			g.drawRect(210, 150, 200, 64);

			g.setColor(Color.white);
			g.drawRect(210, 250, 200, 64);

			g.setColor(Color.white);
			g.drawRect(210, 350, 200, 64);

		} else if (game.gameState == STATE.Help) {

			Font fnt = new Font("arial", 1, 50);
			Font fnt1 = new Font("arial", 1, 25);

			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("How to Play", 170, 90);

			g.setFont(fnt1);
			g.setColor(Color.white);
			g.drawString("Use WASD keys to move and dodge enemies", 65, 150);
			g.drawString("Earn points for every second alive", 110, 195);
			g.drawString("Survive as long as possible", 150, 240);
			g.drawString("Good luck", 240, 285);

			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawRect(410, 350, 200, 64);
			g.drawString("Back", 450, 400);
		}

	}

}
