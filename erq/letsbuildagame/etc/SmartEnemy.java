package erq.letsbuildagame.etc;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class SmartEnemy extends GameObject {

	private Handler handler;
	private GameObject player;

	// Objects location
	public SmartEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);

		this.handler = handler;

		// gets player object
		for (int i = 0; i < handler.object.size(); i++) {
			if (handler.object.get(i).getID() == ID.Player)
				player = handler.object.get(i);
		}

	}

	// velocity updated every tick/second
	public void tick() {
		x += velX;
		y += velY;

		float diffX = x - player.getX() - 16;
		float diffY = y - player.getY() - 16;
		// calculates distance betweeen smartenemy and player
		float distance = (float) Math
				.sqrt((x - player.getX()) * (x - player.getX()) + (y - player.getY()) * (y - player.getY()));

		velX = (float) ((-1 / distance) * diffX);
		velY = (float) ((-1 / distance) * diffY);

		handler.addObject(new Trail(x, y, ID.Trail, Color.green, 16, 16, 0.02f, handler));
	}

	// Enemy graphics
	public void render(Graphics g) {
		g.setColor(Color.green);
		g.fillRect((int) x, (int) y, 16, 16);

	}

	// hitbot for enemy
	public Rectangle getBounds() {

		return new Rectangle((int) x, (int) y, 16, 16);
	}

}
