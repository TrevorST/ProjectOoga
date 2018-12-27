package com.gmt.deathgame.entity.mob;

import com.gmt.deathgame.entity.Entity;
import com.gmt.deathgame.graphics.Sprite;

public abstract class Mob extends Entity  {	// abstract means it cannot be instanciated
	
	protected Sprite sprite;				//protected means only classes that extend mob can access it
	protected int dir = 0;
	protected boolean moving = false;
	
	public void move(int xa, int ya) {
		if(xa > 0) dir = 1;					//east
		if(xa < 0) dir = 3;					//west
		if(ya > 0) dir = 2;					//south
		if(ya < 0) dir = 0;					//north
		
		if(!collision()) {
			x += xa;						//actually moves the player
			y += ya;
			
		}
	}
	
	public void update() {
		
	}
	
	private boolean collision() {
		return false;
	}
	
	public void render() {
		
	}

}
