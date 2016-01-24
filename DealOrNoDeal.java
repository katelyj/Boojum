/*=============================================
  class DealOrNoDeal -- The main functionality of the game!
  Required classes: Player, Dealer, Caseholders, Briefcase, Animation, Values, Waiter, Lucky
  =============================================*/

import java.io.*;
import java.util.*;
import java.util.ArrayList;

public class DealOrNoDeal extends Values implements Waiter  {

    // ~~~~~~~~~~~ INSTANCE VARIABLES ~~~~~~~~~~~
   
    private InputStreamReader isr;
    private BufferedReader in;
    
    private Player you;
    private Dealer banker;
    private CaseHolders caseholder;
    
    private boolean gameOver;
    private int finalAmount;
    
    private ArrayList cases;
    private int[] values;
    private final int[] roundOrder = {6,6,4,4,1,1,-1}; // -1 indicates round for final 2 cases

    
    // ~~~~~~~~~~~ CONSTRUCTOR ~~~~~~~~~~~

    public DealOrNoDeal() {
        super();
	isr = new InputStreamReader( System.in );
	in = new BufferedReader( isr );
	
	gameOver = false;
	finalAmount = 0;
	choVals = new ArrayList<Integer>();

	// sets up values array (not ArrayList so it can match up with fValues)
	// and also it's easier to use arrays for mathematical purposes (you have ints instead of Integers)
	values = new int[fValues.length];
	for ( int x = 0 ; x < values.length ; x++ ) {
	    values[x] = fValues[x];
	}

	// sets up arrayList of briefcases
	cases = new ArrayList<Briefcase>();
	for ( int x = 0 ; x < values.length ; x++ ) {
	    cases.add(x,new Briefcase(x));
	}

	// shuffles the elements of values
	shuffleValues();

	// assigns value to each briefcase
	for ( int x = 0 ; x < values.length ; x++ ) {
	    ((Briefcase)cases.get(x)).setValue(values[x]);
	}

	// sets up our three "characters"
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

	// THE BRIEFCASES
	
	String s = "\n*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*\n";
	s += "\t\tBRIEFCASES\n";
        s += "*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*\n";

	// puts case numbers in a 2d array (makes it easier to display neatly later)
	int[][] d = new int[4][cases.size()/4]; // 4 rows
	int count = cases.size() - 1;
	for ( int x = 0 ; x < d.length ; x++ ) {
	    for ( int y = 0 ; y < d[x].length ; y++ ) {
		d[x][y] = count;
		count -= 1;
	    }
	}

	// displaying in the correct format
	for ( int x = 0 ; x < d.length ; x++ ) {
	    for ( int y = d[x].length - 1 ; y >= 0 ; y-- ) {
		if ( ! ((Briefcase)cases.get(d[x][y])).isOpen() ) { // if the briefcase is open, it displays
		    s += d[x][y] + "\t";
		}
		else { // if the briefcase is closed, it skips a space
		    s += "\t";
		}
	    }
	    s += "\n";
	}

	// THE VALUES

	s += "*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*\n";
	s += "\t\t$$$$$$$$\n";
	s += "*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*\n";

	// puts values in a 2d array (makes it easier to display neatly later)
	int[][] t = new int[4][fValues.length/4]; // 4 rows
	int count1 = 0;
	for ( int x = 0 ; x < t.length ; x++ ) {
	    for ( int y = 0 ; y < t[x].length ; y++ ) {
		t[x][y] = fValues[count1];
		count1 += 1;
	    }
	}

	// displaying in the correct format
	for ( int x = 0 ; x < t.length ; x++ ) {
	    for ( int y = 0 ; y < t[x].length ; y++ ) {
		if ( ! choVals.contains(t[x][y]) ) { // if not already chosen, displays value
		    s += commafy(t[x][y]) + "\t";
		}
		else { // if already chosen, leaves space blank
		    s += "\t";
		}
	    }
	    s += "\n";
	}

	s += "*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*\n";

	// displaying your own case at the bottom
	if ( you.getYourCase() != -1 ) { // as long as the user has chosen a case
	    s += "\t\tYOUR CASE: " + you.getYourCase() + "\n";
	}

	// and we're (finally) done!
	System.out.println(s);
    }

    // puts commas in appropriate place when printing numbers (for clarity)
    public static String commafy(int i) {
	String s = "" + i;
	
	if ( s.length() < 4 ) { // no need to add commas
	    return s;
	}
	
	else { // recursion yay
	    return commafy(Integer.parseInt(s.substring(0, s.length() - 3))) + "," +         
		s.substring(s.length() - 3, s.length());
	}
    }
    
    // waits four fifths of a second (the name is misleading, we know)
    public void waits() {
	try {
	    Thread.sleep(800); // 1000 milliseconds is one second
	}
	catch(InterruptedException ex) {
	    Thread.currentThread().interrupt();
	}
    }

    // returns if a number is a valid amount to guess (aka if it's in the instance variable values)
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
	    return; // no need to go through the rest of this method, as the game is over
	}
	
	int choice;
        String s = "This round, you will be opening " + r + " case";
	if ( r != 1 ) { // if more than one, case is plural
	    s += "s.\n";
	}
	else { // if only one, case is singular
	    s += ".\n";
	}
	
	System.out.println(s);
	waits();
	
	while ( r != 0 ) { // while there are still more cases to choose (r is the amount of cases needed to be open)
	    choice = you.pickCase(); // picks the case
	    Briefcase b = ((Briefcase)cases.get(choice)); // for clenliness
	    
	    if ( b.isOpen() ) { // if already open, the user cannot choose the case again
		System.out.println("\nPlease choose a case not already chosen!\n");
	    }
	    
	    else {
		b.setOpen(true); // sets briefcase to open
		int val = b.getValue();
		choVals.add(val); // adds value to choVals

		fiveHunna(val); // guess the value before showing what it is
		
		System.out.println("\nAMOUNT IN CASE: \n");
		System.out.println("bum"); // suspense!
		waits();
		System.out.println("bum");
		waits();
		System.out.println("bum");
		waits();
		System.out.println("\n*~~~~~~~~~~$" + commafy(val) + "!~~~~~~~~~~*"); // shows the value
		waits();	
		System.out.println("\n" + caseholder.response(val)); // shows an appropriate comment
		waits();

		r -= 1; // one less case to open
		System.out.println("\nYou have " + r + " more briefcase");
		
		if ( r == 1 ) { // briefcase should be singular
		    System.out.print(" to open!\n");
		}
		else { // briefcase should be plural
		    System.out.print("s to open!\n");
		}
		
		waits();
		System.out.println("--------------------------------------------------------------------------");

		displayBoard();
	    }
	}
    }

    // the dramatic conclusion...
    public void finalTwo() {
	int choice;

	System.out.println("Only two briefcases remain.\n");
	waits();
	System.out.println("Will you choose to open your own case (case " + you.getYourCase() + "), or the one remaining on the board?\n");
	waits();
	System.out.println("Choose wisely. The amount in the case you choose will contain the amount of money you win.\n");
	waits();
	
	choice = you.pickCase(); // picks the case
	Briefcase b = ((Briefcase)cases.get(choice)); // for clenliness
	
	if ( b.isOpen() && choice != you.getYourCase() ) { // the chosen briefcase is already open (and not the user's case, which is open and choosable)
	    System.out.println("\nUmm... Pick a valid case, please.\nLet's start this over...\n");
	    waits();
	    finalTwo();
	}
	
	else { // here we go...
	    int val = b.getValue();
	    finalAmount += val; // value added to amount user will win
	    System.out.println("\n--------------------------------------------------------------------------");
	    
	    System.out.println("\nAnd the amount in the case is...\n");
	    System.out.println("bum");
	    waits();
	    System.out.println("bum");
	    waits();
	    System.out.println("bum");
	    waits();
	    System.out.println("\n*~~~~~~~~~~$" + commafy(val) + "!!!!!~~~~~~~~~~*"); // shows amount in case
	    waits();
	    
	    end(); // the end of the game
	    System.out.println("--------------------------------------------------------------------------");
	}
    }

    // the banker's time to shine!
    public void deal() {
	System.out.println("It's time for a deal.\n");
	waits();
	System.out.println("Please wait while the banker is calculating...\n");
	System.out.println("...");
	waits();
	System.out.println("...");
	waits();
	System.out.println("...");
	waits();
	
	int d = banker.deal(choVals); // the banker's offer
	System.out.println("\nBANKER'S OFFER: $" + commafy(d) + "!");
	waits();
	System.out.println("\nIf you accept this amount as your winnings, the game will end, and you will never know what was in your own briefcase.");
	waits();
	
	if ( you.dealOrNoDeal().equals("deal") ) { // if the user has made the deal
	    finalAmount += d; // amount is added to amount user will win
	    end(); // the end of the game
	}
	
	System.out.println("--------------------------------------------------------------------------\n");
    }
    
    // gives the player yet another chance to test their luck at the very end of the game
    public void box24() {
	System.out.println("\n--------------------------------------------------------------------------\n");
	System.out.println("A quick detour...\n");
	System.out.println("--------------------------------------------------------------------------\n");
	String ans = "";

	while ( (! ans.equals("yes")) && (! ans.equals("no")) ) { // to ensure a yes or no answer
		
	    System.out.println("Before we end... would you like to buy the very mysterious box 24 for $" + commafy(finalAmount) + " (all of your money)?\n\nIt contains a mystery prize... \nThe prize is either: DOUBLING your money, adding $10,000 to the amount you made, just getting your money back, getting half your money, or getting nothing (ending up with $0).\n\nWill you take that chance? (yes/no)\n" );
	    
	    try {
		ans = in.readLine();
	    }
	    catch ( IOException e ) {}

	    if ( (! ans.equals("yes")) && (! ans.equals("no")) ) { // so we can display an angry message
		System.out.println("\nYes or no only, please.\n");
	    }
	}
	
	if ( ans.equals("yes") ) { // the user has taken this chance
	    System.out.println("\nHere we go...\n");
	    System.out.println("bum");
	    waits();
	    System.out.println("bum");
	    waits();
	    System.out.println("bum");
	    waits();

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

	waits();
    }

    // guess what's in the case to win (or lose) $500!
    public void fiveHunna(int amount) {
	System.out.println("\nBefore we show you what's in the case... \nwould you like to guess what it contains?");
	String ans = "";
	
	while ( (! ans.equals("yes")) && (! ans.equals("no")) ) { // to ensure a yes or no answer
	    System.out.println("\n(yes/no)\n");
	    try {
		ans = in.readLine();
	    }
	    catch ( IOException e ) {}
	}

	if ( ans.equals("yes") ) { // the player is taking a chance!
	    int guess = 0;
	    
	    while ( guess == 0 ) { // to ensure a valid guess
		System.out.println("\nGo ahead! Take a guess!\n");
		
		try {
		    System.out.print("$");
		    guess = Integer.parseInt(in.readLine());
		}
		catch ( NumberFormatException e ) {
		    System.out.println("\nThat's not okay.");
		}
		catch ( IOException e ) {}

		if ( (! isValue(guess)) || (choVals.contains(guess)) ) { // so we can display an angry message
		    if ( guess != amount ) { // because amount is in choVals, so the if statement won't account for that
			System.out.println("\nInvalid guesses are to your own detriment, man. Trust me.");
			guess = 0;
		    }
		}
	    }

	    if ( guess == amount ) { // guess is correct
		System.out.println("\nSomehow, that is correct! \n$500 has been added to your final balance. Great job!");
		finalAmount += 500; // $500 added to user's final amount
	    }
	    else { // guess is incorrect
		System.out.println("\nSorry, but that is incorrect. \n$500 has been subtracted from your final balance. Better luck next time!");
		finalAmount -= 500; // $500 subtracted from user's final amount
	    }
	}
	
	else { // the player is a dud
	    System.out.println("\n" + caseholder.insult());
	}

	waits();
    }

    // the true end of the game
    public void end() {

	if ( finalAmount < 0 ) { // the user has won negative money
	    finalAmount = 0; // we are not that mean
	}
	
	box24(); // the final chance the user can take
	gameOver = true;
	
	System.out.println("--------------------------------------------------------------------------");
	
	System.out.println("\nCONGRATULATIONS, " + you.getName() +
			   "!!!!!\n\nYOU JUST WON $" + commafy(finalAmount) + "!!!!!!!"); // how much you just won
	
	System.out.println(caseholder.finalResponse(finalAmount)); // shows an appropriate response
	System.out.println("\nThanks for playing!\n"); // thanks for putting up with us!
	
    }
    
    // time to play!
    public void play() {

	// so new players can choose to view the rules
	you.rules();

	// choosing your case
	displayBoard();
	you.setYourCase();
	((Briefcase)cases.get(you.getYourCase())).setOpen(true); // sets your case to open (for displaying purposes)

	// round time!
	for ( int r : roundOrder ) {
	    
	    if ( ! gameOver ) { // as long as the game isn't over
		displayBoard();
		round(r);
		if ( r != -1 ) { // so a deal will not happen in the final round
		    deal();
		}
	    }
	    
	}

    }

} // end class DealOrNoDeal
