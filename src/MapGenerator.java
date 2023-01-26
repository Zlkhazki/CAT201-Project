import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class MapGenerator 
{
	public int map[][];
	public int brickWidth;
	public int brickHeight;
	public int maprow,mapcol;
	
	public int numGreen=0;
	public int numRed=0;
	
	public MapGenerator (int row,int col)
	{
		maprow=row;
		mapcol=col;
		map = new int[maprow][mapcol];		
		for(int i = 0; i<map.length; i++)
		{
			for(int j =0; j<map[0].length; j++)
			{
				//generate random number to assign diff colour
				int r=(int)(Math.random()*5+1);
				map[i][j] = r;
				if (map[i][j] == 4) {
					numGreen+=1;
				} else if (map[i][j] == 5) {
						numRed+=1;
					   }	
			}		
		}
		
		
		if(numGreen> 4) {
		A:for(int i = 0; i<map.length; i++)
		{
			for(int j =0; j<map[0].length; j++)
			{
				if (map[i][j]== 4) {
					//generate random number to assign diff colour
					int r=(int)(Math.random()*3+1);
					map[i][j] = r;
					numGreen-=1;
					
					if(numGreen==3)
					{ break A;}
				}
				
			}			
		}
		
		}
		
		if(numRed> 4) {
		A:for(int i = 0; i<map.length; i++)
		{
			for(int j =0; j<map[0].length; j++)
			{
				if (map[i][j]== 5) {
					//generate random number to assign diff colour
					int r=(int)(Math.random()*3+1);
					map[i][j] = r;
					numRed-=1;
					
					if(numRed==3)
					{ break A;}
				}
				
			}			
		}
		
		}
		
		brickWidth = 540/col;
		brickHeight = 150/row;
	}	
	
	public void draw(Graphics2D g)
	{
		for(int i = 0; i<map.length; i++)
		{
			for(int j =0; j<map[0].length; j++)
			{
				if(map[i][j] > 0)
				{
					//g.setColor(Color.white);
					
					if (map[i][j]==1) {
						g.setColor(new Color(204,102,0));
					}
					if (map[i][j]==2) {
						g.setColor(new Color(255,128,0));
					}
					if (map[i][j]==3) {
						g.setColor(new Color(255,153,51));
					}
					//WIDEPADDLE
					if (map[i][j]==PowerUp.WIDEPADDLE) {
						g.setColor(PowerUp.WIDECOLOR);
					}
					//FASTBALL
					if (map[i][j]==PowerUp.FASTBALL) {
						g.setColor(PowerUp.FASTCOLOR);
					}
					g.fillRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);
					
					// this is just to show separate brick, game can still run without it
					g.setStroke(new BasicStroke(3));
					g.setColor(Color.black);
					g.drawRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);				
				}
			}
		}
	}
	
	//func to detect win situation
	public boolean isThereWin() {
		boolean thereIsWin=false;
		int bricksRemaining=0;
		for (int row=0;row<map.length;row++) {
			for(int col=0;col<map[0].length;col++) {
				
				bricksRemaining+=map[row][col];
			}
		}
		
		if(bricksRemaining==0) {
			thereIsWin=true;
		}
		
		return thereIsWin;
	}
	
	//utk tukar warna,func ni tk guna sbb kita ganti dgn hitbrick func--//guna doh dlm powerup
	public void setBrickValue(int value, int row, int col)
	{
		map[row][col] = value;
	}
	
	//set the reduction of the colour
	public void hitBrick(int row,int col) {
		map[row][col]-=1;
		if (map[row][col]<0) {
			map[row][col]=0;
		}
	}
	
}
