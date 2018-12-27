package com.gmt.deathgame.level;

import com.gmt.deathgame.graphics.Screen;
import com.gmt.deathgame.level.tile.Tile;

public class Level {
	
	protected int width, height;
	protected int[] tiles;
	
	public Level(int width, int height) {
		this.width = width;
		this.height = height;
		tiles = new int[width*height];
		generateLevel();
	}
	
	public Level(String path) {	//load level contructor
		loadLevel(path);
	}
	
	protected void generateLevel(){	//generate random level contructor
		
	}

	private void loadLevel(String path) {
		
	}
	
	public void update() {
		
	}
	
	private void time() {
		
	}
	
	public void render(int xScroll, int yScroll, Screen screen) {	
		screen.setOffset(xScroll, yScroll);				//sets offsets to xscroll and yscroll which are the location of the player
		int x0 = xScroll >> 4; 							// xScroll / 16 dividing by pixels per tile to get the tile. changes pixel percesion to tile percesion.
		int x1 = (xScroll + screen.width + 16) >> 4;	//add tile.sprite.SIZE so it renders one tile outside of the screen so you dont see it render
		int y0 = yScroll >> 4;							//top part of the screen
		int y1 = (yScroll + screen.height + 16) >> 4;		//bottom part of the screen
		
		for(int y = y0; y < y1; y++) {		//what this does is render tiles from right to left then move down a row and repeat
			for (int x = x0; x < x1; x++) {
				getTile(x,y).render(x, y, screen);				//get the tile at that location from the map data. This returns a tile object.
			}
		}
	}
	
	public Tile getTile(int x, int y) {	// reads the tile data from the map and returns it
		if(x < 0 || y<0 || x >= width || y >= height ) return Tile.voidTile;
		if(tiles[x +(y*width)] == 0) return Tile.grass;
		if(tiles[x +(y*width)] == 1) return Tile.grass;
		if(tiles[x +(y*width)] == 2) return Tile.grass;
		return Tile.voidTile;
	}
}
