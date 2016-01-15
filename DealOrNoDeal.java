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
    private ArrayList chosenValues;
    private int[] values;
    private final int[] fvalues = {1,5,10,15,25,50,75,
			    100,200,500,750,1000,5000,10000,25000,50000,75000,
			    100000,200000,300000,400000,500000,750000,1000000};
    private InputStreamReader isr;
    private BufferedReader in;

    
    // ~~~~~~~~~~~ CONSTRUCTOR ~~~~~~~~~~~

    public DealOrNoDeal() {
	isr = new InputStreamReader( System.in );
	in = new BufferedReader( isr );
	chosenValues = new ArrayList<Integer>();

	// sets up values array
	values = new int[fvalues.length];
	for ( int x = 0 ; x < values.length ; x++ ) {
	    values[x] = fvalues[x];
	}

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
        for( int i = values.length - 1 ; i > 0 ; i-- ) {
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
	String s = "\n*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*\n";
	s += "\t\tBRIEFCASES\n";
        s += "*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*\n";

	// the cases

	// puts case numbers in a 2d array (makes it easier to display neatly later)
	int[][] d = new int[4][cases.size()/4]; // 4 rows
	int count = cases.size() - 1;
	for ( int x = 0 ; x < d.length ; x++ ) {
	    for ( int y = 0 ; y < d[x].length ; y++ ) {
		d[x][y] = count;
		count -= 1;
	    }
	}

	for ( int x = 0 ; x < d.length ; x++ ) {
	    for ( int y = d[x].length - 1 ; y >= 0 ; y-- ) {
		if ( ! ((Briefcase)cases.get(d[x][y])).isOpen() ) {
		    // as long as the case is closed, it displays
		    s += d[x][y] + "\t";
		}
		else {
		    // if the case is open, the space is blank
		    s += "\t";
		}
	    }
	    s += "\n";
	}

	s += "*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*\n";

	// the values

	s += "\t\t$$$$$$$$\n";
	s += "*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*\n";

	// puts values in a 2d array (makes it easier to display neatly later)
	int[][] t = new int[4][fvalues.length/4]; // 4 rows
	int count1 = 0;
	for ( int x = 0 ; x < t.length ; x++ ) {
	    for ( int y = 0 ; y < t[x].length ; y++ ) {
		t[x][y] = fvalues[count1];
		count1 += 1;
	    }
	}

	for ( int x = 0 ; x < t.length ; x++ ) {
	    for ( int y = 0 ; y < t[x].length ; y++ ) {
		if ( ! chosenValues.contains(t[x][y]) ) { // if not already chosen, displays value
		    s += t[x][y] + "\t";
		}
		else { // if already chosen, leaves space blank
		    s += "\t";
		}
	    }
	    s += "\n";
	}

	s += "*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*\n";
	
	System.out.println(s);
    }
    
    // waits one second
    public void waitSec() {
	try {
	    Thread.sleep(1000); //1000 milliseconds is one second.
	} catch(InterruptedException ex) {
	    Thread.currentThread().interrupt();
	}
    }

    // goes through case choosing r times
    public void round(int r) {
	int choice;
	while ( r != 0 ) {
	    choice = you.pickCase(); // picks the case
	    Briefcase b = ((Briefcase)cases.get(choice)); // for clenliness
	    if ( b.isOpen() ) { // if already open
		System.out.println("\nPlease choose a case not already chosen!\n");
	    }
	    else {
		b.setOpen(true); // sets briefcase to open
		chosenValues.add(b.getValue()); // adds value to chosenValues
		
		System.out.println("\nAMOUNT IN CASE: \n");
		System.out.println("bum");
		waitSec();
		System.out.println("bum");
		waitSec();
		System.out.println("bum");
		waitSec();
		System.out.println("\n*~~~~~~~~~~$" + b.getValue() + "!~~~~~~~~~~*");

		r -= 1;
		System.out.println("\nYou have " + r + " more briefcases to open!\n");
		waitSec();
		System.out.println("----------------------------------------------------------------");

		displayBoard();
	    }
	}
    }

    // the dramatic conclusion...
    public void finalTwo() {
	
    }
    
    // time to play!
    public void play() {
	
	displayBoard();

	// choosing your case
	you.setYourCase();
	((Briefcase)cases.get(you.getYourCase())).setOpen(true);

	displayBoard();

	round(6);

	//round(6);

	//round(4);

	//round(4);

	//round(1);

	//round(1);

	//finalTwo();

    }


    // ~~~~~~~~~~~ MAIN METHOD ~~~~~~~~~~~

    public static void main( String[] args ) {

        DealOrNoDeal game = new DealOrNoDeal();
	
	game.play();

    }

} //end class DealOrNoDeal
