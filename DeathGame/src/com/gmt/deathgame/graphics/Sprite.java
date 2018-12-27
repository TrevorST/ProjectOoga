package com.gmt.deathgame.graphics;

public class Sprite {

	public final int SIZE;
	private int x, y;
	public int[] pixels;
	private SpriteSheet sheet;
	
	public static Sprite grass = new Sprite(16, 0, 0, SpriteSheet.tiles);
	public static Sprite voidSprite = new Sprite(16, 0x1B87E0);

	public static Sprite player_forward = new Sprite(32 ,1 ,7, SpriteSheet.tiles);
	public static Sprite player_back = new Sprite(32 ,2 ,7, SpriteSheet.tiles);
	public static Sprite player_left = new Sprite(32 ,4 ,7, SpriteSheet.tiles);
	public static Sprite player_right = new Sprite(32 ,3 ,7, SpriteSheet.tiles);
	
	public static Sprite player_forward_1 = new Sprite(32, 1, 6, SpriteSheet.tiles);
	public static Sprite player_forward_2 = new Sprite(32, 1, 5, SpriteSheet.tiles);
	public static Sprite player_forward_3 = new Sprite(32, 1, 4, SpriteSheet.tiles);
	public static Sprite player_forward_4 = new Sprite(32, 1, 3, SpriteSheet.tiles);
	public static Sprite player_forward_5 = new Sprite(32, 1, 2, SpriteSheet.tiles);
	public static Sprite player_forward_6 = new Sprite(32, 1, 1, SpriteSheet.tiles);
	public static Sprite player_forward_7 = new Sprite(32, 0, 2, SpriteSheet.tiles);
	public static Sprite player_forward_8 = new Sprite(32, 0, 1, SpriteSheet.tiles);
	
	
	public Sprite(int size, int x, int y, SpriteSheet sheet) {
		SIZE = size;
		pixels = new int[SIZE*SIZE];
		this.x = x*size;	//feed the location of the sprite in the sprite sheet and get the coords ex. sprite in row 2 collum 1 becomes 32, 16
		this.y = y*size;
		this.sheet = sheet;
		load();
	}
	
	public Sprite(int size, int color) {
		SIZE = size;
		pixels = new int[SIZE*SIZE];
		setColor(color);
		
	}
	
	private void setColor(int color) {
		for(int i = 0; i < SIZE*SIZE; i++) {
			pixels[i] = color;	//sets all the pixels in a sprite to one solid color
		}
	}
	
	private void load() {
		for(int y = 0; y < SIZE; y++) {
			for(int x = 0; x < SIZE; x++) {
				pixels[x+(y*SIZE)] = sheet.pixels[(x + this.x) + (y + this.y)*sheet.SIZE];
			}
		}
	}
}
