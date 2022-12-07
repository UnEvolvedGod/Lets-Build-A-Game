package erq.letsbuildagame.etc;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class EnemyBoss extends GameObject {

	private Handler handler;
	private int timer = 60;// timer used to bring boss to middle of the screen
	private int timer2 = 50;// timer used for when boss goes horizontal
	Random r = new Random();

	Color bossColor = new Color(128, 0, 0);

	// Objects location
	public EnemyBoss(int x, int y, ID id, Handler handler) {
		super(x, y, id);

		this.handler = handler;
		velX = 0;
		velY = 2;

	}

	// velocity updated every tick/second
	public void tick() {
		x += velX;
		y += velY;

		// boss goes to center of the screen, once the timer reaches 0, boss vel will
		// stop, so in 80 ticks
		// the boss will stop around the upper middle of the screeen
		if (timer <= 0)
			velY = 0;
		else
			timer--;

		// a few ticks after the boss reaches the middle of the screen, they will begin
		// to go left and right
		if (timer <= 0)
			timer2--;
		if (timer2 <= 0) {
			// begins boss horizontal movement
			if (velX == 0)
				velX = 2;

			// gradually increase speed of boss
			if (velX > 0)
				velX += 0.005f;
			else if (velX < 0)
				velX -= 0.005f;

			// sets max speed to 10/-10
			velX = Game.clamp(velX, -10, 10);

			// Boss will begin spawning random bullets everywhere
			int spawn = r.nextInt(10);

			// if no bullets are present, one will spawn
			if (spawn == 0)
				handler.addObject(new EnemyBossBullet((int) x + 48, (int) y + 48, ID.BasicEnemy, handler));
		}

		// cannot leave borders
		// if(y <= 0 || y >= Game.HEIGHT - 32) velY *= -1;
		if (x <= -62 || x >= Game.WIDTH - 62)
			velX *= -1;

		// handler.addObject(new Trail(x, y, ID.Trail, Color.red, 96, 96, 0.008f,
		// handler));
	}

	// Enemy graphics
	public void render(Graphics g) {
		g.setColor(bossColor);
		g.fillRect((int) x, (int) y, 96, 96);

	}

	// hitbot for enemy
	public Rectangle getBounds() {

		return new Rectangle((int) x, (int) y, 96, 96);
	}

}
