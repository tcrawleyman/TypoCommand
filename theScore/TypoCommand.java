// Typocommand -- Stage 7

// In Stage 7 the getNewLetter method is altered to created different kinds
// of Letter objects.  These "special" letters are all subclasses of Letter.
// Polymorphism allows the program to work with very little modification.

package theScore;

import java.awt.*;
import java.awt.event.*;
import java.applet.*;



public class TypoCommand extends Applet implements KeyListener, FocusListener
{
	/**
	 *
	 */
	public static TypoCommand instance;
	
	private static final long serialVersionUID = 1L;
	private int numLetters = 10;  	// This value should be between 1 and 30.
	private int numCities  =  8;	// This value needs to be 8.
	private int appletWidth;		// width of the Applet window
	private int appletHeight;		// height of the Applet window
	private Letter letters[];		// array of all falling letters
	private City cities[];			// array of 8 cities
	private LaserCannon laserCan;	// the 1 laser cannon

	@SuppressWarnings("unused")
	private Graphics g, gBuffer;	// used for virtual memory/double buffering
    private Image virtualMem;     	// to eliminate flicker in the animation

    private char keyFired;			// the last key pressed by the user
	private boolean focus;			// equals true when you click inside the applet window;
									// false when you click outside it

	boolean aKeyFired = false;
	public static int score = 0;
	private int addedScore = 0;
	private int failCounter = 0;

	
	private Image cannon;

	Font scoreFont = new Font("Castellar",Font.BOLD,30);




	public void init()
	{
		instance = this;
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
		boolean allAlive = false;
		for(int i=0; i<cities.length;i++)
		{
			if(cities[i].isAlive())
			{
				allAlive = true;
				break;
			}
			
		}
		if(allAlive)
		{

		
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
			cannon = getImage(getDocumentBase(),"laser.png");
			gBuffer.drawImage(cannon,475,440,this);

		}

		g.drawImage (virtualMem,0,0,this);
		repaint();  // makes the program repeat
		} else
		{
			Font gameOver = new Font("Magneto",Font.ITALIC,90);
			g.setFont(gameOver);
			g.setColor(Color.white);
			g.drawString("Game Over", 250, 300);
		}
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
		gBuffer.drawString("TYPOCOMMAND 2013",150,150);
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
				score += 100;
				failCounter = 0;
				
			}

			if(aKeyFired && keyFired != letters[j].getLetter())
			{
				score -= 100;
				aKeyFired = false;
				failCounter++;
				System.out.println(failCounter);
				
				if(failCounter == 21)
				score = 0;
			}


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
			if(score <=25000)
			{
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
			} else if(score <= 50000)
			{
				if(y<=100)
					addedScore=400;
				if(y<=200 && y>100)
					addedScore=380;
				if(y<=300 && y>200)
					addedScore = 360;
				if(y<=400 && y>300)
					addedScore=340;
				if(y<=500 && y>400)
					addedScore=320;
				if(y<=600 && y>400)
					addedScore=300;
			} else if(score <= 75000)
			{
				if(y<=100)
					addedScore=600;
				if(y<=200 && y>100)
					addedScore=580;
				if(y<=300 && y>200)
					addedScore=560;
				if(y<=400 && y>300)
					addedScore=540;
				if(y<=500 && y>400)
					addedScore=520;
				if(y<=600 && y>400)
					addedScore=500;
			} else if(score <= 100000)
			{
				if(y<=100)
					addedScore=800;
				if(y<=200 && y>100)
					addedScore=780;
				if(y<=300 && y>200)
					addedScore=760;
				if(y<=400 && y>300)
					addedScore=740;
				if(y<=500 && y>400)
					addedScore=720;
				if(y<=600 && y>400)
					addedScore=700;
			}
		return addedScore;


	} 
	
	public static int difficulty()
	{
		int difficulty = 1;
		
		if(score <= 50000)
		{
			difficulty = 1;
		} else if(score <= 75000)
		{
			difficulty = 2;
		} else
		{
			difficulty = 3;
		}
		
		return difficulty;
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
		Letter temp = new Letter(gBuffer);
		switch(Grfx.random(1,10))
		{
			case 1  :
			if(score>10000)	
			temp = new FastLetter(gBuffer); break;
			case 2  :
			if(score>20000)
			temp = new VanishingLetter(gBuffer); break;
			case 3  :
			if(score>30000)	
			temp = new ShrinkingLetter(gBuffer); break;
			case 4  :
			if(score>40000)	
			temp = new WarpingLetter(gBuffer); break;
			case 5  :
			if(score>50000)	
			temp = new MorphingLetter(gBuffer); break;
			default : temp = new Letter(gBuffer);
		}
		return temp;
	}


	public void drawCities()
	{
		for (int x = 0; x < numCities; x++){
			cities[x].drawCity();
		}
	}


	public void focusGained(FocusEvent evt) { focus = true;  }

	public void focusLost(FocusEvent evt) 	{ focus = false; }

	public void keyTyped(KeyEvent e) // used for normal characters
	{
		// The retrieved character is converted to a String, so it can be "up-cased",
		// and then is converted back into a character.
		keyFired = String.valueOf(e.getKeyChar()).toUpperCase().charAt(0);
		//System.out.println(keyFired);

		if (keyFired == '0')  // Prevents confusion of letter O and number 0.
			keyFired = 'O';
		if (keyFired == '`')
			score = 50100;
		aKeyFired = true;
	}


	// This methods are not used, but they are needed because this class "implements" KeyListener.
	public void keyReleased(KeyEvent e) { }

	public void keyPressed(KeyEvent e)  {

	}  // used for scan codes
	
	public int getScore()
	{
		return score;
	}
















}