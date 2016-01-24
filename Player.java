/*=============================================
  class Player - the user
  =============================================*/

import java.io.*;
import java.util.*;

public class Player implements Lucky {

    // ~~~~~~~~~~~ INSTANCE VARIABLES ~~~~~~~~~~~
  
    private InputStreamReader isr;
    private BufferedReader in;
    
    private String name;
    private int yourCase;
    private double luck;
    private int likability;
    
    private final String rules = "\nTHE RULES:\n\nIn this game, there are 24 briefcases filled with money, ranging from $1 to $1,000,000. Without knowing the amounts inside, you will choose a briefcase to be your own. This briefcase will stick with you throughout the game, and at the end, you will win whatever is inside of it.\n\nEach round, you will open a designated number of briefcases, revealing the amounts of money they have inside. This will give you a clue as to what is NOT in your own briefcase. And at the end of each round, you will be presented a certain amount of money: a deal. You can either make the deal and take this money, ending the game and never knowing how much was in your case, or reject the deal and keep opening more briefcases.\n\nIf you get to the end without making a deal, you will win whatever amount is in your own briefcase.\n\nNow, press enter to begin...\n";


    // ~~~~~~~~~~~ CONSTRUCTOR ~~~~~~~~~~~

    public Player() {
	isr = new InputStreamReader( System.in );
	in = new BufferedReader( isr );
	
	luck = 1;
	likability = 0;
	yourCase = -1;
	name = "";

	inputName();
	inputMode();
    }


    // ~~~~~~~~~~~~~~ METHODS ~~~~~~~~~~~~~~~~~

    // gets and returns name from the user
    public void inputName() {
	while ( name.equals("") ) { // to ensure non-empty names
	    System.out.println("Name? \n");
	    try {
		name = in.readLine();
	    }
	    catch ( IOException e ) {}
	}
    }
    
    // sets mode
    public void inputMode() {
	int n = 0;
	while ( n == 0 ) { // to ensure that the user inputs a valid mode before continuing
     
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

	    if ( n != 1 && n != 2 && n != 3 ) { // the user has not inputted a valid mode
		System.out.println("\nUm, no.");
		n = 0;
	    }
     
	}
 
	if ( n == 1 ) {
	    System.out.println("\nCongrats, you are a regular person!\n");
	}
	else if ( n == 2 ) {
	    setLuck(0.9);
	    setLikability(1);
	    System.out.println("\nCongrats, you are a likable person!\n");
	}
	else {
	    setLuck(1.1);
	    setLikability(-1);
	    System.out.println("\nCongrats, you are a lucky person!\n");
	}
    }

    // for new players who don't know what's up
    public void rules() {
	System.out.println("Before we begin, do you know how to play? (y/n)\n");
	String b = "";
	
	while ( (! b.equals("y")) && (! b.equals("n")) ) { // to ensure a yes or no answer, only
	    try {
		b = in.readLine();
	    }
	    catch ( IOException e ) {}
	    
	    if ( (! b.equals("y")) && (! b.equals("n")) ) { // so we can print an angry message!
		System.out.println("\nPlease choose yes or no!\n");
	    }
	}
	
	if ( b.equals("n") ) { // the user is clueless
	    System.out.println(rules);
	    try { in.readLine(); }
	    catch ( IOException e ) {}
	}
	else { // the user is a smart cookie
	    System.out.println("\nGreat! Now let's begin...");
	}
    }

    // picking your case
    public int pickCase() {
	int c = -1;
	while ( c < 0 || c > 23 ) { // to ensure valid numbers
	    System.out.println("Pick a case to open...\n");
	    try {
		c = Integer.parseInt(in.readLine());
	    }
	    catch ( NumberFormatException e ) {
		System.out.println("\nThat doesn't look like a number.");
	    }
	    catch ( IOException e ) {}
	    
	    if ( c < 0 || c > 23 ) { // so we can print an angry message!
		System.out.println("\nPick a valid case, please.\n");
	    }
	}
	return c;
    }

    // mutator for yourCase
    public void setYourCase() {
	System.out.println("Choose a briefcase to be your own.\nBe wary. This case will stick with you throughout your time here...\n");
	
	while ( yourCase < 0 || yourCase > 23 ) { // to ensure valid numbers
	    try {
		yourCase = Integer.parseInt(in.readLine());
	    }
	    catch ( NumberFormatException e ) {
		System.out.println("\nThat doesn't look like a number.");
	    }
	    catch ( IOException e ) {}
	    
	    if ( yourCase < 0 || yourCase > 23 ) { // so we can print an angry message!
		System.out.println("\nPick a valid case, please.\n");
	    }
	}
	
    }

    // allows the user to choose to make the deal or not
    public String dealOrNoDeal() {
	String n = "";
	System.out.println("\ndeal, or no deal?\n(all lower case please)\n");
 
	try {
	    n = in.readLine();
	}
	catch ( IOException e ) {}

	if ( n.equals("deal") ) { // the user has accepted the deal
	    return n;
	}
	else if ( n.equals("no deal") ) { // the user has not accepted the deal
	    System.out.println("\nAnd the game continues on!\n");
	    return n;
	}
	else { // the user is dumb
	    System.out.println("\nUhh... let's try that again, shall we?");
	    return dealOrNoDeal();
	}
    }
       
    // accessor for case
    public int getYourCase() {
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
    public int getLikability() {
	return likability;
    }

    // mutator for luck
    public void setLuck(double l) {
	luck = l;
    }
    
    // mutator for likability
    public void setLikability(int l) {
	likability = l;
    }

} // end class Player
