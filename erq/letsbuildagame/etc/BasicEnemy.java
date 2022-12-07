package erq.letsbuildagame.etc;

/*	Enemy Ideas
 * 
 * HomerEnemy: low-tracking homing missile *SUCCESS, Homer works as intended but will be improved on*
 * 
 * SonicEnemy: Super sonic tracker with delay
 * 
 * SpliceEnemy: Enemy splits during unknown time
 * 
 * EnemyBossTandem: 2 enemies work together ping pong while your in middle 
 * with bullets getting stronger if they hit partner, 
 * 
 * SlowEnemy: Large enemy that slows you and dmg over timer
 * 
 * ScramblerEnemy:If hit by movement flipped
 * 
 * DefectorEnemy: If you hit helpenemy, they kill other enemies for 
 * certain amount of time
 * 
 * Enemy with 2 weapons
 * 
 * 
 * ENEMIES YOU VERSE HAVE SIMILAR THEME TO BOSS THEY WILL VERSE
 * 
 * 
 * Shop items ideas:
 * 
 * Time bomb: slows enemies for few seconds
 * 
 * Portal: teleport
 * 
 * Invisible: invisible for a few seconds
 * 
 * Scatter bomb: bomb spreads
 * 
 * Atomic bomb: Kills all enemies, only gotten in certain instances 
 * 
 * Knock back: pushes enemies away from you
 * 
 * Freeze: Freeze enemies 
 * 
 * Become worm: Gain extra lives/heads
 * 
 * Decoy: Enemies chase fake 
 * 
 * */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class BasicEnemy extends GameObject {

	private Handler handler;

	// Objects location
	public BasicEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);

		this.handler = handler;
		velX = 5;
		velY = 5;

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

		handler.addObject(new Trail(x, y, ID.Trail, Color.red, 16, 16, 0.02f, handler));
	}

	// Enemy graphics
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int) x, (int) y, 16, 16);

	}

	// hitbot for enemy
	public Rectangle getBounds() {

		return new Rectangle((int) x, (int) y, 16, 16);
	}

}
