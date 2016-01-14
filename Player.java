/*=============================================
  class Player - the user
  =============================================*/

import java.io.*;
import java.util.*;

public class Player {

    // ~~~~~~~~~~~ INSTANCE VARIABLES ~~~~~~~~~~~

    private String name;
    private int yourCase;
    private double luck;
    private double likability;
    private InputStreamReader isr;
    private BufferedReader in;


    // ~~~~~~~~~~~ CONSTRUCTOR ~~~~~~~~~~~

    public Player() {
	isr = new InputStreamReader( System.in );
	in = new BufferedReader( isr );
	name = inputName();
	luck = 0.5;
	likability = 0.5;
	System.out.println(inputMode());
	
    }


    // ~~~~~~~~~~~~~~ METHODS ~~~~~~~~~~~~~~~~~

    // gets and returns name from the user
    public String inputName() {
	String n = "";
	while ( n.equals("") ) { // to ensure non-empty names
	    System.out.println("Name? \n");
	    try {
		n = in.readLine();
	    }
	    catch ( IOException e ) {}
	}
	return n;
    }
    
   // sets mode
    public String inputMode() {
	int n = 0;
	while ( n == 0 ) {
	    
	    System.out.println("\nPlease type 1 if you would like to be a regular player." +
	    "\nPlease type 2 if you would like to be a likable player, but unlucky." +
	    "\nPlease type 3 if you would like to be a lucky player, but not likable.\n");
	    
	    try {
		n = Integer.parseInt(in.readLine());
	    }
	    catch ( IOException e ) {}
	    
	}
	
	if ( n == 1 ) {
	    return "\nCongrats, you are a regular person!";
	}
	if ( n == 2 ) {
	    setLuck(0.25);
	    setLikability(0.75);
	    return "\nCongrats, you are a likable person!";
	}
	if ( n == 3 ) {
	    setLuck(0.75);
	    setLikability(0.25);
	    return "\nCongrats, you are a lucky person!";
	}
	else {
	    System.out.println("Um, no. \n");
	    return inputMode();
	}
    }

    // accessor for name
    public String getName() {
	return name;
    }

    // accessor for luck
    public double getLuck() {
	return luck;
    }

    // accessor for likeability
    public double getLikability() {
	return likability;
    }

    // mutator for luck
    public void setLuck(double i) {
    	luck = i;
    }

    // mutator for likeability
    public void setLikability(double i) {
    	likability = i;
    }

    // mutator for yourCase
    public void setCase( int c ) {
	yourCase = c;
    }

} // end class Player
