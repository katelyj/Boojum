/*=============================================
  abstract class Values -- For classes that use values!
  subclasses of Values: Dealer, DealOrNoDeal
  =============================================*/

import java.util.ArrayList;

public abstract class Values {

    // ~~~~~~~~~~~ INSTANCE VARIABLES ~~~~~~~~~~~
    
    protected ArrayList choVals;
    
    protected int[] fValues = {1,5,10,15,25,50,75,
			       100,200,500,750,1000,5000,10000,25000,50000,75000,
			       100000,200000,300000,400000,500000,750000,1000000};

    
    // ~~~~~~~~~~~~~~ METHODS ~~~~~~~~~~~~~~~~~

    // tells if a value is in an array
    public boolean isIn(int i, int[] a) {
        for ( int x : a ) {
	    if ( i == x ) {
		return true;
	    }
	}
	return false;
    }
    
} // end class Values
