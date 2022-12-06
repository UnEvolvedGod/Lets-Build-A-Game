import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Game.STATE;

public class Menu extends MouseAdapter{

	Game game;
	
	public Menu(Game game) {
		this.game = game;
	}
	
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		
		
		if(mouseOver(mx, my, 210, 150, 200, 64)) {
			game.gameState = STATE.Game;
		}
	}
	
	public void mouseReleased(MouseEvent e) {
		
	}
	
	//Checks if mouse is hovering over a button
	private boolean mouseOver(int mx, int my,int x, int y, int width, int height) {
		if(mx > x && mx < x + width) {
			if(my>y && my < y+ height) {
				return true;
			}else return false;
		}else return false;
		
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		Font fnt = new Font("arial", 1, 50);
		Font fnt1 = new Font("arial", 1, 30);
		
		g.setFont(fnt);
		g.setColor(Color.white);
		g.drawString("Menu", 240, 70);
		
		g.setFont(fnt1);
		g.setColor(Color.white);
		g.drawString("Play", 280, 190);
		
		g.setFont(fnt1);
		g.setColor(Color.white);
		g.drawString("Help", 280, 290);
		
		g.setFont(fnt1);
		g.setColor(Color.white);
		g.drawString("Quit", 280, 390);
		
		g.setColor(Color.white);
		g.drawRect(210, 150, 200, 64);
		
		g.setColor(Color.white);
		g.drawRect(210, 250, 200, 64);
		
		g.setColor(Color.white);
		g.drawRect(210, 350, 200, 64);
		
		
	}
	
}
