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
    private double likeability;
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

    // accessor for name
    public String getName() {
	return name;
    }

    // accessor for luck
    public double getLuck() {
	return luck;
    }

    // accessor for likeability
    public double getLikeability() {
	return likeability;
    }

    // mutator for yourCase
    public void setCase( int c ) {
	yourCase = c;
    }

} // end class Player
