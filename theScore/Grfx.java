// THE Grfx CLASS
// NOTE: HTML <br> (breaks) are used in the documentation so it will look better in Javadoc.

package theScore;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.applet.Applet;

/**
 *
 * Grfx CLASS UPDATE: 9/18/2012 <BR><BR>
 *
 * This class contains several graphics methods and several Color objects originally created by
 * John L. M. Schram for the Deluxe Expo class.
 *
 */

public class Grfx
{
	// built in colors copied to the Grfx class
	static final Color red        = Color.red;
	static final Color green      = Color.green;
	static final Color blue       = Color.blue;
	static final Color orange     = Color.orange;
	static final Color cyan       = Color.cyan;
	static final Color magenta    = Color.magenta;
	static final Color yellow     = Color.yellow;
	static final Color gray       = Color.gray;
	static final Color lightGray  =	Color.lightGray;
	static final Color darkGray   = Color.darkGray;
	static final Color pink       = Color.pink;
	static final Color black      = Color.black;
	static final Color white      = Color.white;

	// other colors
	static final Color lightRed       = new Color(255,128,128);
	static final Color darkRed        = new Color(192,0,0);
	static final Color lightOrange    = new Color(255,224,0);
	static final Color darkOrange     = new Color(255,128,0);
	static final Color lightYellow    = new Color(255,255,128);
	static final Color darkYellow     = new Color(192,192,0);
	static final Color lightGreen     = new Color(128,255,128);
	static final Color darkGreen      = new Color(0,128,0);
	static final Color lightBlue      = new Color(128,128,255);
	static final Color darkBlue       = new Color(0,0,128);
	static final Color lightMagenta   = new Color(255,64,255);
	static final Color darkMagenta    = new Color(192,0,192);
	static final Color lightCyan      = new Color(128,255,255);
	static final Color darkCyan       = new Color(0,192,192);
	static final Color lightPink      = new Color(255,194,194);
	static final Color darkPink       = new Color(240,150,150);
	static final Color tan            = new Color(210,180,140);
	static final Color lightTan       = new Color(231,198,154);
	static final Color darkTan        = new Color(189,145,87);
	static final Color brown          = new Color(150,100,15);
	static final Color violet         = new Color(240,128,240);
	static final Color purple         = new Color(128,0,128);
	static final Color turquoise      = new Color(64,224,208);
	static final Color gold           = new Color(255,215,0);
	static final Color silver         = new Color(192,192,192);
	static final Color bronze         = new Color(164,102,40);
	
	Random ran1 = new Random();
	Random ran2 = new Random();
	Random ran3 = new Random();
	



	//////////////////////////////////////////////////////////////////////////////////////////////////////////////





	/**
     * Returns a "true" random integer in the [min..max] range, inclusive. <BR>
     * Precondition: max > min <br>
     * Example:<br>
     * System.out.println(Grfx.random(100,200)); <br>
     * This will display a random integer between 100 and 200.
     */
	public static int random(int min, int max)
	{
		int range = max - min + 1;
		int number = (int) (range * Math.random() + min);
		return number;
	}


	/**
	 * Delays the output for a specified number of milliseconds (1/1000ths of a second). <br>
	 * Examples:<br>
	 * Grfx.delay(1000); // pause for 1 second <br>
	 * Grfx.delay(2000); // pause for 2 seconds <br>
	 * Grfx.delay(500);  // pause for 1/2 of a second <br>
	 * Grfx.delay(100);  // pause for 1/10 of a second <br>
	 * Grfx.delay(10);   // pause for 1/100 of a second <br>
	 * Grfx.delay(1);    // pause for 1/1000 of a second <br>
	 */
	public static void delay(int n)
	{
		long startDelay = System.currentTimeMillis();
		long endDelay = 0;
		while (endDelay - startDelay < n)
			endDelay = System.currentTimeMillis();
	}


	/**
	 * Allows the user to change to one of 10 predefined primary colors using a single int parameter.<br>
	 * This is especially useful in programs where you want to change from one color to a contrasting color quickly. <br>
	 * 0 = black <br>
	 * 1 = red <br>
	 * 2 = green <br>
	 * 3 = blue <br>
	 * 4 = orange <br>
	 * 5 = cyan <br>
	 * 6 = magenta <br>
	 * 7 = yellow <br>
	 * 8 = gray <br>
	 * 9 = pink <br>
	 * Any negative number will give you white. <br><br>
	 * Example: <br>
	 * Grfx.setColor(g,1); <br>
     * Changes the current drawing color to red.
	 */
	public static void setColor(Graphics g, int colorNum)
	{
		colorNum %= 10;
		switch(colorNum)
		{
			case  0 : g.setColor(black); 	break;
			case  1 : g.setColor(red); 		break;
			case  2 : g.setColor(green); 	break;
			case  3 : g.setColor(blue); 	break;
			case  4 : g.setColor(orange); 	break;
			case  5 : g.setColor(cyan); 	break;
			case  6 : g.setColor(magenta); 	break;
			case  7 : g.setColor(yellow); 	break;
			case  8 : g.setColor(gray); 	break;
			case  9 : g.setColor(pink); 	break;
			default : g.setColor(white);
		};
	}


	/**
	 * Allows the user to change the color, using 3 int parameters representing the amount of red, green and blue.<br>
	 * This allows for more than 16 million different color combinations. <br>
	 * Precondition: All 3 color values need to be between 0 and 255 inclusive. <br>
	 * Example: <br>
	 * Grfx.setColor(g,190,10,47); <br>
     * Changes the current drawing color to the shade of red used in the official Texas Flag
	 */
	public static void setColor(Graphics g, int red, int green, int blue)
	{
		Color newColor = new Color(red, green, blue);
		g.setColor(newColor);
	}


	/**
	 * Sets the entire background to a single color using a Color object parameter. <br>
	 * Precondition: The applet window cannot be greater than 4800 X 3600 in size. <br>
	 * Postcondition: The current drawing color will not be affected by the background color. <br>
	 * Example: <br>
	 * Grfx.setBackground(g,Grfx.red); <br>
	 * This will make the entire background red.
	 */
	public static void setBackground(Graphics g, Color bgColor)
	{

	    g.setColor(bgColor);
	    fillRectangle(g,0,0,4800,3600);
	}
	

	public static void drawBackgroundStars(Graphics g)
	{
		Random rand = new Random(12345);
		int xCoord = 20;
		int yCoord = 20;
	    for(int c = 0; c<=35; c++)
	    {
	    	for(int h = 0; h<=50; h++)
	    	{

	    	int c1 = rand.nextInt(255);
	    	int c2 = rand.nextInt(255);
	   		int c3 = rand.nextInt(255);
	    	Color starColor = new Color(c1,c2,c3);
	    	drawStar(g,5,2,xCoord,yCoord,starColor);
	    	xCoord +=20;
	    	}
	    	xCoord = 20;
	    	yCoord +=20;
	    }
	}
	
	public static void explosionStar(Graphics g,int centerX, int centerY, int radius)
	{
		Random rand = new Random();
		int xCoord = centerX;
		int yCoord = centerY;
		int divisibleBy = rand.nextInt(3);
		if(divisibleBy%3 == 0)
		{
		for(int c = 0; c<=25; c++)
		{
	    	int c1 = rand.nextInt(255);
	    	int c2 = rand.nextInt(255);
	   		int c3 = rand.nextInt(255);
	    	Color starColor = new Color(c1,c2,c3);
	    	drawStar(g,5,radius,xCoord,yCoord, starColor);
	    	radius +=2;
		}

		} else if(divisibleBy%2 == 0)
		{
			for(int c = 0; c<=25; c++)
			{
		    	int c1 = rand.nextInt(255);
		    	int c2 = rand.nextInt(255);
		   		int c3 = rand.nextInt(255);
		    	Color starColor = new Color(c1,c2,c3);
		    	drawStar(g,8,radius,xCoord,yCoord, starColor);
		    	radius +=2;
			}

		}
	}

	/**
	 * Draws a single pixel at the specified x,y location. <BR>
	 * Example: <BR>
	 * Grfx.drawPixel(g,100,200); <BR>
	 * Draws a very small single dot (pixel) on the computer screen 100 pixels over and 200 pixels down
	 */
	public static void drawPixel(Graphics g, int x, int y)
	{
		g.drawLine(x,y,x,y);
	}


	/**
	 * Draws a 3 pixel X 3 pixel "point" whose center is at the specified x,y location. <BR>
	 * Example: <BR>
	 * Grfx.drawPoint(g,100,200);
	 * Draws a larger, more visible (than drawPixel) dot on the computer screen 100 pixels over and 200 pixels down.
	 */
	public static void drawPoint(Graphics g, int x, int y)
	{
		g.fillRect(x-1,y-1,3,3);
	}



	/**
	 * Draws an open circle.<br>
	 * The user specifies the x,y coordinate of the center of the circle as well as the radius value. <br>
	 * Example: <br>
	 * Grfx.drawCircle(g,300,200,100); <br>
	 * Draws an open circle with a radius of 100 pixels whose center is located at the coordinate (300,200).
	 */
	public static void drawCircle(Graphics g, int centerX, int centerY, int radius)
	{
		int diameter = 2 * radius;
		g.drawOval(centerX-radius, centerY-radius, diameter, diameter);
	}



	/**
	 * Draws and arc which looks like a curve.<br>
	 * An ARC is a "piece" of an OVAL.<br>
	 * The first 5 parameters (g and 4 ints) are the same as drawOval.<br>
	 * There are 2 additional parameters for the starting degree value and finishing degree of the arc. <br>
	 * 0 degrees is at the 12:00 position and the degrees progress in a CLOCKWISE fashion. <br>
	 * (90 degrees is at 3:00, 180 degrees is at 6:00, 270 degrees is at 9:00, 360 degrees is back at 12:00).
	 */
	public static void drawArc(Graphics g, int centerX, int centerY, int hRadius, int vRadius, int start, int finish)
	{
		int hDiameter = 2 * hRadius;
		int vDiameter = 2 * vRadius;

		if (finish < start)
			finish += 360;
		int newStart  = 90 - start;   // shifts starting position from 3:00 to 12:00
		int newFinish = start-finish; // as opposed to finish-start.  Subtracting backwards changes from counter-clockwise to clockwise.

		g.drawArc(centerX-hRadius, centerY-vRadius, hDiameter, vDiameter, newStart, newFinish);
	}


	/**
	 * Draws an open irregular triangle using the three sets of provided coordinates. <br>
	 * Example: <br>
	 * Grfx.drawTriangle(g,100,300,200,100,300,300);
	 */
	 public static void drawTriangle(Graphics g,int x1,int y1,int x2,int y2,int x3,int y3) ////// Added 06-25-08
	 {
	 	Polygon myPoly = new Polygon();
	 	myPoly.addPoint(x1,y1);
	 	myPoly.addPoint(x2,y2);
	 	myPoly.addPoint(x3,y3);
	 	g.drawPolygon(myPoly);
	}


	 /**
	  *	Draws an open irregular quadrilateral using the four sets of provided coordinates. <br>
	  * Example: <br>
	  * Grfx.drawQuad(g,100,100,200,300,400,400,300,200);
	  */
	 public static void drawQuad(Graphics g,int x1,int y1,int x2,int y2,int x3,int y3,int x4,int y4) ////// Added 06-25-08
	 {
	 	Polygon myPoly = new Polygon();
	 	myPoly.addPoint(x1,y1);
	 	myPoly.addPoint(x2,y2);
	 	myPoly.addPoint(x3,y3);
	 	myPoly.addPoint(x4,y4);
	 	g.drawPolygon(myPoly);
	}



	/**
	 * Draws an open rectangle.<br>
	 * The upper-left-hand corner is specified by x1,y1 and the lower-right-hand corner is specified by x2, y2. <br>
	 * Example: <br>
	 * Grfx.drawRectangle(g,100,200,300,400); <br>
	 * Draws an open rectangle whose upper-left-hand coordinate is (100,200) and whose lower-right-hand coordinate is (300,400).
	 */
	public static void drawRectangle(Graphics g, int x1, int y1, int x2, int y2)
	{
		int temp;
		if (x1 > x2)
			{ temp = x1; x1 = x2; x2 = temp; }
		if (y1 > y2)
			{ temp = y1; y1 = y2; y2 = temp; }
		int width  = x2 - x1 + 1;
		int height = y2 - y1 + 1;
		g.drawRect(x1,y1,width,height);
	}


	/**
	 * Draws an open rectangle with rounded corners.<br>
	 * The first 5 parameters (g and 4 ints) are the same as drawRect.<br>
	 * The final parameter "pixels" specifies the pixels used in the rounded corner. <br>
	 * Example: <br>
	 * Grfx.drawRoundedRectangle(g,100,200,300,400,25);
	 */
	public static void drawRoundedRectangle(Graphics g, int x1, int y1, int x2, int y2, int pixels)
	{
		int temp;
		if (x1 > x2) { temp = x1; x1 = x2; x2 = temp; }
		if (y1 > y2) { temp = y1; y1 = y2; y2 = temp; }
		int width  = x2 - x1 + 1;
		int height = y2 - y1 + 1;
		int diameter = pixels * 2;
		g.drawRoundRect(x1,y1,width,height,diameter,diameter);
	}


	/**
	 * Draws a "thick" line segment connecting coordinates x1,y2 and x2,y2.<br>
	 * The thickness is specified in the last parameter. <br>
	 * Example: <br>
	 * Grfx.drawThickLine(g,100,200,300,400,5); <br>
	 * Draws a "thick" line segment connecting the starting coordinate point (100,200) with the ending point (300,400). <br>
	 * The line will be 5 pixels thick.
	 */
	public static void drawThickLine(Graphics g, int x1, int y1, int x2, int y2, int thickness)
	{
		if (thickness < 2)
			thickness = 2;

		int start = -thickness / 2;
		int finish;
		if (thickness % 2 == 0)
			finish = thickness / 2;
		else
			finish = thickness / 2 - 1;

		for (int x = start; x <= finish; x++)
		   for (int y = start; y <= finish; y++)
			  g.drawLine(x1+x,y1+y,x2+x,y2+y);
	}


	/**
	 * Draws a "thick" circle.<br>
	 * The user specifies the x,y coordinate of the center of the circle as well as the radius value.<br>
	 * The thickness is specified in the last parameter. <br>
	 * Example: <br>
	 * Grfx.drawThickCircle(g,300,200,100,5); <br>
	 * Draws a "thick" open circle with a radius of 100 pixels whose center is located at the coordinate (300,200). <br>
	 * The edge of this circle will be 5 pixels thick.
	 */
	public static void drawThickCircle(Graphics g, int centerX, int centerY, int radius, int thickness)
	{
		int diameter = 2 * radius - 1;

		if (thickness < 2)
			thickness = 2;
		thickness--;

		for (int j = 0; j < thickness; j++)
		   g.drawOval(centerX-radius+j, centerY-radius+j, diameter-2*j, diameter-2*j);

		centerX++;

		for (int j = 0; j < thickness; j++)
		   g.drawOval(centerX-radius+j, centerY-radius+j, diameter-2*j, diameter-2*j);

		centerY++;

		for (int j = 0; j < thickness; j++)
		   g.drawOval(centerX-radius+j, centerY-radius+j, diameter-2*j, diameter-2*j);

		centerX--;

		for (int j = 0; j < thickness; j++)
		   g.drawOval(centerX-radius+j, centerY-radius+j, diameter-2*j, diameter-2*j);
	}


	/**
	 * Draws a "thick" arc which will look like a thick curved line.<br>
	 * The parameters are the same as drawArc, except there is an extra parameter at the end for the thickness.
	 */
	public static void drawThickArc(Graphics g, int centerX, int centerY, int hRadius, int vRadius, int start, int finish, int thickness)
	{
		int hDiameter = 2 * hRadius - 1;
		int vDiameter = 2 * vRadius - 1;

		if (thickness < 2)
			thickness = 2;
		thickness--;

		for (int j = 0; j < thickness; j++)
		   drawArc(g,centerX, centerY, hRadius-j, vRadius-j, start, finish);

		centerX++;

		for (int j = 0; j < thickness; j++)
		   drawArc(g,centerX, centerY, hRadius-j, vRadius-j, start, finish);

		centerY++;

		for (int j = 0; j < thickness; j++)
		   drawArc(g,centerX, centerY, hRadius-j, vRadius-j, start, finish);

		centerX--;

		for (int j = 0; j < thickness; j++)
		   drawArc(g,centerX, centerY, hRadius-j, vRadius-j, start, finish);
	}


	/**
	 * Draws an "thick" open irregular triangle using the three sets of provided coordinates.<br>
	 * The parameters are the same as drawTriangle, except there is an extra parameter at the end for the thickness.
	 */
	public static void drawThickTriangle(Graphics g,int x1,int y1,int x2,int y2,int x3,int y3,int thickness) ////// Added 10/21/2008
	{
	 	for (int x = 0; x < thickness; x++)
	 		for (int y = 0; y < thickness; y++)
	 			drawTriangle(g,x1+x,y1+y,x2+x,y2+y,x3+x,y3+y);
	}


	/**
	 * Draws an "thick" open irregular quadrilateral using the four sets of provided coordinates.<br>
	 * The parameters are the same as drawQuad, except there is an extra parameter at the end for the thickness.
	 */
	public static void drawThickQuad(Graphics g,int x1,int y1,int x2,int y2,int x3,int y3,int x4,int y4,int thickness) ////// Added 10/21/2008
	{
	 	for (int x = 0; x < thickness; x++)
	 		for (int y = 0; y < thickness; y++)
	 			drawQuad(g,x1+x,y1+y,x2+x,y2+y,x3+x,y3+y,x4+x,y4+y);
	}


	/**
	 * Draws a "thick" open rectangle.<br>
	 * The parameters are the same as drawRectangle, except there is an extra parameter at the end for the thickness.
	 */
	public static void drawThickRectangle(Graphics g, int x1, int y1, int x2, int y2, int thickness)
	{
		if (thickness < 2)
			thickness = 2;
		int temp;
		if (x1 > x2)
			{ temp = x1; x1 = x2; x2 = temp; }
		if (y1 > y2)
			{ temp = y1; y1 = y2; y2 = temp; }
		int width  = x2 - x1 + 1;
		int height = y2 - y1 + 1;
		for (int j = 0; j < thickness; j++)
		   g.drawRect(x1+j, y1+j, width-2*j, height-2*j);

	}


	/**
	 * Draws an "thick" open rectangle with rounded corners.<br>
	 * The parameters are the same as drawRoundedRectangle, except there is an extra parameter at the end for the thickness.
	 */
	public static void drawThickRoundedRectangle(Graphics g, int x1, int y1, int x2, int y2, int pixels, int thickness)
	{
		int temp;
		if (x1 > x2) { temp = x1; x1 = x2; x2 = temp; }
		if (y1 > y2) { temp = y1; y1 = y2; y2 = temp; }
		int width  = x2 - x1 + 1;
		int height = y2 - y1 + 1;
		int diameter = pixels * 2;

		if (thickness < 2)
			thickness = 2;
		thickness--;

		for (int j = 0; j < thickness; j++)
		   g.drawRoundRect(x1+j, y1+j, width-2*j, height-2*j,diameter,diameter);

		x1++;

		for (int j = 0; j < thickness; j++)
		   g.drawRoundRect(x1+j, y1+j, width-2*j, height-2*j,diameter,diameter);

		y1++;

		for (int j = 0; j < thickness; j++)
		   g.drawRoundRect(x1+j, y1+j, width-2*j, height-2*j,diameter,diameter);

		x1--;

		for (int j = 0; j < thickness; j++)
		   g.drawRoundRect(x1+j, y1+j, width-2*j, height-2*j,diameter,diameter);
	}


	/**
	 * Draws a solid "filled in" circle.<br>
	 * The user specifies the x,y coordinate of the center of the circle as well as the radius value. <br>
	 * Example: <br>
	 * Grfx.fillCircle(g,300,200,100); <br>
	 * Draws a solid circle with a radius of 100 pixels whose center is located at the coordinate (300,200).
	 */
	public static void fillCircle(Graphics g, int centerX, int centerY, int radius)
	{
		int diameter = 2 * radius;
		g.fillOval(centerX-radius, centerY-radius, diameter, diameter);
	}


	/**
	 * Draws a "solid" arc which will look either like a pie wedge or Pac-man.<br>
	 * A FILLED ARC is a "piece" of a SOLID OVAL.<br>
	 * The first 5 parameters (g and 4 ints) are the same as drawOval.<br>
	 * There are 2 additional parameters for the starting degree value and finishing degree of the arc. <br>
	 * 0 degrees is at the 12:00 position and the degrees progress in a CLOCKWISE fashion. <br>
	 * (90 degrees is at 3:00, 180 degrees is at 6:00, 270 degrees is at 9:00, 360 degrees is back at 12:00).
	 */
	public static void fillArc(Graphics g, int centerX, int centerY, int hRadius, int vRadius, int start, int finish)
	{
		int hDiameter = 2 * hRadius;
		int vDiameter = 2 * vRadius;

		if (finish < start)
			finish += 360;
		int newStart  = 90 - start;   // shifts starting position from 3:00 to 12:00
		int newFinish = start-finish; // as oppose to finish-start.  Subtracting backwards changes from counter-clockwise to clockwise.

		g.fillArc(centerX-hRadius, centerY-vRadius, hDiameter, vDiameter, newStart, newFinish);
	}



	/**
	 * Draws an solid "filled-in" irregular triangle using the three sets of provided coordinates. <br>
	 * Example: <br>
	 * Grfx.fillTriangle(g,100,300,200,100,300,300);
	 */
	public static void fillTriangle(Graphics g,int x1,int y1,int x2,int y2,int x3,int y3) ////// Added 06-25-08
	{
	 	Polygon myPoly = new Polygon();
	 	myPoly.addPoint(x1,y1);
	 	myPoly.addPoint(x2,y2);
	 	myPoly.addPoint(x3,y3);
	 	g.fillPolygon(myPoly);
	}


	/**
	 *	Draws an solid "filled-in" irregular quadrilateral using the four sets of provided coordinates.  <br>
	 * Example: <br>
	 * Grfx.fillQuad(g,100,100,200,300,400,400,300,200);
	 */
	public static void fillQuad(Graphics g,int x1,int y1,int x2,int y2,int x3,int y3,int x4,int y4) ////// Added 06-25-08
	{
	 	Polygon myPoly = new Polygon();
	 	myPoly.addPoint(x1,y1);
	 	myPoly.addPoint(x2,y2);
	 	myPoly.addPoint(x3,y3);
	 	myPoly.addPoint(x4,y4);
	 	g.fillPolygon(myPoly);
	}



	/**
	 * Draws a solid "filled in" rectangle.<br>
	 * The upper-left-hand corner is specified by x1,y1 and the lower-right-hand corner is specified by x2, y2. <br>
	 * Example: <br>
	 * Grfx.fillRectangle(g,100,200,300,400); <br>
	 * Draws an open rectangle whose upper-left-hand coordinate is (100,200) and whose lower-right-hand coordinate is (300,400).
	 */
	public static void fillRectangle(Graphics g, int x1, int y1, int x2, int y2)
	{
		int temp;
		if (x1 > x2)
			{ temp = x1; x1 = x2; x2 = temp; }
		if (y1 > y2)
			{ temp = y1; y1 = y2; y2 = temp; }
		int width  = x2 - x1 + 1;
		int height = y2 - y1 + 1;
		g.fillRect(x1,y1,width,height);
	}


	/**
	 * Draws a solid "filled in" rectangle with rounded corners. <br>
	 * The parameters are the same as drawRoundedRectangle.
	 */
	public static void fillRoundedRectangle(Graphics g, int x1, int y1, int x2, int y2, int pixels)
	{
		int temp;
		if (x1 > x2) { temp = x1; x1 = x2; x2 = temp; }
		if (y1 > y2) { temp = y1; y1 = y2; y2 = temp; }
		int width  = x2 - x1 + 1;
		int height = y2 - y1 + 1;
		int diameter = pixels * 2;
		g.fillRoundRect(x1,y1,width,height,diameter,diameter);
	}

	public static void drawStar(Graphics g, int points,int radius, int xCoord, int yCoord, Color color)
	{
		points *= 2;
		double twoPI = (Math.PI * 2);
		double halfPI = (Math.PI / 2);

		int xCoord1[] = new int[points];
		int yCoord1[] = new int[points];
		
		int currentRadius;

		for (int k = 0; k < points; k++)
		{
//			int radius = (int) Math.sqrt(Math.abs(Math.pow(xDrag - tempxDrag, 2) + Math.pow(yDrag - tempyDrag, 2)));
			if (k % 2 == 0)
				currentRadius = radius;
			else
				currentRadius = radius / 2;

			xCoord1[k] = (int) Math.round(Math.cos(twoPI * k/points - halfPI) * currentRadius) + xCoord;
			yCoord1[k] = (int) Math.round(Math.sin(twoPI * k/points - halfPI) * currentRadius) + yCoord;
		}
		g.setColor(color);
		g.drawPolygon(xCoord1, yCoord1, points);
		
	}
	

}