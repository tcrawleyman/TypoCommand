// ShrinkingLetter.java -- Stage 7

// Shrinking letters will "shrink" as they fall.
// They are also the only letters that fall diagonally.


import java.awt.*;
import java.applet.*;


public class ShrinkingLetter extends Letter
{
	private boolean goingRight;    // Is the letter moving to the right?
	private int     visibleSize;   // Visible size of the shrinking letter

	public ShrinkingLetter(Graphics g)
	{
		super(g);

		visibleSize = (appletHeight - y) / 30 + 12;
		goingRight = Grfx.random(1,2) == 1;
	}

	public void drawLetter()
	{
		if (!alive)  // Do not draw the letter if it has been destroyed.
			return;

		visibleSize = (appletHeight - y) / 30 + 12;
		font = new Font("Arial",Font.BOLD,visibleSize);
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
			if (goingRight)
		    	x++;
			else
				x--;

			if (x < 0)
				goingRight = true;

			if (x + visibleSize > appletWidth)
				goingRight = false;

			column = x / letterSize;
			// Since the letter moves horizontally, its column number changes.

			count = 0;
		}
		drawLetter();
	}
}


