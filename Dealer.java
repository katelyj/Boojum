/*=============================================
  class Dealer - makes the deals
  =============================================*/

import java.util.ArrayList;

public class Dealer extends Values implements Lucky {

    // ~~~~~~~~~~~ INSTANCE VARIABLES ~~~~~~~~~~~

    private int totBrief;
    private double luck;
    private double sum;
    private double[] valsSquared;


     // ~~~~~~~~~~~ CONSTRUCTOR ~~~~~~~~~~~

    public Dealer (double l) {
	luck = l;
	valsSquared = new double[fValues.length];

	// creates valsSquared -- all the values, but squared
	for ( int v = 0 ; v < valsSquared.length ; v++ ) {
	    valsSquared[v] = Math.pow(fValues[v],2); // corresponding number, but squared
	}
	
	// we do not set all the other variables here because they will change with each deal!
    }

    
    // ~~~~~~~~~~~~~~ METHODS ~~~~~~~~~~~~~~~~~

    // sets the values of totVals, leftVals, and sumChoVals
    public void setVals(ArrayList c) {
	
    	totBrief = fValues.length - c.size(); // number of briefcasees that are still closed
	
	for ( int i = 0 ; i < fValues.length ; i++ ) {
	    
	    if ( ! c.contains(fValues[i]) ) { // the value is not in c (chosen values)
		sum += valsSquared[i]; // adds the square of the value
	    }
	    
	}

    }

    // returns the deal delt by the dealer (the geometric mean, multiplied by likability)
    public int deal(ArrayList chosenValues) {
	
	setVals(chosenValues); // sets values properly
	
	double mean = sum / totBrief; // average of fValues in briefcases not opened
	mean = Math.sqrt(mean); // to calculate the geometric average
	
	return (int)(getLuck() * mean);
	// lucky, you get a better deal than the average. likable, you get a worse deal (luck is lower)
	
    }

    // accessor for luck
    public double getLuck() {
	return luck;
    }
    
} // end class Dealer
