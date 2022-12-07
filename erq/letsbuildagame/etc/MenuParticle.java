package erq.letsbuildagame.etc;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class MenuParticle extends GameObject {

	private Handler handler;
	Random r = new Random();
	Color color;

	// Objects location
	public MenuParticle(int x, int y, ID id, Handler handler) {
		super(x, y, id);

		this.handler = handler;

		velX = (r.nextInt(14) + -7);
		velY = (r.nextInt(14) + -7);
		if (velX == 0)
			velX = 1;
		if (velY == 0)
			velY = 1;

		color = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));

	}

	// velocity updated every tick/second
	public void tick() {
		x += velX;
		y += velY;

		// cannot leave borders
		if (y <= 0 || y >= Game.HEIGHT - 48)
			velY *= -1;
		if (x <= 0 || x >= Game.WIDTH - 16)
			velX *= -1;

		//https://www.youtube.com/watch?v=QgQUt3nuBx4 9:26
		handler.addObject(new Trail(x, y, ID.Trail, color, 12, 12, 0.05f, handler));
	}

	// Enemy graphics
	public void render(Graphics g) {
		g.setColor(color);
		g.fillRect((int) x, (int) y, 12, 12);

	}

	// hitbot for enemy
	public Rectangle getBounds() {

		return new Rectangle((int) x, (int) y, 16, 16);
	}

}
