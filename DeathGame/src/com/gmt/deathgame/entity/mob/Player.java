package com.gmt.deathgame.entity.mob;

import com.gmt.deathgame.graphics.Screen;
import com.gmt.deathgame.graphics.Sprite;
import com.gmt.deathgame.input.Keyboard;

public class Player extends Mob {
	
	private Keyboard input;
	private Sprite sprite;
	private float movementAcceleration;
	private float speed;
	private int anim = 0;						//timer or animation step
	private int animFrame = 0;					//current animation frame
	private boolean walking = false;
	
	private Sprite[] animationForward = new Sprite[] 
			{Sprite.player_forward_1, Sprite.player_forward_2, Sprite.player_forward_3,
			 Sprite.player_forward_4, Sprite.player_forward_5, Sprite.player_forward_6,
			 Sprite.player_forward_7};
	
	
	public Player(Keyboard input) {
		this.input = input;
		sprite = Sprite.player_forward;
		
	}
	
	public Player(int x, int y, Keyboard input) {		//when player is constructed we need a spawnpoint and keyboard class
		this.x = x;
		this.y = y;
		this.input = input;
	}
	
	public void update() {
		int xa = 0, ya = 0;
		
		if (anim < 7500) anim++;						//updates the animation steps and how fast the animation should play
		else anim = 0;
		if( anim % 6 == 0  ) {
			if(animFrame < animationForward.length-1)
				animFrame++;
			else animFrame = 0;
		}
		
		if (input.up) ya--;				//this movement affects the entity's x and y coordinates
		if (input.down) ya++;
		if (input.left) xa--;
		if (input.right) xa++;
		
		if(xa != 0 || ya != 0) {
			move(xa, ya);	//if the player types input try to move them
			walking = true;
		}
		else walking = false;
		
		
	}
	
	public void render(Screen screen) {
		if(dir == 0) sprite = Sprite.player_back;
		if(dir == 1) sprite = Sprite.player_right;
		if(dir == 2) {
			sprite = Sprite.player_forward;
			if(walking) {
					sprite = animationForward[animFrame];
			}
		}
		if(dir == 3) sprite = Sprite.player_left;
		
		screen.renderPlayer(x-16, y-16, sprite);			//renders sprite and centers
		
	}

}
