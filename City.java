// City.java

// This file is identical to Stage 4E.
// It will remain unchanged until students do the Typocommand lab assignment.



import java.awt.*;
import java.applet.*;
import java.util.Random;

public class City extends GameThing
{
	private int index;      // index # of the city
	private int x1,x2;      // Coordinate of the upper-left hand corner of the rectangular city block.
	private int	y1,y2;      // Coordinate of the bottom-right hand corner of the rectangular city block.
	private int column;     // The screen is divided into 34 columns.  What column does the city start in?
	private int numLights;  // The number of lights a city has
	private int seed;       // A random seed used to randomly draw the buildings on each city
	int numCity = 8;

	public City(Graphics g, int index)
	{
		super(g);
		seed = Grfx.random(1,2000000000);
		this.index = index;
		numLights = 9;

		if (index < 4)  // Calculate horizontal ordinates for the cities to the left of the laser.
		{
			x1 = 4 * index * letterSize + centerOffset;
			x2 = 4 * index * letterSize + 3 * letterSize + centerOffset - 1;
			column = 4 * index;
		}
		else  // Calculate horizontal ordinates for the cities to the right of the laser.
		{
			x1 = 4 * index * letterSize + 3 * letterSize + centerOffset;
			x2 = 4 * index * letterSize + 6 * letterSize + centerOffset - 1;
			column = 4 * index + 3;
		}
		y1 = appletHeight - letterSize - 1;  // Calculate vertical ordinates
		y2 = appletHeight - 1;
		xc = (x1 + x2)/2;  // Calculate center coordinates
		yc = (y1 + y2)/2;

	}

	public void drawCity()
	{
		if (!alive) 
		{
			

			return;
		}
//		if(numCity == 0)
//		{
//			System.out.println("Game Over");
//		}
		g.setColor(Grfx.orange);
		Grfx.fillRectangle(g,x1,y1,x2,y2);
		g.setColor(Grfx.darkGreen);
		Grfx.drawThickRectangle(g,x1,y1,x2,y2,2);

		int s = 9;
		int buildingBase = appletHeight - letterSize - 1;
		Random rand = new Random(seed);
		for(int j = 0; j < numLights; j++)
		{
			int lightX = x1 + (j+1) * s;
			int lightY = appletHeight - letterSize/2 - 1;
			g.setColor(Grfx.white);
			g.fillRect(lightX-3,lightY-1,4,4);  // Draws each light

			Grfx.setColor(g,rand.nextInt(9) + 1);
			int height = rand.nextInt(20) + 10;
			int buildingHeight = buildingBase - height;
			g.fillRect(lightX-4,buildingHeight,6,height);  // Draws each building in the city.

		}
		
		for(int j = 0; j<8; j++)
		{
			
		}
		
		if(exploding)
		{
			numCity--;
			explode();
			alive = false;
		}
	}

	public boolean hit(int letterColumn) // Was a city hit by a letter in  particular column?
	{

		return 	this.column   == letterColumn ||   // left   column
		      	this.column+1 == letterColumn ||   // middle column
				this.column+2 == letterColumn;     // right  column
	}



	public void triggerExplosion()
	{
		

		exploding = true;
	
	}


	public void explode()
	{
		numCity++;
		Grfx.explosionStar(g,xc,yc,2);

	}
}


