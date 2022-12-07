package erq.letsbuildagame.etc;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class SonicEnemy extends GameObject {

	private Handler handler;
	private GameObject player;
	Color SonicColor = new Color(255, 255, 0);
	private int timer = 100;// timer used to get close, stop, then hyper speed tp player
	private int timer1 = 500;// timer used to get close, stop, then hyper speed tp player


	// Objects location
	public SonicEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);

		this.handler = handler;

		// gets player object
		for (int i = 0; i < handler.object.size(); i++) {
			if (handler.object.get(i).getID() == ID.Player)
				player = handler.object.get(i);
		}
		
		velX = 1;
		velY = 1;

	}

	// velocity updated every tick/second
	public void tick() {
		
		float diffX = x - player.getX() - 16;
		float diffY = y - player.getY() - 16;
		
		// calculates distance betweeen smartenemy and player
		float distance = (float) Math
				.sqrt((x - player.getX()) * (x - player.getX()) + (y - player.getY()) * (y - player.getY()));

		if (timer <= 0) {
			velX = (float) ((-1 / distance) * diffX);
			velY = (float) ((-1 / distance) * diffY);
			x += velX * 4;
			y += velY * 4;
		}else {
			x += velX;
			y += velY;

			timer--;
		}
			if(timer1 <= 0 ) {
				handler.removeObject(this);
			}else {
				timer1--;
			}
		
	
		
		
		if (y <= 0 || y >= Game.HEIGHT - 48)
			handler.removeObject(this);
		if (x <= 0 || x >= Game.WIDTH - 16)
			handler.removeObject(this);
		

		handler.addObject(new Trail(x, y, ID.Trail, SonicColor, 16, 16, 0.05f, handler));
		
	}

	// Enemy graphics
	public void render(Graphics g) {
		g.setColor(SonicColor);
		g.fillRect((int) x, (int) y, 16, 16);

	}

	// hitbot for enemy
	public Rectangle getBounds() {

		return new Rectangle((int) x, (int) y, 16, 16);
	}

}
