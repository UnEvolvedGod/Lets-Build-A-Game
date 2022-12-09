package erq.letsbuildagame.etc;

import java.awt.Color;
import java.awt.Graphics;

//Health, score, shop, other on screen info
public class HUD {

	public static float HEALTH = 100;

	// Green and redvalues used to fade color as it gets lower
	private float greenValue = 255;
	private float redValue;

	private int score = 0;
	private int level = 1;
	private int highScore = 0;
	
	public void tick() {

		// Keeps health bar from leaving screen
		HEALTH = Game.clamp(HEALTH, 0, 100);
		greenValue = Game.clamp(greenValue, 0, 255);

		// As health gets lower, it gets less green and more red
		greenValue = HEALTH * 2;
		redValue = 255 - greenValue;
		score++;
		
		if(score > highScore)
			highScore = score;
	}

	// renders everything onto the screen
	public void render(Graphics g) {

		// backround of health bar
		g.setColor(Color.gray);
		g.fillRect(15, 15, 200, 32);

		// health bar
		g.setColor(new Color((int) redValue, (int) greenValue, 0));
		g.fillRect(15, 15, (int) HEALTH * 2, 32);

		// border of health bar
		g.setColor(Color.white);
		g.drawRect(15, 15, 200, 32);

		// Shows game level and score
		g.drawString("Score: " + score, 15, 64);
		g.drawString("Level: " + level, 15, 80);

	}

	// setters and getters

	public int setScore(int score) {
		return this.score = score;
	}

	public int getScore() {
		return score;
	}

	public int getLevel() {
		return level;
	}

	public int setLevel(int level) {
		return this.level = level;
	}
	
	public int getHighScore() {
		return highScore;
	}
}
