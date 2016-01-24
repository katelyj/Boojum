/*=============================================
  class Dealer - makes the deals
  =============================================*/

import java.util.ArrayList;

public class Dealer extends Values implements Lucky {

    // ~~~~~~~~~~~ INSTANCE VARIABLES ~~~~~~~~~~~
    
    private double luck;
    private int totBrief;
    private int leftVals;
    private int SumChoVals;


     // ~~~~~~~~~~~ CONSTRUCTOR ~~~~~~~~~~~

    public Dealer (double l) {
	super();
	luck = l;
	// we do not set all the other variables here because they will change with each deal!
    }

    
    // ~~~~~~~~~~~~~~ METHODS ~~~~~~~~~~~~~~~~~

    // sets the values of totVals, leftVals, and sumChoVals
    public void setVals(ArrayList c) {
	int sumChoVals = 0;
	int totVals = 0;
    	totBrief = fValues.length - c.size(); // number of briefcasees that are still closed
	
	for ( int i = 0 ; i < c.size() ; i++ ) {
	    sumChoVals += (int)c.get(i); // sum of all of the open briefcases
	}
	
	for ( int i = 0 ; i < fValues.length ; i++ ) {
	    totVals += fValues[i]; // sum of all of the values (ever)
	}
	
	leftVals = totVals - sumChoVals;
	
        // difference between values that were opened and all the values, leaving you with only the values of briefcases that weren't opened
    }

    // returns the deal delt by the dealer
    public int deal(ArrayList chosenValues, int[] values) {
	
	setVals(chosenValues); // sets values properly
	int mean = leftVals / totBrief; // average of fValues in briefcases not opened
	
	return (int)(luck * mean); // lucky, you get a better deal than the average. likable, you get a worse deal (luck is lower)
	
    }
    public double getLuck(){
	return luck;
    }
    
} // end class Dealer
