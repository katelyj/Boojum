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
    // make a final and copy version of this:
    private int[] values = {1,5,10,15,25,50,75,
			    100,200,500,750,1000,5000,10000,25000,50000,75000,
			    100000,200000,300000,400000,500000,750000,1000000};
    private final int[] finVals = {1,5,10,15,25,50,75,
			    100,200,500,750,1000,5000,10000,25000,50000,75000,
			    100000,200000,300000,400000,500000,750000,1000000};
    private InputStreamReader isr;
    private BufferedReader in;
    private int phase;

    
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
	phase = 1;
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
	String s = "\n*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*\n";

	// puts values in a 2d array (makes it easier to display neatly later)
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
	
	System.out.println(s);
    }
    public void displayValues(){
	System.out.println("remaining values:");
	int ctr = 0;
	while (ctr < 6){System.out.print(finVals[ctr] + "\t");ctr++;}
	System.out.print("\n");
	while (ctr < 12){System.out.print(finVals[ctr] + "\t");ctr++;}
	System.out.print("\n");
	while (ctr < 18){System.out.print(finVals[ctr] + "\t");ctr++;}
	System.out.print("\n");
	while (ctr < 24){System.out.print(finVals[ctr] + "\t");ctr++;}
	System.out.print("\n");
	
    }
    public void guess(){
	isr = new InputStreamReader( System.in );
	in = new BufferedReader( isr );
	int choice = 0;
	int ctr = 0;
	if (phase == 1 || phase == 2){
	    
	    try {
		if (ctr >= 6)return;
		System.out.println(ctr);
		System.out.println("You have " + (6 - ctr) + " briefcases to open!");		    		
		System.out.println("pick a briefcase to open!");
		choice = Integer.parseInt(in.readLine());
		System.out.println("briefcase " + choice + " contains: " + 	((Briefcase)cases.get(choice)).getValue());		    
		ctr++;
	    }
	    catch ( IndexOutOfBoundsException e){
		System.out.println("\nThat number is not a briefcase!");
	    }
	    catch ( NumberFormatException e ) {
		System.out.println("\nThat doesn't look like a number.");
	    }
	    catch ( IOException e ) {}	    	
	}
	else if (phase == 3 || phase == 4){}
	else if (phase == 5 || phase == 6){}
    }
    public void deal(){}

	
    // time to play!
    public void play(){
	int phase = 1;
	displayBoard();
	displayValues();
	
	you.setYourCase();
	((Briefcase)cases.get(you.getYourCase())).setOpen(true);

	displayBoard();
	for (int i = 0; i < 6; i++){
	    guess();
	    deal();
	    phase ++;
	}

    }


    // ~~~~~~~~~~~ MAIN METHOD ~~~~~~~~~~~

    public static void main( String[] args ) {

        DealOrNoDeal game = new DealOrNoDeal();
	
	game.play();

    }

} //end class DealOrNoDeal
