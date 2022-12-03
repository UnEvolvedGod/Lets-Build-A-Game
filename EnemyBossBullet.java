

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class EnemyBossBullet extends GameObject{

	private Handler handler;
	Random r = new Random();
	
	
	//Objects location
	public EnemyBossBullet(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		//randomizes the x vel from -5 to 5
		velX = (r.nextInt(5- -5) + -5);
		velY = 5;
		
	}

	//velocity updated every tick/second
	public void tick() {
		x += velX;
		y += velY;
		
		//cannot leave borders
		//if(y <= 0 || y >= Game.HEIGHT - 48) velY *= -1;
		//if(x <= 0 || x >= Game.WIDTH - 16) velX *= -1;
		
		//removes bullet if it is out of view
		if(y >= Game.HEIGHT) handler.removeObject(this);
		
		handler.addObject(new Trail(x, y, ID.Trail, Color.red, 16, 16, 0.02f, handler));
	}

	//Enemy graphics
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int)x,(int)y,16,16);
		
	}

	//hitbot for enemy
	public Rectangle getBounds() {
		
		return new Rectangle((int)x,(int)y,16,16);
	}
	
	

}
