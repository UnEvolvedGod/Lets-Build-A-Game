package erq.letsbuildagame.etc;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Trail extends GameObject {

	Handler handler;
	private float alpha = 1;
	private Color color;
	private int width;
	private int height;
	private float life;

	// Allows you to add trails to any object
	public Trail(float x, float y, ID id, Color color, int width, int height, float life, Handler handler) {
		super(x, y, id);
		this.color = color;
		this.handler = handler;
		this.width = width;
		this.height = height;
		this.life = life;
	}

	// Makes trail fade away
	public void tick() {
		if (alpha > life) {
			alpha -= (life - 0.0001f);

		} else {
			handler.removeObject(this);
		}

	}

	// Makes trail
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setComposite(makeTransparent(alpha));

		g.setColor(color);
		g.fillRect((int) x, (int) y, width, height);

		g2d.setComposite(makeTransparent(1));

	}

	// Renders transparent trails
	private AlphaComposite makeTransparent(float alpha) {
		int type = AlphaComposite.SRC_OVER;
		return (AlphaComposite.getInstance(type, alpha));
	}

	// trail does not use hitbox so its null
	public Rectangle getBounds() {

		return null;
	}

}
