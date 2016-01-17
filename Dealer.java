/*=============================================
  class Dealer - makes the deals
  =============================================*/

import java.util.ArrayList;

public class Dealer {

    // ~~~~~~~~~~~ INSTANCE VARIABLES ~~~~~~~~~~~
    private double luck;
    private int totVals;
    private int totBrief;
    private int leftVals;
    private int choVals;


     // ~~~~~~~~~~~ CONSTRUCTOR ~~~~~~~~~~~

    public Dealer (double l) {
	luck = l;
    }

    
    // ~~~~~~~~~~~~~~ METHODS ~~~~~~~~~~~~~~~~~

    // sets the values of totVals, leftVals, and choVals
    public void setVals(ArrayList c, int[] v) {
	choVals = 0;
	totVals = 0;
    	totBrief = v.length - c.size(); // number of briefcasees that are still closed
	
	for ( int i = 0 ; i < c.size() ; i++ ) {
	    choVals += (int)c.get(i); // sum of all of the open briefcases
	}
	for ( int i = 0 ; i < v.length ; i++ ) {
	    totVals += v[i]; // sum of all of the values (ever)
	}
	leftVals = totVals - choVals;
	
        // difference between values that were opened and all the values, leaving you with only the values of briefcases that weren't opened
    }


    // returns the deal delt by the dealer
    public int deal(ArrayList chosenValues, int[] values) {
	setVals(chosenValues,values); // sets values properly
	int mean = leftVals / totBrief; // average of totVals in briefcases not opened
	return (int)(luck * mean); // lucky, you get a better deal than the average. likable, you get a worse deal (luck is lower)
    }
    
} // end class Dealer
