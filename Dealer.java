/*=============================================
  class Dealer - makes the deals
  =============================================*/

import java.util.ArrayList;
import java.util.Random;

public class Dealer extends Values implements Lucky {

    // ~~~~~~~~~~~ INSTANCE VARIABLES ~~~~~~~~~~~

    private int totBrief;
    private String luck;
    private double sum;
    private double[] valsSquared;


     // ~~~~~~~~~~~ CONSTRUCTOR ~~~~~~~~~~~

    public Dealer (String l) {
	luck = l;
	valsSquared = new double[fValues.length];

	// creates valsSquared -- all the values, but squared
	for ( int v = 0 ; v < valsSquared.length ; v++ ) {
	    valsSquared[v] = Math.pow(fValues[v],2); // corresponding number, but squared
	}
	
	// we do not set all the other variables here because they will change with each deal!
    }

    
    // ~~~~~~~~~~~~~~ METHODS ~~~~~~~~~~~~~~~~~

    // sets the values of totBrief and sum(of the squares)
    public void setVals(ArrayList c) {

	sum = 0;
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
	
	double mean = sum / totBrief;
	mean = Math.sqrt(mean); // to calculate the geometric mean
	Random rand = new Random();
	double  scale = 0;
	if (getLuck().equals("true")){
	    scale = ((double)(rand.nextDouble() * .4))+ .9; //.9 to 1.3
	}
	else if (getLuck().equals("neither")){
	    scale = ((double)(rand.nextDouble() * .5))+ .75;//.75 to 1.25
	}
	else {
	    scale = ((double)(rand.nextDouble() * .5))+ .65;//.65 to 1.15
	}
	return (int)(mean * scale);
	// lucky, you get a better deal than the average. likable, you get a worse deal (luck is lower)
	
    }

    // accessor for luck
    public String getLuck() {
	return luck;
    }
    
} // end class Dealer
