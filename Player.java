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
	likeability = 0.5;
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
   // gets and returns mode from the user
    public String inputMode() {
	int n = 0;
	while ( n == 0 ) { // to ensure non-empty names
	    System.out.println("Please type 1 if you would like to be a regular player" +
	    "\n Please type 2 if you would like to be a likable player, but unlucky" +
	    "\n Please type 3 if you would like to be a lucky player, but not likable");
	    try {
		n = in.parseInt(in.readLine());
	    }
	    catch ( IOException e ) {}
	}
	
	if (n == 2){
		return "Congrats, you are a regular person!";
	}
	if (n == 2){
		setLikability((getLikablility() + 1));
		return "Congrats, you are a likable person!";
	}
	if (n == 3){
		setLuck(getLuck() + .1);
		return "Congrats, you are a lucky person!"
	else {
		return "please specify your mode";
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
	return likeability;
    }
    public void setLikability(double i){
    	likability = i;
    }
    public void setLuck(double i){
    	luck = i;
    }

    // mutator for yourCase
    public void setCase( int c ) {
	yourCase = c;
    }

} // end class Player
