package erq.letsbuildagame.etc;

import java.util.Random;
import java.awt.*;

public class Player extends GameObject {

	Random r = new Random();
	Handler handler;

	// Object location and id
	public Player(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;

	}

	// velocity updated every tick/second
	public void tick() {
		x += velX;
		y += velY;

		// Prevents player object from leaving borders
		x = Game.clamp(x, 0, Game.WIDTH - 37);
		y = Game.clamp(y, 0, Game.HEIGHT - 60);

		// If "Player collides with something, event occurs"
		collison();

		handler.addObject(new Trail(x, y, ID.Trail, Color.white, 32, 32, 0.2f, handler));
		// Trail added to player
	}

	// method used for collsion
	private void collison() {
		// runs throgh all object in the game
		for (int i = 0; i < handler.object.size(); i++) {
			// all objects
			GameObject tempObject = handler.object.get(i);

			// if an object has tag of "BasicEnemy" something happens below
			if (tempObject.getID() == ID.BasicEnemy || tempObject.getID() == ID.FastEnemy
					|| tempObject.getID() == ID.SmartEnemy || tempObject.getID() == ID.RepeaterEnemy) {

				// if "Hitbox"(bounds) hits(intersects) the "BasicEnemy" hitbox(bound),
				// something occurs
				if (getBounds().intersects(tempObject.getBounds())) {
					// collsion code
					HUD.HEALTH -= 2;
				}
				// if the player ever gets close enough to intersect with the boss, they will
				// auto die
			} else if (tempObject.getID() == ID.EnemyBoss) {
				if (getBounds().intersects(tempObject.getBounds())) {
					// collsion code
					HUD.HEALTH = 0;
				}
			} else if (tempObject.getID() == ID.HomerEnemy) {
				if (getBounds().intersects(tempObject.getBounds())) {
					// collsion code
					HUD.HEALTH -= 1;
				}

			}
		}

	}

	// Shows player object onto screen
	public void render(Graphics g) {

		g.setColor(Color.white);
		g.fillRect((int) x, (int) y, 32, 32);

	}

	// Sets hitbox for player
	public Rectangle getBounds() {

		return new Rectangle((int) x, (int) y, 32, 32);
	}

}
