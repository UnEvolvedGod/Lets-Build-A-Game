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

	public Menu(Game game, Handler handler) {
		this.game = game;
		this.handler = handler;
	}

	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();

		// Play button
		if (mouseOver(mx, my, 210, 150, 200, 64)) {
			game.gameState = STATE.Game;

			handler.addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, handler));

			handler.addObject(
					new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));

			// handler.addObject(new HomerEnemy(r.nextInt(Game.WIDTH -
			// 50),r.nextInt(Game.HEIGHT- 50), ID.HomerEnemy, handler));
		}

		// Help Button
		if (mouseOver(mx, my, 210, 250, 200, 64)) {
			game.gameState = STATE.Help;
		}

		// Back Button from help screen

		if (game.gameState == STATE.Help) {
			if (mouseOver(mx, my, 210, 250, 200, 64)) {
				game.gameState = STATE.Help;
			}
		}

		// Quit button
		if (mouseOver(mx, my, 210, 350, 200, 64)) {
			System.exit(1);
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
			g.drawString("Menu", 240, 90);

			g.setFont(fnt1);
			g.setColor(Color.white);
			g.drawString("Play", 280, 190);

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

			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Help", 240, 90);

			g.setColor(Color.white);
			g.drawRect(410, 350, 200, 64);
			g.drawString("Back", 450, 400);
		}

	}

}
