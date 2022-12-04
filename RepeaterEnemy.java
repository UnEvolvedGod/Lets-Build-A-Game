

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class RepeaterEnemy extends GameObject{

	private Handler handler;
	
	//Objects location
	public RepeaterEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		velX = 3;
		velY = 1;
		
	}

	//velocity updated every tick/second
	public void tick() {
		x += velX;
		y += velY;
		
		//If enemy hits right wall, it will come back from the left
		if(y <= 0 || y >= Game.HEIGHT - 48) velY *= -1;
		if(x <= 0 )  velX *= -1;
		if( x >= Game.WIDTH - 16) {
			x = 0;
		}
		
		
		
		
		/*
		if(y <= 0 || y >= Game.HEIGHT - 48 ||x <= 0 || x >= Game.WIDTH - 16) {
			handler.removeObject(this);
			handler.addObject(new RepeaterEnemy((int)x *-16, (int)y * -48, ID.RepeaterEnemy, handler));
			
		}*/
		
		handler.addObject(new Trail(x, y, ID.Trail, Color.magenta, 16, 16, 0.02f, handler));
	}

	//Enemy graphics
	public void render(Graphics g) {
		g.setColor(Color.magenta);
		g.fillRect((int)x,(int)y,16,16);
		
	}

	//hitbot for enemy
	public Rectangle getBounds() {
		
		return new Rectangle((int)x,(int)y,16,16);
	}
	
	

}
