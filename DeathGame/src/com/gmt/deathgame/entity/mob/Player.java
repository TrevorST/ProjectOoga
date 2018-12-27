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
	private int animLength = 0;
	private String currentAnimation = "";
	private String lastAnimation = "";
	private boolean isAnimChanged = false;
	private boolean walking = false;
	
	
	private Sprite[] animationForward = new Sprite[] 
			{Sprite.player_forward_1, Sprite.player_forward_2, Sprite.player_forward_3,
			 Sprite.player_forward_4, Sprite.player_forward_5, Sprite.player_forward_6,
			 Sprite.player_forward_7, Sprite.player_forward_8};
	private Sprite[] animationBackward = new Sprite[] 
			{Sprite.player_backward_1, Sprite.player_backward_2, Sprite.player_backward_3,
			 Sprite.player_backward_4, Sprite.player_backward_5, Sprite.player_backward_6,
			 Sprite.player_backward_7, Sprite.player_backward_8};
	private Sprite[] animationRight = new Sprite[] 
			{Sprite.player_right_1, Sprite.player_right_2, Sprite.player_right_3,
			 Sprite.player_right_4, Sprite.player_right_5, Sprite.player_right_6,
			 Sprite.player_right_7, Sprite.player_right_8};
	private Sprite[] animationLeft = new Sprite[] 
			{Sprite.player_left_1, Sprite.player_left_2, Sprite.player_left_3,
			 Sprite.player_left_4, Sprite.player_left_5, Sprite.player_left_6,
			 Sprite.player_left_7, Sprite.player_left_8};
	
	
	
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
			if(animFrame < animLength-1) {
				if(currentAnimation == lastAnimation) {
					isAnimChanged = false;
				}
				if(!isAnimChanged) {					//check and see if the animation being played has changed
					animFrame++;
				}
				else animFrame = 0;
			}
			else animFrame = 0;
		}
		lastAnimation = currentAnimation;
		
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
		if(dir == 0){
			sprite = Sprite.player_back;
			if(walking) {
				currentAnimation = "animationBackward";
				animLength = animationBackward.length;
				sprite = animationBackward[animFrame];
			}
		}
		if(dir == 1) { 
			sprite = Sprite.player_right;
			if(walking) {
				currentAnimation = "animationRight";
				animLength = animationRight.length;
				sprite = animationRight[animFrame];
			}
		}
		if(dir == 2) {
			sprite = Sprite.player_forward;
			if(walking) {
				currentAnimation = "animationForward";
				animLength = animationForward.length;
				sprite = animationForward[animFrame];
			}
		}
		if(dir == 3) {
			sprite = Sprite.player_left;
			if(walking) {
				currentAnimation = "animationLeft";
				animLength = animationLeft.length;
				sprite = animationLeft[animFrame];
			}
		}
		
		screen.renderPlayer(x-16, y-16, sprite);			//renders sprite and centers
		
	}

}
