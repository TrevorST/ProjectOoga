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
	
	public static Sprite player_forward_1 = new Sprite(32, 0, 4, SpriteSheet.tiles);
	public static Sprite player_forward_2 = new Sprite(32, 1, 4, SpriteSheet.tiles);
	public static Sprite player_forward_3 = new Sprite(32, 2, 4, SpriteSheet.tiles);
	public static Sprite player_forward_4 = new Sprite(32, 3, 4, SpriteSheet.tiles);
	public static Sprite player_forward_5 = new Sprite(32, 4, 4, SpriteSheet.tiles);
	public static Sprite player_forward_6 = new Sprite(32, 5, 4, SpriteSheet.tiles);
	public static Sprite player_forward_7 = new Sprite(32, 6, 4, SpriteSheet.tiles);
	public static Sprite player_forward_8 = new Sprite(32, 2, 6, SpriteSheet.tiles);
	
	public static Sprite player_backward_1 = new Sprite(32, 0, 2, SpriteSheet.tiles);
	public static Sprite player_backward_2 = new Sprite(32, 1, 2, SpriteSheet.tiles);
	public static Sprite player_backward_3 = new Sprite(32, 2, 2, SpriteSheet.tiles);
	public static Sprite player_backward_4 = new Sprite(32, 3, 2, SpriteSheet.tiles);
	public static Sprite player_backward_5 = new Sprite(32, 4, 2, SpriteSheet.tiles);
	public static Sprite player_backward_6 = new Sprite(32, 5, 2, SpriteSheet.tiles);
	public static Sprite player_backward_7 = new Sprite(32, 6, 2, SpriteSheet.tiles);
	public static Sprite player_backward_8 = new Sprite(32, 0, 6, SpriteSheet.tiles);
	
	public static Sprite player_right_1 = new Sprite(32, 0, 5, SpriteSheet.tiles);
	public static Sprite player_right_2 = new Sprite(32, 1, 5, SpriteSheet.tiles);
	public static Sprite player_right_3 = new Sprite(32, 2, 5, SpriteSheet.tiles);
	public static Sprite player_right_4 = new Sprite(32, 3, 5, SpriteSheet.tiles);
	public static Sprite player_right_5 = new Sprite(32, 4, 5, SpriteSheet.tiles);
	public static Sprite player_right_6 = new Sprite(32, 5, 5, SpriteSheet.tiles);
	public static Sprite player_right_7 = new Sprite(32, 6, 5, SpriteSheet.tiles);
	public static Sprite player_right_8 = new Sprite(32, 3, 6, SpriteSheet.tiles);
	
	public static Sprite player_left_1 = new Sprite(32, 0, 3, SpriteSheet.tiles);
	public static Sprite player_left_2 = new Sprite(32, 1, 3, SpriteSheet.tiles);
	public static Sprite player_left_3 = new Sprite(32, 2, 3, SpriteSheet.tiles);
	public static Sprite player_left_4 = new Sprite(32, 3, 3, SpriteSheet.tiles);
	public static Sprite player_left_5 = new Sprite(32, 4, 3, SpriteSheet.tiles);
	public static Sprite player_left_6 = new Sprite(32, 5, 3, SpriteSheet.tiles);
	public static Sprite player_left_7 = new Sprite(32, 6, 3, SpriteSheet.tiles);
	public static Sprite player_left_8 = new Sprite(32, 1, 6, SpriteSheet.tiles);

	
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
