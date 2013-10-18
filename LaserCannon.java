// LaserCannon.java

// This minimum LaserCannon class was introduced with Stage 5A.
// This file will remain unchanged until students do the Typocommand lab assignment.
// In that assignment students can improve the appearance of the LaserCannon
// which right now is nothing more than a triangle.


import java.awt.*;
import java.applet.*;


public class LaserCannon extends GameThing
{
	public LaserCannon(Graphics g)  { super(g); }

	public void drawLaserCannon()
	{
		g.setColor(Grfx.lightCyan);
		int start  = 16 * letterSize + centerOffset;
		int finish = 18 * letterSize + centerOffset - 1;
		Grfx.fillTriangle(g,laserCanTipX,laserCanTipY,start,appletHeight,finish,appletHeight);
	}

	// Since I have decided that the LaserCannon will be indestructible
	// both methods are intentionally redefined with empty method bodies.
	public void triggerExplosion()
	{
	}

	public void explode()
	{
	}

}


