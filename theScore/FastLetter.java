// FastLetter.java -- Stage 7

// A FastLetter falls much faster than a normal Letter.

package theScore;

import java.awt.*;
import java.applet.*;


public class FastLetter extends Letter
{
	public FastLetter(Graphics g)
	{
		super(g);
	}

	public void fall()  // redefined fall method
	{
		y++;     // By always adding 1 and not bothering with count or speed the Letter will fall much faster.
		drawLetter();
	}
}


