import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class PowerUp {
	
	//Fields
	private int x,y,dy,type,width,height;
	
	private boolean isOnScreen;
	private boolean wasUsed;
	
	private Color color;
	
	public final static int WIDEPADDLE=4;//UTK WIDEKAN SLIDER
	public final static int FASTBALL=5;//CEPATKAN BOLA
	//set the colour for WIDEPADDLE and FASTBALL
	public final static Color WIDECOLOR=Color.GREEN;
	public final static Color FASTCOLOR=Color.RED;
	
	//constructor
	public PowerUp (int xStart, int yStart, int theType, int theWidth, int theHeight) {
		
		x=xStart;
		y=yStart;
		type=theType;
		width=theWidth;
		height=theHeight;
		
		//checkjust in case somebody passes over type that's neither 4 or 5
		if (type<4) {
			type=4;
		}
		if (type>5) {
			type=5;
		}
		
		//asign the color
		if(type==WIDEPADDLE) {
			color=WIDECOLOR;
		}
		if(type==FASTBALL) {
			color=FASTCOLOR;
		}
		
		//generate random speed dia gerak ke bawah
		dy=(int)(Math.random()*6+1);
		
		wasUsed=false;
		
	}
	
	//guna graphics
    public void draw(Graphics g) {
    	g.setColor(color);
    	g.fillRect(x, y, width, height);
    }
    
    //ni bila dia memenuhi condition final static n berlaku collision dgn slider
    public void update() {
    	//block jatuh ke bawah
    	y+=dy;
    	
    	//570 tu ambik sama ngn lose condition
    	if(y>570) {
    		isOnScreen=false;
    	}
    }

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getDy() {
		return dy;
	}

	public void setDy(int dy) {
		this.dy = dy;
	}
	
	public int getType() {
		return type;
	}
	
	public boolean getIsOnScreen() {
		return isOnScreen;
	}
	
	public void setIsOnScreen(boolean onScreen) {
		isOnScreen=onScreen;
	}
	
	public boolean getWasUsed() {
		return wasUsed;
	}
	
	public void setWasUsed(boolean used) {
		wasUsed=used;
	}
	//create Rectangle
	public Rectangle getRect() {
		return new Rectangle(x,y,width,height);
	}
}
