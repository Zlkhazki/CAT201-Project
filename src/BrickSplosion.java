import java.awt.Graphics;
import java.util.ArrayList;

public class BrickSplosion 
{
	//fields
	private ArrayList<BrickPiece> pieces;
	private int x, y;
	private MapGenerator map;
	private boolean isActive;
	private long startTime;
	
	//constructor
	public BrickSplosion(int theX, int theY, MapGenerator map)
	{
		x = theX;
		y = theY;
		this.map = map;
		isActive = true;
		startTime = System.nanoTime();
		pieces = new ArrayList<BrickPiece>();
		init();
		
	}
	
	public void init()
	{
		int randNum = (int)(Math.random() * 20 + 5 ); 
		
		for(int i = 0; i < randNum; i++)
		{
			pieces.add(new BrickPiece(x, y, map));
		}
	}
	
	//update each piece and check time
	public void update()
	{
		for(BrickPiece bp : pieces) {
			bp.update();
		}
		
		if((System.nanoTime()-startTime) / 1000000 > 2000 && isActive) {
			isActive=false;
		}
	}
	
	public void draw(Graphics g) {
		for(BrickPiece bp : pieces) {
			bp.draw(g);
		}
	}
	
	public boolean getIsActive() {return isActive;}
}

