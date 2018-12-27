package com.gmt.deathgame.level.tile;

import com.gmt.deathgame.graphics.Screen;
import com.gmt.deathgame.graphics.Sprite;

public class GrassTile extends Tile {

	public GrassTile(Sprite sprite) {
		super(sprite);
		// TODO Auto-generated constructor stub
	}
	
	public void render(int x, int y, Screen screen) {
		//Do render stuff here
		screen.renderTile(x << 4, y << 4, this); 	//multiply by 16 to go to tile percesion
	}

}
