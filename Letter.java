// Letter.java -- Stage 7

// The Letter attributes now have "protected" access since they will be
// inherited by various subclasses.


import java.awt.*;
import java.util.ArrayList;


public class Letter extends GameThing
{
	protected int x,y;              // coordinate location of the bottom-left corner of the letter
	protected Font font;            // font of the letter
	protected char letter;       	// the letter (or digit) which
	protected String letterString;  // letter converted to a String for methods which require a String parameter
	protected int colorNum;         // determines the color of the letter
	protected int count;            // counts iteration to determine letter falling speed
	protected int speed;	  		  // controls how fast the letter is falling
	protected int centerAmount;     // # of pixels needed to find the center of the letter


	public Letter(Graphics g)
	{
		super(g);

		do
		{
			column = Grfx.random(0, numColumns-1); // pick a random column
		}
		while (column == 16 || column == 17);
		// The laser is in the middle (column 16 & 17).  Don't pick those numbers.

		x = column * letterSize + centerOffset;  // converts column# to x coordinate
		y = Grfx.random(-30,-1) * 10;   // negative y value allows letter to start above the screen.

		int letNum = Grfx.random(1,35);    // 35 possible characters
		if (letNum < 10)
			letter = (char) (((int) '0') + letNum);  // 9 out of 35 characters are digits
		else
		    letter = (char) (Grfx.random(65,90));   // 26 out of 35 characters are letters
		letterString = String.valueOf(letter);

		colorNum = Grfx.random(1,9);  // 9 different possible colors for the letter
		
		int difficulty = TypoCommand.difficulty();

		if(difficulty == 1)
		{
		count = 0;
		speed = 6;
		} else if(difficulty == 2)
		{
			count = 0;
			speed = 3;
		} else if(difficulty == 3)
		{
			count = 0;
			speed = 1;
		}
		centerAmount = 2*letterSize/5;  // 40% of the size of the letter

		maxExplodingCount  = 30;
		explodingCountDown = maxExplodingCount;
		halfExplodingCount = maxExplodingCount / 2;
	}

	ArrayList<City> cityList = new ArrayList<City>();
	
	public void triggerExplosion()
	{
		exploding = true;
	}

	public void explode()
	{
		if (!alive)   // A letter that is "exploding" is still alive.
			return;   // A dead letter cannot explode.

		xc = x + centerAmount;
		yc = y - centerAmount;

		
		Grfx.explosionStar(g,xc,yc,2);

		if (explodingCountDown <= 0)
			alive = false;  // When the explosion is finished, the letter dies.
		else if (count == 0)
			explodingCountDown--;
			// By only counting down once each cycle the explosion does not happen to quickly.
	}

	public void drawLetter()
	{
		if (!alive)  // Do not draw the letter if it has been destroyed.
			return;

		font = new Font("Arial",Font.BOLD,letterSize);
		g.setFont(font);
		Grfx.setColor(g,colorNum);
		g.drawString(letterString, x, y);

		if (exploding)  // If the letter has been hit by a laser, show the explosion.
			explode();
	}

	public void fall()
	{
		count++;
		if (count >= speed)
		{
			y++;
			count = 0;
		}
		drawLetter();
	}

	public boolean hitBottom() // Has the letter reached the bottom of the screen?
	{
		return y >= appletHeight;
	}

	public char getLetter()   { return letter; }

	public void drawLaser()
	{
		Grfx.setColor(g,Grfx.random(1,9));
		xc = x + centerAmount;
		yc = y - centerAmount;
		g.drawLine(laserCanTipX,laserCanTipY,xc,yc);
	}

	public boolean onScreen()
	{
		return y > 10;  // Is the letter at least partially visible on the screen?
	}

	public boolean isBeingTrackedAndShot()
	{
		return exploding && explodingCountDown > halfExplodingCount;
	}
	
	
	public int getLetterY()
	{
		return y;
	}

}


