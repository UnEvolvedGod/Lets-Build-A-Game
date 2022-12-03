

import java.awt.Graphics;
import java.awt.Rectangle;

//Everything in game will be considered a GameObject
public abstract class GameObject {
	
	protected float x, y;
	protected ID id;
	protected float velX, velY;
	
	//location of all objects in game
	public GameObject(float x, float y, ID id) {
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	//updates all objects and renders them onto the screen
	public abstract void tick();
	public abstract void render(Graphics g);
	
	//Handles all collisions
	public abstract Rectangle getBounds();
	
	//x, y, velX, velY, all set location and speed the objects go, 
	//ID is used to identify different objects
	
	
	//setters and getters 
	
	public void setX(int x) {
		this.x = x;
	}
	 public void setY(int y) {
		 this.y = y;
		 
	 }
	 public float getX() {
		 return x;
	 }
	 public float getY() {
		 return y;
	 }
	 
	 public void setID( ID id) {
		 this.id = id;
	 }
	 public ID getID() {
		 return id;
	 }
	 public void setVelX(int velX) {
		 this.velX = velX;
	 }
	 public void setVelY(int velY) {
		 this.velY = velY;
	 }
	 public float getVelX() {
		 return velX;
	 }
	 public float getVelY() {
		 return velY;
	 }
	 
}
