/*=============================================
  class DealOrNoDeal -- Driver file for Kate and Bayle's FINAL PROJECT!
  Required classes: Player, Banker, Caseholder, Briefcase
  =============================================*/

import java.io.*;
import java.util.*;
import java.util.ArrayList;

public class DealOrNoDeal {

    // ~~~~~~~~~~~ INSTANCE VARIABLES ~~~~~~~~~~~

    private Player you;
    private ArrayList cases;
    private int[] values = {1,5,10,15,25,50,75,
			    100,200,300,400,500,750,
			    1000,5000,10000,25000,50000,75000,
			    100000,200000,300000,400000,500000,750000,
			    1000000};

    private InputStreamReader isr;
    private BufferedReader in;

    
    // ~~~~~~~~~~~ CONSTRUCTOR ~~~~~~~~~~~

    public DealOrNoDeal() {
	isr = new InputStreamReader( System.in );
	in = new BufferedReader( isr );

	// sets up arrayList of briefcases
	cases = new ArrayList<Briefcase>();
	for ( int x = 0 ; x < values.length ; x++ ) {
	    cases.add(x,new Briefcase(x));
	}

	shuffleValues();

	// assigns value to each briefcase
	for ( int x = 0 ; x < values.length ; x++ ) {
	    ((Briefcase)cases.get(x)).setValue(values[x]);
	}
	
	you = new Player();
    }

    
    // ~~~~~~~~~~~ METHODS ~~~~~~~~~~~

    // shuffles the elements of values
    public void shuffleValues() {
	int randomIndex;
        for( int i = values.length - 1; i > 0 ; i-- ) {
	    //pick an index at random
            randomIndex = (int)( (i+1) * Math.random() );
	    //swap the values at position i and randomIndex
	    int temp = values[i];
	    values[i] = values[randomIndex];
	    values[randomIndex] = temp;
        }
    }

    // displays the game board
    public void displayBoard() {
	String s = "";

	// MAJOR WORK IN PROGRESS
	for ( int x = cases.size() - 1 ; x >= 0 ; x-- ) {
	    if ( x == 6 || x == 13 || x == 20 ) {
		s += "\n";
	    }
	    s += x + "\t";
	}
	
	System.out.println(s);
    }

    // time to play!
    public void play(){
	
	String t = "\n*~~~~~~~~~~~~~~~~~~~~~~~~~~~~*\n";

	System.out.println(t);

	displayBoard();

    }


    // ~~~~~~~~~~~ MAIN METHOD ~~~~~~~~~~~

    public static void main( String[] args ) {

        DealOrNoDeal game = new DealOrNoDeal();
	
	game.play();

    }

} //end class DealOrNoDeal
