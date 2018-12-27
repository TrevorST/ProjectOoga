package com.gmt.deathgame.graphics;

import java.util.Random;

import com.gmt.deathgame.entity.mob.Player;
import com.gmt.deathgame.level.tile.Tile;


public class Screen {
	
	public int width, height;
	public int[] pixels;
	public final int MAP_SIZE = 64;
	public final int MAP_SIZE_MASK = MAP_SIZE - 1;
	public int xOffset, yOffset;
	public int[] tiles = new int[MAP_SIZE * MAP_SIZE];
	private Random random = new Random();
	
	public Screen(int width, int height) {
		this.width = width;	//makes the width of this.screen class = to the width param
		this.height = height;
		pixels = new int[width * height];	//54000 pixels
		
		
		for(int i = 0; i < MAP_SIZE*MAP_SIZE; i++) {
			tiles[i] = random.nextInt(0xffffff);
		}
	}
	
	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}
	
	public void renderTile(int xPos, int yPos, Tile tile) {
		xPos -= xOffset; 	// xp = xp - offset same thing and what this does is adjust the x position to the offset of the player
		yPos -= yOffset;
		for(int y = 0; y < tile.sprite.SIZE; y++) {		//y and x represents the pixel
			int yAbsolute = y + yPos;
			for(int x = 0; x < tile.sprite.SIZE; x++) {
				int xAbsolute = x + xPos;
				if(xAbsolute < -tile.sprite.SIZE || xAbsolute >= width || yAbsolute < 0 || yAbsolute >= height) break;	//if a pixel is set to render off screen then break DETERMINE WHAT RENDERS
				if(xAbsolute < 0) xAbsolute = 0;
				pixels[xAbsolute+(yAbsolute*width)] = tile.sprite.pixels[x+(y*tile.sprite.SIZE)];	//render corresponding pixel to absolute pos from the tile's sprite
			}
		}
	}
	
	public void renderPlayer(int xPos, int yPos, Sprite sprite) {
		xPos -= xOffset; 	// xp = xp - offset same thing and what this does is adjust the x position to the offset of the player
		yPos -= yOffset;
		for(int y = 0; y < 32; y++) {		//y and x represents the pixel
			int yAbsolute = y + yPos;
			for(int x = 0; x < 32; x++) {
				int xAbsolute = x + xPos;
				//int xReflected = 31 - x;		//Used to flip/ reflect the sprite when walking left or right
				if(xAbsolute < -32 || xAbsolute >= width || yAbsolute < 0 || yAbsolute >= height) break;	//if a pixel is set to render off screen then break DETERMINE WHAT RENDERS
				if(xAbsolute < 0) xAbsolute = 0;
				int col = sprite.pixels[x+(y*32)];				//set the color equal to a value on the sprite
				if (col != 0xFFFF05EE) {
					pixels[xAbsolute+(yAbsolute*width)] = col;	//render corresponding pixel to absolute pos from the tile's sprite
				}
			}
		}
	}
	
	public void setOffset(int xOffset, int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
}
