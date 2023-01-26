import java.util.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

import java.awt.*;

import javax.swing.*;
import javax.swing.Timer;

public class Gameplay extends JPanel implements KeyListener, ActionListener 
{
	private boolean play = false;
	private int score = 0;
	//private Graphics2D g;
	
	//private int totalBricks = 48;
	private int ipWidth=100;
	private int pWidth=ipWidth;
	//timer for shrink
	private long widthTimer;
	private boolean altWidth;
	//utk powerups
	private int condition;
	
	private Timer timer;
	//NEW
	private ArrayList<BrickSplosion> brickSplosions;
	private int delay=8;
	
	private int playerX = 310;
	
	private int ballposX = 120;
	private int ballposY = 350;
	private int ballXdir = -1;
	private int ballYdir = -2;
	private int ballLifeTime=2;
	
	private MapGenerator map;
	
	private ArrayList<PowerUp> powerUps;
	
	public Gameplay()
	{		
		map = new MapGenerator(4, 12);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
        timer=new Timer(delay,this);
		timer.start();
		
		//betul ke letak sini powerup
		powerUps=new ArrayList<PowerUp>();
		//NEW
		brickSplosions = new ArrayList<BrickSplosion>();
	
		
	}
	
	
	public void paint(Graphics g)
	{    		
		// background
		g.setColor(Color.black);
		g.fillRect(1, 1, 692, 592);
		
		// drawing map
		map.draw((Graphics2D) g);
		
		// borders
		g.setColor(Color.yellow);
		g.fillRect(0, 0, 3, 592);
		g.fillRect(0, 0, 692, 3);
		g.fillRect(691, 0, 3, 592);
		
		// the scores 		
		g.setColor(Color.white);
		g.setFont(new Font("serif",Font.BOLD, 23));
		g.drawString("Score: "+score, 560,30);
		
		// the life time 		
		g.setColor(Color.white);
		g.setFont(new Font("serif",Font.BOLD, 23));
		g.drawString("Life time: "+ballLifeTime, 50,30);
				
				
		// the paddle
		g.setColor(Color.white);
		g.fillRect(playerX, 550, pWidth, 8);
		
		// the ball
		g.setColor(Color.yellow);
		g.fillOval(ballposX, ballposY, 20, 20);
	
		//updatepowerup
		for(PowerUp pu :powerUps) {
			pu.update();
		}
		
		//draw powerups
		for(PowerUp pu:powerUps) {
			pu.draw(g);//betul ke g
		}
		
		//NEW
		for(int i =0; i< brickSplosions.size(); i++) {
			brickSplosions.get(i).update();
			if(!brickSplosions.get(i).getIsActive()) {
				brickSplosions.remove(i);
				}
			}
				
		for(BrickSplosion bs : brickSplosions) {
			bs.draw(g);
			}//
				
		//newhabis
		// when you won the game
		if(map.isThereWin()==true)
		{
			 playSound("file:./Resources/winsound.wav",0);	
			 play = false;
             ballXdir = 0;
     		 ballYdir = 0;
             g.setColor(Color.RED);
             g.setFont(new Font("serif",Font.BOLD, 50));
             g.drawString("You Won!!!", 230,300);
             
             g.setColor(Color.RED);
             g.setFont(new Font("serif",Font.BOLD, 20));           
             g.drawString("Press (Enter) to Restart", 260,350);  
		}
		
		// when you lose the game
		if(ballposY > 570 && ballLifeTime==0)
        {     
       
			 playSound("file:./Resources/losesound.wav",0);	
			 play = false;
             ballXdir = 0;
     		 ballYdir = 0;
             g.setColor(Color.RED);
             g.setFont(new Font("serif",Font.BOLD, 30));
             g.drawString("Game Over, Scores: "+score, 190,300);
             
             g.setColor(Color.RED);
             g.setFont(new Font("serif",Font.BOLD, 20));           
             g.drawString("Press (Enter) to Restart", 230,350);    
             
           
        }else if (ballposY > 570 && ballLifeTime>0) {
        	
//        	play = true;
//        	addKeyListener(this);
//    		setFocusable(true);
//    		setFocusTraversalKeysEnabled(false);
//            timer=new Timer(delay,this);
//    		timer.start();
//		    ballposX = 120;
//			ballposY = 350;
//			ballXdir = -1;
//		    ballYdir = -2;
		    
		    play = true;
			ballposX = 120;
			ballposY = 350;
			ballXdir = -1;
			ballYdir = -2;
			
			repaint();
		    
			ballLifeTime-=1;}
		
		//updatefor paddle timer
		if((System.nanoTime()-widthTimer)/1000 > 5000000 && altWidth==true && condition==4) {
			pWidth=ipWidth;
			altWidth=false;
		}
		
		//update speed
		if((System.nanoTime()-widthTimer)/1000 > 3000000 && altWidth==true && condition==5) {
			ballXdir = -1;
			ballYdir = -2;
			altWidth=false;
		}
		
		//draw text shrinking
//		if(altWidth=true) {
//		 g.setColor(Color.RED);
//		 g.setFont(new Font ("Courier New", Font.BOLD,18));
//		 g.drawString("Shrinking in "+(3-(System.nanoTime()-widthTimer)/1000000000), playerX, 550+18);
//		}//X YAH LA SHOW TEXT TU
		g.dispose();
	}	

	public void keyPressed(KeyEvent e) 
	{
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
		{        
			if(playerX >= 600)
			{
				playerX = 600;
			}
			else
			{
				moveRight();
			}
        }
		
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
		{          
			if(playerX < 10)
			{
				playerX = 10;
			}
			else
			{
				moveLeft();
			}
        }		
		if (e.getKeyCode() == KeyEvent.VK_ENTER)
		{          
			if(!play)
			{
				play = true;
				ballposX = 120;
				ballposY = 350;
				ballXdir = -1;
				ballYdir = -2;
				playerX = 310;
				score = 0;
				ballLifeTime=2;
		//		totalBricks = 21;
				map = new MapGenerator(3, 7);
				
				repaint();
			}
        }		
	}

	 //func for new slider when powerup occur
//	public void draw() {
//	Graphics g=null;
//	// the paddle
//	g.setColor(Color.BLUE);
//	g.fillRect(playerX, 550, 200, 8);
//	}
	
	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}
	
	public void moveRight()
	{
		play = true;
		playerX+=20;	
	}
	
	public void moveLeft()
	{
		play = true;
		playerX-=20;	 	
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		timer.start();
		if(play)
		{			
			//when collision between powerup and slider
			//loop over the powerup
			
			for(int i=0;i<powerUps.size();i++) {
				Rectangle puRect=powerUps.get(i).getRect();
				
				if(new Rectangle(playerX, 550, pWidth, 8).intersects(puRect)) {
					
					if (powerUps.get(i).getType()==PowerUp.WIDEPADDLE && !powerUps.get(i).getWasUsed()) {
						//double the size of slider
			//			draw();
						//g.fillRect(playerX, 550, 200, 8);
						altWidth=true;
						pWidth*=2;
						
						setWidthTimer(4);
						powerUps.get(i).setWasUsed(true);
					}
					
					else if (powerUps.get(i).getType()==PowerUp.FASTBALL && !powerUps.get(i).getWasUsed()) {
						Random random = new Random();
						int n = random.nextInt(10-6+4) - 5;
						 ballXdir = n;
						 ballYdir = n;
						 setSpeedTimer(5);
						 powerUps.get(i).setWasUsed(true);
					}
					
					
				}
			}
			
			if(new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(playerX, 550, pWidth, 8)))
			{
				playSound("file:./Resources/hitslider.wav",0);	
				ballYdir = -ballYdir;
				ballXdir = -2;
			}
			else if(new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(playerX + 70, 550, pWidth, 8)))
			{
				playSound("file:./Resources/hitslider.wav",0);	
				ballYdir = -ballYdir;
				ballXdir = ballXdir + 1;
			}
			else if(new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(playerX + 30, 550, pWidth, 8)))
			{
				playSound("file:./Resources/hitslider.wav",0);	
				ballYdir = -ballYdir;
			}
			
			// check map collision with the ball		
			A: for(int i = 0; i<map.map.length; i++)
			{
				for(int j =0; j<map.map[0].length; j++)
				{				
					if(map.map[i][j] > 0)
					{
						//scores++;
						int brickX = j * map.brickWidth + 80;
						int brickY = i * map.brickHeight + 50;
						int brickWidth = map.brickWidth;
						int brickHeight = map.brickHeight;
						
						Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);					
						Rectangle ballRect = new Rectangle(ballposX, ballposY, 20, 20);
						Rectangle brickRect = rect;
						
						//when ball intersect with bricks
						if(ballRect.intersects(brickRect))
						{	
							playSound("file:./Resources/ballhitbricks.wav",0);				
							
							//int mapRow=map.maprow;
							//int mapCol=map.mapcol;
							
							//NEW
							if(map.map[i][j] == 1) {
								brickSplosions.add(new BrickSplosion(brickX, brickY, map));
							}
							
							//powerups happend
							if(map.map[i][j]>3) {
								powerUps.add(new PowerUp(brickX,brickY,map.map[i][j],brickWidth,brickHeight));
								
								map.setBrickValue(3, i, j);
								//perlu letak utk kalau dia 5 dia takkan jd 4 dh
							}else {
								map.hitBrick(i, j);
							}
							
						//	map.hitBrick(i, j);
							score+=5;	
						//	totalBricks--;
							
							// when ball hit right or left of brick
							if(ballposX + 19 <= brickRect.x || ballposX + 1 >= brickRect.x + brickRect.width)	
							{
								ballXdir = -ballXdir;
							}
							// when ball hits top or bottom of brick
							else
							{
								ballYdir = -ballYdir;				
							}
							
							break A;
						}
					}
				}
			}
			
			ballposX += ballXdir;
			ballposY += ballYdir;
			
			if(ballposX < 0)
			{
				ballXdir = -ballXdir;
			}
			if(ballposY < 0)
			{
				ballYdir = -ballYdir;
			}
			if(ballposX > 670)
			{
				ballXdir = -ballXdir;
			}		
			
			repaint();		
		}
	}
	
	//to play sound
	public void playSound(String soundFile,int times) {
		
		try {
			URL soundLocation =new URL(soundFile);
			AudioInputStream audio = AudioSystem.getAudioInputStream(soundLocation);
			Clip clip = AudioSystem.getClip();
			clip.open(audio);
			clip.loop(times);
			clip.start();
			
		}catch (UnsupportedAudioFileException uae) {
			System.out.println(uae);
		}catch (IOException ioe) {
			System.out.println(ioe);
		}catch (LineUnavailableException lua) {
		    System.out.println(lua);	
		}
		
	}
	
	public void setWidthTimer(int newWidth) {
		condition=newWidth;
		widthTimer=System.nanoTime();
	}
	//speed timer
	public void setSpeedTimer(int speed) {
		condition=speed;
		widthTimer=System.nanoTime();
	}
}
