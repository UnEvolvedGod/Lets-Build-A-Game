package erq.letsbuildagame.etc;


import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable{

	private static final long serialVersionUID = 1550691097823471818L;

	//Good aspect ratio for window
	public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;
	
	//Declaring everything
	private Thread thread;
	private boolean running = false;
	Random r = new Random();
	private HUD hud;
	private Spawn spawner;
	private Menu menu;
	private Handler handler;
	
	
	/*When the program is run there are 2 states,
	 * The Menu state and the Game state.
	 * During menu state they will be able to pause 
	 * the game, buy items, etc. During the Game 
	 * state, this is where the gameplay is active.
	 * While Menu state is active, the Game state is unactive
	 * and vise versa. */
	public enum STATE {
		Menu,
		Help,
		Game
	};
	
	public STATE gameState = STATE.Menu;
	
	public Game() {
		//handles all objects
		handler = new Handler();
		menu = new Menu(this, handler);
		
		//handles user input
		this.addKeyListener(new KeyInput(handler));
		this.addMouseListener(menu);
		
		//Window game is played on
		new Window(WIDTH, HEIGHT, "Let's Build a Game!", this);	
		
		//health bar and other hud things
		hud = new HUD();
		
		//Handles spawns for different levels 
		spawner = new Spawn(handler, hud);
		
		if(gameState == STATE.Menu) {
			for(int i = 0; i < 20; i++) {
				handler.addObject(new MenuParticle(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.MenuParticle, handler));

			}
			 
		}
		
		
		
		
		
	}
	
	//Starts game
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	//stops game
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//tracks fps and other info
	public void run() {
		/*
		 * "lastTime", "now," and "ns" are used to calculate "delta."
		 * "amountOfTicks" is the amount of tics/second
		 * "ns" is the amount of nanoseconds/tick
		 * 
		 * When delta is calculated, you have (now-lastTime)/(ns/tick), 
		 * but now and lastTime  are in nanoseconds, so it has units 
		 * "tick". We then add this to delta, and keep going.
		 * 
		 * Whenever delta+=1, one tick has passed, and we therefore call the command tick()
		 * and reset delta to 0 in the while(delta>=1) loop.
		 * 
		 * the if(running) loop updates the window (by rendering again), and increases the frames with 1.
		 * 
		 * Since this event happens once every second, the value "frames" is the frames per second.
		 * 
		 * Shorter: The game loop basically gets the current time, and waits for the game to "tick". 
		 * The delta gets the difference between the now and lastTime variable. Of course, 
		 * time always changes, so the lastTime would be the now var before it changed. 
		 * BTW delta means the change in two variables of similar units. 
		 * Example: 100 feet and 50 feet. the delta is 50 feet (100 - 50)
		 * 
		 * */
		
		//auto focus on screen when program is run
		this.requestFocus();
		
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >=1) {
				tick();
				delta--;
			}
			if(running)
				render();
			frames++;
			
			if(System.currentTimeMillis() - timer >1000) {
				timer += 1000;
						System.out.println("FPS: "+frames);
						frames = 0;
			}
		}
		stop();
		
	}
	
	//renders everything onto the screen
	private void render() {
		
		//Creates 3 buffers within the game
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		//size of window
		g.setColor(Color.black);
		g.fillRect(0,0, WIDTH, HEIGHT);
		
		
		handler.render(g);
		
		//Runs game
		if(gameState == STATE.Game) {
			hud.render(g);
		}else if(gameState == STATE.Menu || gameState == STATE.Help) {
			menu.render(g);
		}
		
		
		
		g.dispose();
		bs.show();
		
		
	}

	//keeps objects updated every tick/second
	private void tick() {
		handler.tick();
		
		//Runs game stops menu
		if(gameState == STATE.Game) {
			hud.tick();
			spawner.tick();
		}else if(gameState == STATE.Menu) {
			menu.tick();
		}
		
	}
	
	//Sets borders for certain objects so they dont leave the screen
	//Used by: Player, health bar
	public static float clamp(float var, float min, float max) {
		if(var >= max) {
			return var= max;
		}else if(var <= min) {
			return var = min;
		}else {
			return var;
		}
	}
	
	//main method starts game
	public static void main(String[] args) {
		new Game();

	}

	

	
	

}
