// GameThing
// abstract super class for Letter, City & LaserCannon
// This same file is used for all stages.
// When doing the lab, do not alter this file.
// Several attributes are defined here because there are used in several if not all classes.

package theScore;

import java.awt.*;

abstract public class GameThing
{
	// static final attributes
	protected static final int appletWidth  = 1000;  	// The width  of the appletWindow
	protected static final int appletHeight =  600; 	// The height of the appletWindow

	protected static final int letterSize   =   29; 	// The pixel width of each letter

	protected static final int numColumns   = appletWidth / letterSize;	   // result is 34 columns

	protected static final int centerOffset = (appletWidth - numColumns * letterSize) / 2;
									 	// After the 8 Cities, the Laser Cannon, and the spaces in
										// between, this stores the extra space (divided by 2) for
										// centering the output.

	protected static final int laserCanTipX = 500;	// The X and Y values of the coordinate of the
	protected static final int laserCanTipY = 500;	// tip of the Laser Cannon.


    // non-static attributes
    protected Graphics g;				// The Graphics object g which is needed by any method in
	                            		// this class and its subclasses which generates output.

	protected int column; 	  			// Stores the starting column location of any object.
						    	    	// Letters are 1 column wide.  Citites are 3 columns wide.
						        		// The Laser Cannon is 2 columns wide.
						        		// There is 1 column spacing between all GameThing
						        		// objects on the ground.

	protected boolean alive;   			// Is the letter/city alive?
										// For a letter this means it has neither been shot
										// nor has it hit bottom yet.
										// For a city this means a falling letter has not hit it.
										// I chose to make the Laser Cannon indestructible,
										// but with a little manipulation falling letters could
								  	   	// destroy it as well.

	protected boolean exploding; 	 	// Is this object in the process of exploding?
	                  					// Meaning it was just hit by something.

	protected int maxExplodingCount; 	// Determines how long it takes for an object to explode.
	   									// Also determines the size of the explosion.

	protected int halfExplodingCount;  	// Halfway into the explosion,
	                                    // the laser will stop shooting.

	protected int explodingCountDown;  	// Starts at maxExplodingCount and counts down.
										// When it reached 0 the explosion is over,
										// and the GameThing object no longer exists.

	protected int xc, yc;               // Center coordinate of the GameThing object
	 									// Objects explode from their center.


	
	// constructor

	public GameThing(Graphics g)
	{
		alive = true;
		exploding = false;
		this.g = g;     //  allows g to be used by any method of this class without passing is as a parameter

		// The remaining attributes need to be initialized individually in the subclass constructors.
	}


	// concrete methods which will be inherited by each subclass

	public int getColumn()	  		{ return column; 	}

	public boolean isExploding()  	{ return exploding; }

	public boolean isAlive()  		{ return alive;  	}
	



	// abstract methods which will need to be redefined individually by each subclass

	abstract public void triggerExplosion();

	abstract public void explode();
}


