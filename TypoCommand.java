// Typocommand -- Stage 7

// In Stage 7 the getNewLetter method is altered to created different kinds
// of Letter objects.  These "special" letters are all subclasses of Letter.
// Polymorphism allows the program to work with very little modification.



import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.util.Random;


public class TypoCommand extends Applet implements KeyListener, FocusListener
{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private int numLetters = 10;  	// This value should be between 1 and 30.
	private int numCities  =  8;	// This value needs to be 8.
	private int appletWidth;		// width of the Applet window
	private int appletHeight;		// height of the Applet window
	private Letter letters[];		// array of all falling letters
	private City cities[];			// array of 8 cities
	private LaserCannon laserCan;	// the 1 laser cannon

	private Graphics g, gBuffer;	// used for virtual memory/double buffering
    private Image virtualMem;     	// to eliminate flicker in the animation

    private char keyFired;			// the last key pressed by the user
	private boolean focus;			// equals true when you click inside the applet window;
									// false when you click outside it

	private int score = 0;
	private int addedScore = 0;
	private int moo = 0;

	Font scoreFont = new Font("Castellar",Font.BOLD,30);




	public void init()
	{
		setSize(1000,650);
  		appletWidth = getWidth();
		appletHeight = getHeight();

		virtualMem = createImage(appletWidth,appletHeight);
		gBuffer = virtualMem.getGraphics();

		letters = new Letter[numLetters];       	// instantiates array
		for (int j = 0; j < numLetters; j++)
			letters[j] = getNewLetter(j,j,gBuffer);	// instantiates each array element

		cities = new City[numCities];  				// instantiates Cities array
		for (int j = 0; j < numCities; j++)
			cities[j] = new City(gBuffer,j);		// instantiates each individual City object

		laserCan = new LaserCannon(gBuffer);		// instantiates Laser Cannon

		keyFired = ' ';
		addKeyListener(this);
		addFocusListener(this);
		focus = false;
	}


	public void paint(Graphics g)
	{
		this.g = g;  // allows all methods to access g without passing it as a parameter

		if (!focus)
			titleScreen();  // The title screen is shown until the user click in the applet window.
		else
		{

			Grfx.setBackground(gBuffer,Grfx.black);

			Grfx.drawBackgroundStars(gBuffer);

			gBuffer.setFont(scoreFont);
			gBuffer.drawString("Score: " + score, 100, 100);
			drawLetters();
			drawCities();
			laserCan.drawLaserCannon();

		}

		g.drawImage (virtualMem,0,0,this);
		repaint();  // makes the program repeat
	}


	public void titleScreen()
	{
		// This screen is shown anytime the applet window is not in "focus".
		Grfx.setBackground(gBuffer,Grfx.darkRed);
		gBuffer.setColor(Grfx.darkBlue);
		Grfx.drawThickRectangle(gBuffer,0,0,appletWidth,appletHeight,48);
		Grfx.drawThickRoundedRectangle(gBuffer,0,0,appletWidth,appletHeight,48,48);

		Font title = new Font("Algerian",Font.ITALIC,72);
		Font directions = new Font("Times New Roman",Font.BOLD,36);
		Font footNote = new Font("Times New Roman",Font.PLAIN,24);

		gBuffer.setFont(title);
		gBuffer.setColor(Grfx.white);
		gBuffer.drawString("TYPOCOMMAND 2012",150,150);
		gBuffer.setFont(directions);
		gBuffer.drawString("By: Team YOLO SWAG",320,235);

		gBuffer.drawString("Instructions:",400,400);
		gBuffer.setFont(footNote);
		gBuffer.drawString("Based on Atari's \"Missile Command\" and \"Typo Attack\"" ,245,305);
		gBuffer.drawString("Click inside the window to begin/resume the game.",255,470);
	}


	public void drawLetters()  // This method is unchanged from Stage 6E.
	{
		for (int j = 0; j < numLetters; j++)
		{
			letters[j].fall(); // move every letter down a bit

			if (!letters[j].isExploding() && letters[j].getLetter() == keyFired && letters[j].onScreen())
			{
				letters[j].triggerExplosion();
				addScore();
				score += addedScore;
			}

//			if (!letters[j].isExploding() && letters[j].getLetter() != keyFired)
//			{
//				score -= 50;
//			}


			if (letters[j].isBeingTrackedAndShot())
				letters[j].drawLaser();

			if (letters[j].hitBottom())
			{
				for (int c = 0; c < numCities; c++)
					if (!letters[j].isExploding() && cities[c].isAlive() && cities[c].hit(letters[j].getColumn()))  // Did a letter hit a city when it hit bottom?
						cities[c].triggerExplosion();

				letters[j] = getNewLetter(j,numLetters,gBuffer);
				// A new letter is created when the old letter hits bottom.
			}

			if (!letters[j].isAlive())
			{
				letters[j] = getNewLetter(j,numLetters,gBuffer);
				// A new letter is created when the old letter is shot with a laser.
			}
		}
	}


	public void update(Graphics g)
	{
		paint(g);
	}

	public int addScore()
	{
		int y = 0;
		for(int k = 0; k < letters.length; k++){
			y = letters[k].getLetterY();
		}

		if(y<=100)
			addedScore=200;
		if(y<=200 && y>100)
		addedScore=180;
		if(y<=300 && y>200)
		addedScore = 160;
		if(y<=400 && y>300)
			addedScore=140;
		if(y<=500 && y>400)
			addedScore=120;
		if(y<=600 && y>400)
			addedScore=100;

		return addedScore;


	}


	public Letter getNewLetter(int insertIndex, int currentArraySize, Graphics gBuffer)
	{
		Letter temp = getSpecialLetter();

		// This while loop prevents letters from being on top of each other by not
		// allowing a new letter to be placed in the same column as any existing letter.
		boolean duplicateColumn = true;
		while (duplicateColumn)
		{
		   	duplicateColumn = false;
		   	for (int k = 0; k < currentArraySize; k++)
		    	if (k != insertIndex && temp.getColumn() == letters[k].getColumn())
		    		duplicateColumn = true;
			if (duplicateColumn)
			    temp = getSpecialLetter();
		}
		return temp;
	}


	private Letter getSpecialLetter()
	{
		Letter temp;
		switch(Grfx.random(1,6))
		{
			case 1  : temp = new FastLetter(gBuffer); break;
			case 2  : temp = new VanishingLetter(gBuffer); break;
			case 3  : temp = new ShrinkingLetter(gBuffer); break;
			default : temp = new Letter(gBuffer);
		}
		return temp;
	}


	public void drawCities()
	{
		for (int x = 0; x < numCities; x++)
			cities[x].drawCity();
	}


	public void focusGained(FocusEvent evt) { focus = true;  }

	public void focusLost(FocusEvent evt) 	{ focus = false; }

	public void keyTyped(KeyEvent e) // used for normal characters
	{
		// The retrieved character is converted to a String, so it can be "up-cased",
		// and then is converted back into a character.
		keyFired = String.valueOf(e.getKeyChar()).toUpperCase().charAt(0);
		//System.out.print(keyFired);

		if (keyFired == '0')  // Prevents confusion of letter O and number 0.
			keyFired = 'O';
	}


	// This methods are not used, but they are needed because this class "implements" KeyListener.
	public void keyReleased(KeyEvent e) { }

	public void keyPressed(KeyEvent e)  { }  // used for scan codes
}