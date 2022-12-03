package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class BasicEnemy extends GameObject{

	private Handler handler;
	
	//Objects location
	public BasicEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		velX = 5;
		velY = 5;
		
	}

	//velocity updated every tick/second
	public void tick() {
		x += velX;
		y += velY;
		
		//cannot leave borders
		if(y <= 0 || y >= Game.HEIGHT - 48) velY *= -1;
		if(x <= 0 || x >= Game.WIDTH - 16) velX *= -1;
		
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
