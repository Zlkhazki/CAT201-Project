import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class BrickPiece 
{
	//fields
	private int x, y;
	private double dx, dy, gravity;
	private Color color;
	private MapGenerator map;
	private int size;
	
	//constructor
	public BrickPiece(int brickx, int bricky, MapGenerator theGamemap)//send location
	{
		map = theGamemap;
		x = brickx + map.brickWidth/2;
		y = bricky + map.brickHeight/2;
		dx = (Math.random() * 30 - 15);
		dy = (Math.random() * 30 - 15);
		size = (int) (Math.random() * 15 + 2);
		color = new Color(204,102,0); 		//weakest brick
		gravity = .8;
		
	}
	
	public void update()
	{
		x += dx;
		y += dy;
		dy += gravity;
		
	}
	
	public void draw(Graphics g)
	{
		g.setColor(color);
		g.fillRect((int)x, (int) y, size, size);
	}
}
