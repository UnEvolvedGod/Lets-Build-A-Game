
import java.util.Random;

//Handles the spawning of enemies based on level
public class Spawn {
	
	private Handler handler;
	private HUD hud;
	Random r = new Random();
	private int scoreKeep = 0;
	
	public Spawn(Handler handler, HUD hud) {
		this.handler = handler;
		this.hud = hud;
	}
	
	//Every 250 points, it goes to next level. More enemies spawn every level and new types as well.
	public void tick() {
		scoreKeep++;
		
		//Score keep is what sets level to next level. 
		if(scoreKeep >= 250) {
			scoreKeep = 0;
			hud.setLevel(hud.getLevel() +1);
	
			//Switch is used where every case is a new level, with enemies spawning every level, and new enemies spawning aswell.
			//This is the main point of the game, so it will exspand a lot
			switch(hud.getLevel()) {
				case 2:
						handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50),r.nextInt(Game.HEIGHT- 50), ID.BasicEnemy, handler));
						break;
				case 3:
						handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH),r.nextInt(Game.HEIGHT), ID.FastEnemy, handler));						break;

				case 4:
						handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH),r.nextInt(Game.HEIGHT), ID.SmartEnemy, handler));
						
						break;
				
				
			}
			
			
		
		}
			
		
	}
}
