package erq.letsbuildagame.etc;

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {
	
	//List of all objects in game
	LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	//Runs through all game objects
	public void tick() {
		for(int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			
			tempObject.tick();
		}
	}
	
	//Render all game objects
	public void render(Graphics g) {
		for(int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			
			tempObject.render(g);
			
		}
	}
	
	//add object into game
	public void addObject(GameObject object) {
		this.object.add(object);
	}
	
	//remove object from game
	public void removeObject(GameObject object) {
		this.object.remove(object);
	}

	public void clearEnemies() {
		for(int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			
			if(tempObject.getID() != ID.Player) {
				object.clear();
				
				addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, this));
				
				
			}
			
		}
		
	}
}
