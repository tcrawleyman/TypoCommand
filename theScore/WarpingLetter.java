
package theScore;

import java.awt.Font;
import java.awt.Graphics;


public class WarpingLetter extends Letter
{

	private int determine;
	private int amt;
	
	public WarpingLetter(Graphics g) {
		super(g);
		
		do
		{
			column = Grfx.random(0, numColumns-1);
		}
		while (column == 16 || column == 17);
		
		x = column * letterSize + centerOffset;
		y = Grfx.random(-30, -1) * 10;

		int letNum = Grfx.random(1,35);    // 35 possible characters
		if (letNum < 10)
			letter = (char) (((int) '0') + letNum);  // 9 out of 35 characters are digits
		else
		    letter = (char) (Grfx.random(65,90));   // 26 out of 35 characters are letters
		letterString = String.valueOf(letter);
		
		colorNum = Grfx.random(1,9);  // 9 different possible colors for the letter

		count = 0;
		speed = 3;
		
		centerAmount = 2*letterSize/5;
		
		maxExplodingCount  = 30;
		explodingCountDown = maxExplodingCount;
		halfExplodingCount = maxExplodingCount / 2;
		
		determine = Grfx.random(0,numColumns-1);
		amt = Grfx.random(400,2000);
		
	}

	public void drawLetter()
	{
		amt--;
		if(!alive)
			return;
		
		if(amt <= 0)
		x = determine * letterSize + centerOffset;
		else
		x = column *letterSize + centerOffset;
		
		
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


}
