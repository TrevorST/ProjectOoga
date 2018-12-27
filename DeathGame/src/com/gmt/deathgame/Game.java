package com.gmt.deathgame;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import com.gmt.deathgame.entity.mob.Player;
import com.gmt.deathgame.graphics.Screen;
import com.gmt.deathgame.input.Keyboard;
import com.gmt.deathgame.level.Level;
import com.gmt.deathgame.level.RandomLevel;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;
	
	public static int width = 300;
	public static int height = width / 16 * 9;
	public static int scale = 3;				//resolution
	
	private Thread thread;
	private JFrame frame;
	private Keyboard key;
	private Level level;
	private Player player;
	private boolean running = false;
	
	private Screen screen;
	
	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();  //makes an array of the indiv pixels of the buffer ^
	
	public Game()	{
		Dimension size = new Dimension(width * scale, height * scale); //These are all canvas methods and variables where we extend it
		setPreferredSize(size);
		
		screen = new Screen(width, height);
		frame = new JFrame();
		key = new Keyboard();
		level = new RandomLevel(64, 64);		//generates new level
		player = new Player(key);				//creates player and send the player the keyboard class instance
		addKeyListener(key);					//if broken add frame. before
	}
	
	public synchronized void start()	{
		running = true;
		thread = new Thread(this, "Display");
		thread.start();
	}
	
	public synchronized void stop()	{
		running = false;
		try {
			thread.join();
		} catch(InterruptedException e)	{
			e.printStackTrace();
		}
	}
	
	public void run() {
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 60.0;	//nanosecond converter
		double delta = 0;
		int framesPerSec = 0;
		int updatesPerSec = 0;
		requestFocus();
		while(running)	{			
			long now = System.nanoTime();
			delta += (now-lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {	//while loop is true about 60 times every second
				update();	//tick
				updatesPerSec++;
				delta--;
			}
			render();
			framesPerSec++;
			
			if(System.currentTimeMillis() - timer > 1000) {	//while loop is true ever second
				timer += 1000;	//1000 milisecs = 1 sec we add this time to timer to keep its time up to date with the System time
				System.out.println(updatesPerSec + " updates, " + framesPerSec + " Fps.");
				frame.setTitle("DeathGame" + " | " + updatesPerSec + " updates, " + framesPerSec + " Fps.");
				updatesPerSec = 0;
				framesPerSec = 0;
			}
		}
	}
	public void update() {
		key.update();
		player.update();
		
	}
	
	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if(bs == null) {
			createBufferStrategy(3);	// calculates next frame up to two frames ahead before rendered
			return;
		}
		
		screen.clear();
		int cameraCenterX = player.x - screen.width / 2;		//centers camera
		int cameraCenterY = player.y - screen.height / 2;
		level.render(cameraCenterX, cameraCenterY, screen);		//handles the actual rendering of the level
		player.render(screen);
		
		for(int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.pixels[i];
		}
		
		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.setColor(Color.WHITE);
		g.drawString("X: " + player.x/16 + " Y: " + player.y/16, 25, 25);
		g.dispose();
		bs.show();
		
	}
	
	public static void main(String[] args)	{
		Game game = new Game();
		game.frame.setResizable(false);
		game.frame.setTitle("DeathGame");
		game.frame.add(game); //remember game is sub class of canvas
		game.frame.pack();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);
		
		game.start();
		
	}
}
