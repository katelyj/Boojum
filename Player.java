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
	luck = 1;
	likability = 1;
	yourCase = -1;
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
	    catch ( NumberFormatException e ) {
		System.out.println("\nThat doesn't look like a number.");
	    }
	    catch ( IOException e ) {}
	    
	}
	
	if ( n == 1 ) {
	    return "\nCongrats, you are a regular person!\nNow let's begin...";
	}
	if ( n == 2 ) {
	    setLuck(0.9);
	    setLikability(1.1);
	    return "\nCongrats, you are a likable person!\nNow let's begin...";
	}
	if ( n == 3 ) {
	    setLuck(1.1);
	    setLikability(0.9);
	    return "\nCongrats, you are a lucky person!\nNow let's begin...";
	}
	else {
	    System.out.println("\nUm, no.");
	    return inputMode();
	}
	
    }

    // picking your case
    public int pickCase() {
	int c = -1;
	while ( c < 0 || c > 24 ) {
	    System.out.println("Pick a case to open...\n");
	    try {
		c = Integer.parseInt(in.readLine());
	    }
	    catch ( NumberFormatException e ) {
		System.out.println("\nThat doesn't look like a number.");
	    }
	    catch ( IOException e ) {}
	    if ( c < 0 || c > 24 ) {
		System.out.println("\nPick a valid case, please.\n");
	    }
	}
	return c;
    }

    // mutator for yourCase
    public void setYourCase() {
	while ( yourCase < 0 || yourCase > 24 ) {
	    System.out.println("Choose a briefcase to be your own.\nBe wary. This case will stick with you throughout your time here...\n");
	    try {
		yourCase = Integer.parseInt(in.readLine());
	    }
	    catch ( NumberFormatException e ) {
		System.out.println("\nThat doesn't look like a number.");
	    }
	    catch ( IOException e ) {}
	    if ( yourCase < 0 || yourCase > 24 ) {
		System.out.println("\nPick a valid case, please.\n");
	    }
	}
    }
       
    // accessor for case
    public int getYourCase(){
	return yourCase;
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
    public void setLuck(double l) {
	luck = l;
    }
    
    // mutator for likability
    public void setLikability(double l) {
	likability = l;
    }

} // end class Player
