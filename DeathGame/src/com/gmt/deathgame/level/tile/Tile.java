package com.gmt.deathgame.level.tile;

import com.gmt.deathgame.graphics.Screen;
import com.gmt.deathgame.graphics.Sprite;

public class Tile {

	public int x, y;
	public Sprite sprite;
	
	public static Tile grass = new GrassTile(Sprite.grass);
	public static Tile voidTile = new VoidTile(Sprite.voidSprite);
	
	public Tile(Sprite sprite) {
		this.sprite = sprite;
	}
	
	public void render(int x, int y, Screen screen) {
				//how the tile is rendered
	}
	
	public boolean solid() {
		return false;
	}
}
