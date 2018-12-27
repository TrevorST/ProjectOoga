package com.gmt.deathgame.level.tile;

import com.gmt.deathgame.graphics.Screen;
import com.gmt.deathgame.graphics.Sprite;

public class VoidTile extends Tile {

	public VoidTile(Sprite sprite) {
		super(sprite);
	}
	
	public void render(int x, int y, Screen screen) {
		//Do render stuff here
		screen.renderTile(x << 4, y << 4, this); 
	}

}
