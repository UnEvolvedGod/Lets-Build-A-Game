package erq.letsbuildagame.etc;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class FastEnemy extends GameObject{

	private Handler handler;
	
	//Objects location
	public FastEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		//Faster speed since it is "Faster Enemy"
		velX = 2;
		velY = 9;
		
	}

	//velocity updated every tick/second
	public void tick() {
		x += velX;
		y += velY;
		
		//cannot leave borders
		if(y <= 0 || y >= Game.HEIGHT - 48) velY *= -1;
		if(x <= 0 || x >= Game.WIDTH - 16) velX *= -1;
		
		handler.addObject(new Trail(x, y, ID.Trail, Color.CYAN, 12, 12, 0.02f, handler));
	}

	//Enemy graphics
	public void render(Graphics g) {
		g.setColor(Color.CYAN);
		g.fillRect((int)x,(int)y,12,12);
		
	}

	//hitbot for enemy
	public Rectangle getBounds() {
		
		return new Rectangle((int)x,(int)y,16,16);
	}
	
	

}
