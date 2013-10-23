// VanishingLetter.java -- Stage 7

// A VanishingLetter gets darker and darker until it is invisible,
// then gets bright again and repeats the process.

package theScore;

import java.awt.*;
import java.applet.*;


public class VanishingLetter extends Letter
{
	private int red, green, blue;  // amounts of the 3 primary colors in each letter

	public VanishingLetter(Graphics g)
	{
		super(g);

		resetColor();
	}

	public void drawLetter()
	{
		if (!alive)  // Do not draw the letter if it has been destroyed.
			return;

		font = new Font("Arial",Font.BOLD,letterSize);
		g.setFont(font);
		Grfx.setColor(g,red,green,blue);
		g.drawString(letterString, x, y);

		if (count == 0)  // Prevents letter from vanishing too quickly.
		{
			if (red > 0)   red--;     // makes the
			if (green > 0) green--;   // letter get
			if (blue > 0)  blue--;    // darker
		}

		if (invisible())
			resetColor();

		if (exploding)  // If the letter has been hit by a laser, show the explosion.
			explode();
	}

	private void resetColor()
	{
		switch(Grfx.random(1,6))
		{
			case 1 : red = 255; green =   0; blue =   0; break;  // red
			case 2 : red =   0; green = 255; blue =   0; break;  // green
			case 3 : red =   0; green =   0; blue = 255; break;  // blue
			case 4 : red = 255; green = 255; blue =   0; break;  // yellow
			case 5 : red =   0; green = 255; blue = 255; break;  // cyan
			case 6 : red = 255; green =   0; blue = 255; break;  // magenta
		}
	}

	private boolean invisible()
	{
		return red == 0 && green == 0 && blue == 0;
	}
}


