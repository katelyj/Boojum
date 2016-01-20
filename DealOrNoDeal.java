/*=============================================
  class DealOrNoDeal -- The main functionality of the game!
  Required classes: Player, Dealer, Caseholders, Briefcase
  =============================================*/

import java.io.*;
import java.util.*;
import java.util.ArrayList;

public class DealOrNoDeal {

    // ~~~~~~~~~~~ INSTANCE VARIABLES ~~~~~~~~~~~
    
    private InputStreamReader isr;
    private BufferedReader in;
    private Player you;
    private Dealer banker;
    private CaseHolders caseholder;
    private boolean gameOver;
    private int finalAmount;
    private ArrayList cases;
    private ArrayList chosenValues;
    private int[] values;
    private final int[] fvalues = {1,5,10,15,25,50,75,
			    100,200,500,750,1000,5000,10000,25000,50000,75000,
			    100000,200000,300000,400000,500000,750000,1000000};
    private final int[] roundOrder = {6,6,4,4,1,1,-1}; // -1 indicates round for final 2 cases

    
    // ~~~~~~~~~~~ CONSTRUCTOR ~~~~~~~~~~~

    public DealOrNoDeal() {
	isr = new InputStreamReader( System.in );
	in = new BufferedReader( isr );
	gameOver = false;
	finalAmount = 0;
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
	banker = new Dealer(you.getLuck());
	caseholder = new CaseHolders(you.getLikability());
    }

    
    // ~~~~~~~~~~~ METHODS -- HELPER ~~~~~~~~~~~

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
		    s += commafy(t[x][y]) + "\t";
		}
		else { // if already chosen, leaves space blank
		    s += "\t";
		}
	    }
	    s += "\n";
	}

	s += "*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*\n";
	if ( you.getYourCase() != -1 ) {
	    s += "\t\tYOUR CASE: " + you.getYourCase() + "\n";
	}
	
	System.out.println(s);
    }

    // puts commas in appropriate place when printing numbers
    public static String commafy(int i) {
	String s = "" + i;
	if ( s.length() < 4 ){
	    return s;
	}
	else {
	    return commafy(Integer.parseInt(s.substring(0, s.length() - 3))) + "," +         
		s.substring(s.length() - 3, s.length());
	}
    }
    
    // waits four fifths of a second (the name is misleading, we know)
    public void waitSec() {
	try {
	    Thread.sleep(800); // 1000 milliseconds is one second
	} catch(InterruptedException ex) {
	    Thread.currentThread().interrupt();
	}
    }

    // returns if a number is a valid amount in our game of Deal Or No Deal (aka if it's in the instance variable values)
    public boolean isValue(int amount) {
	for ( int v : values ) {
	    if ( amount == v ) {
		return true;
	    }
	}
	return false;
    }

    // ~~~~~~~~~~~ METHODS -- GAMEPLAY ~~~~~~~~~~~

    // goes through case choosing r times
    public void round(int r) {
	if ( r == -1 ) { // the thrilling conclusion
	    finalTwo();
	    return;
	}
	
	int choice;
        String s = "This round, you will be opening " + r + " case";
	if ( r != 1 ) {
	    s += "s.\n";
	}
	else {
	    s += ".\n";
	}
	System.out.println(s);
	waitSec();
	
	while ( r != 0 ) {
	    choice = you.pickCase(); // picks the case
	    Briefcase b = ((Briefcase)cases.get(choice)); // for clenliness
	    if ( b.isOpen() ) { // if already open
		System.out.println("\nPlease choose a case not already chosen!\n");
	    }
	    else {
		b.setOpen(true); // sets briefcase to open
		int val = b.getValue();
		chosenValues.add(val); // adds value to chosenValues

		fiveHunna(val); // guess the value before showing what it is
		
		System.out.println("\nAMOUNT IN CASE: \n");
		System.out.println("bum");
		waitSec();
		System.out.println("bum");
		waitSec();
		System.out.println("bum");
		waitSec();
		System.out.println("\n*~~~~~~~~~~$" + commafy(val) + "!~~~~~~~~~~*");
		waitSec();
		
		String response = caseholder.response(val);	
		System.out.println("\n" + response);
		waitSec();

		r -= 1;
		System.out.println("\nYou have " + r + " more briefcases to open!\n");
		waitSec();
		System.out.println("--------------------------------------------------------------------------");

		displayBoard();
	    }
	}
    }

    // the dramatic conclusion...
    public void finalTwo() {
	int choice;

	System.out.println("Only two briefcases remain.\n");
	waitSec();
	System.out.println("Will you choose to open your own case (case " + you.getYourCase() + "), or the one remaining on the board?\n");
	waitSec();
	System.out.println("Choose wisely. The amount in the case you choose will contain the amount of money you win.\n");
	waitSec();
	
	choice = you.pickCase(); // picks the case

	Briefcase b = ((Briefcase)cases.get(choice)); // for clenliness
	if ( b.isOpen() && choice != you.getYourCase() ) {
	    System.out.println("\nUmm... Pick a valid case, please.\nLet's start this over...\n");
	    waitSec();
	    finalTwo();
	}
	else {
	    finalAmount += b.getValue();
	    System.out.println("\n--------------------------------------------------------------------------");
	    System.out.println("\nAnd the amount in the case is...\n");
	    System.out.println("bum");
	    waitSec();
	    System.out.println("bum");
	    waitSec();
	    System.out.println("bum");
	    waitSec();
	    System.out.println("\n*~~~~~~~~~~$" + commafy(finalAmount) + "!!!!!~~~~~~~~~~*");
	    waitSec();
	    end();
	    System.out.println("--------------------------------------------------------------------------");
	}
    }

    // the banker's time to shine!
    public void deal() {
	System.out.println("It's time for a deal.\n");
	waitSec();
	System.out.println("Please wait while the banker is calculating...\n");
	System.out.println("...");
	waitSec();
	System.out.println("...");
	waitSec();
	System.out.println("...");
	waitSec();
	int d = banker.deal(chosenValues,values);
	System.out.println("\nBANKER'S OFFER: $" + commafy(d) + "!");
	waitSec();
	
	if ( you.dealOrNoDeal().equals("deal") ) {
	    finalAmount += d;
	    end();
	}
	
	System.out.println("--------------------------------------------------------------------------\n");
    }
    
    // gives the player yet another chance to test their luck at the very end of the game
    public void box24() {
	System.out.println("\n--------------------------------------------------------------------------\n");
	System.out.println("A quick detour...\n");
	System.out.println("--------------------------------------------------------------------------\n");
	String ans = "";

	while ( (! ans.equals("yes")) && (! ans.equals("no")) ) {
		
	    System.out.println("Before we end... would you like to buy the very mysterious box 24 for $" + commafy(finalAmount)+ " (all of your money)?\n\nIt contains a mystery prize... \nThe prize is either: DOUBLING your money, adding $10,000 to the amount you made, just getting your money back, getting half your money, or getting nothing (ending up with $0).\n\nWill you take that chance? (yes/no)\n" );
	    try {
		ans = in.readLine();
	    }
	    catch ( IOException e ) {}

	    if ( (! ans.equals("yes")) && (! ans.equals("no")) ) {
		System.out.println("\nYes or no only, please.\n");
	    }
	}
	
	if ( ans.equals("yes") ) { // the user has taken this chance
	    System.out.println("\nHere we go...\n");
	    System.out.println("bum");
	    waitSec();
	    System.out.println("bum");
	    waitSec();
	    System.out.println("bum");
	    waitSec();

	    double prob = Math.random();
	    if ( prob > .8 ) { // user has won double their money
		System.out.println("\nYOU JUST WON DOUBLE YOUR MONEY!!!!! CONGRATULATIONS!\n");
	        finalAmount *= 2;
	    }
	    else if ( prob > .6 ) { // user has won an additional $10,000
		System.out.println("\nYOU JUST WON AN ADDITIONAL $10,000!!!!! CONGRATS, MAN!\n");
		finalAmount += 10000;
	    }
	    else if ( prob > .4 ) { // user just gets their original amount
		System.out.println("\nYou get the same amount! Nothing has changed.\n");
	    }
	    else if ( prob > .2 ) { // user loses half their money
		System.out.println("\nD'OH! You just lost half your money! Ouch...\n");
		finalAmount /= 2;
	    }
	    else { // user loses everything
		System.out.println("\nOh, man! You lost everything! Too bad. Play smarter next time!\n");
	        finalAmount = 0;
	    }
	}
	
	else {  // the user is playing it safe
	    System.out.println("\nPlayin it safe, eh?\n");
	    System.out.println("Alrighty then.\n");
	}

	waitSec();
    }

    // guess what's in the case to win (or lose) $500!
    public void fiveHunna(int amount) {
	System.out.println("\nBefore we show you what's in the case... \nwould you like to guess what it contains?");
	String ans = "";
	
	while ( (! ans.equals("yes")) && (! ans.equals("no")) ) {
	    System.out.println("\n(yes/no)\n");
	    try {
		ans = in.readLine();
	    }
	    catch ( IOException e ) {}
	}

	if ( ans.equals("yes") ) { // the player is taking a chance!
	    int guess = 0;
	    
	    while ( guess == 0 ) {
		System.out.println("\nGo ahead! Take a guess!\n");
		try {
		    System.out.print("$");
		    guess = Integer.parseInt(in.readLine());
		}
		catch ( NumberFormatException e ) {
		    System.out.println("\nThat's not okay.");
		}
		catch ( IOException e ) {}

		if ( (! isValue(guess)) || (chosenValues.contains(guess)) ) {
		    System.out.println("\nInvalid guesses are to your own detriment, man. Trust me.");
		    guess = 0;
		}
	    }

	    if ( guess == amount ) { // guess is correct
		System.out.println("\nSomehow, that is correct! \n$500 has been added to your final balance. Great job!\n");
		finalAmount += 500;
	    }
	    else { // guess is incorrect
		System.out.println("\nSorry, but that is incorrect. \n$500 has been subtracted from your final balance. Better luck next time!");
		finalAmount -= 500;
	    }
	}
	
	else { // the player is a dud
	    System.out.println("\nWhimp.");
	}

	waitSec();
    }

    // the true end of the game
    public void end() {
	box24();
	gameOver = true;
	System.out.println("--------------------------------------------------------------------------");
	System.out.println("\nCONGRATULATIONS!!!!!\n\nYOU JUST WON $" + commafy(finalAmount) + "!!!!!!!");
	System.out.println("\nThanks for playing!\n");
    }
    
    // time to play!
    public void play() {

	// for new players
	you.rules();

	// choosing your case
	displayBoard();
	you.setYourCase();
	((Briefcase)cases.get(you.getYourCase())).setOpen(true);

	// round time!
	for ( int r : roundOrder ) {
	    if ( ! gameOver ) {
		displayBoard();
		round(r);
		if ( r != -1 ) { // so a deal will not happen in the final round
		    deal();
		}
	    }
	}

    }

} // end class DealOrNoDeal
