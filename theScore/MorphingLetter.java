
package theScore;

import java.awt.Font;
import java.awt.Graphics;


public class MorphingLetter extends Letter
{

	private int determine;
	private int amt;

	private int letNum;
	
	public MorphingLetter(Graphics g)
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

		letNum = Grfx.random(1,35);    // 35 possible characters
		if (letNum < 10)
			letter = (char) (((int) '0') + letNum);  // 9 out of 35 characters are digits
		else
		    letter = (char) (Grfx.random(65,90));   // 26 out of 35 characters are letters
		letterString = String.valueOf(letter);


		colorNum = Grfx.random(1,9);  // 9 different possible colors for the letter

		count = 0;
		speed = 3;

		centerAmount = 2*letterSize/5;  // 40% of the size of the letter

		maxExplodingCount  = 30;
		explodingCountDown = maxExplodingCount;
		halfExplodingCount = maxExplodingCount / 2;
		
		determine = Grfx.random(1,35);
		amt = Grfx.random(400,2000);
		
	}
	
	public void drawLetter()
	{
		amt--;
		if(!alive)
			return;
		
		font = new Font("Arial",Font.BOLD,letterSize);
		g.setFont(font);
		Grfx.setColor(g,colorNum);

		
		if(amt <= 0 && amt >=-1000)
		{
			if (determine < 10)
				letter = (char) (((int) '0') + determine);  // 9 out of 35 characters are digits
			else
			    letter = (char) (Grfx.random(65,90));
		letterString = String.valueOf(letter);
		g.drawString(letterString, x, y);
		}
		else
		{
			if (letNum < 10)
				letter = (char) (((int) '0') + letNum);  // 9 out of 35 characters are digits
			else
			    letter = (char) (Grfx.random(65,90));   // 26 out of 35 characters are letters
			letterString = String.valueOf(letter);
			g.drawString(letterString, x, y);
		}
		
		


		if (exploding)  // If the letter has been hit by a laser, show the explosion.
			explode();
		
	}
	
	

}
