/*=============================================
  class Animation -- A cute lil dog for all your aesthetic pleasures!
  =============================================*/

public class Animation {

    // ~~~~~~~~~~~ INSTANCE VARIABLES ~~~~~~~~~~~
    
    private String person;

    
    // ~~~~~~~~~~~ CONSTRUCTOR ~~~~~~~~~~~
    
    public Animation(String p) {
	person = p;
    }

    
    // ~~~~~~~~~~~ METHODS ~~~~~~~~~~~
    
    public void waits() {
	try {
	    Thread.sleep(300); // waits 300 milliseconds
	}
	catch ( InterruptedException ex ) {
	    Thread.currentThread().interrupt();
	}
    }
    
    public void dance() {
	if ( person.equals("wave") ) {
	    System.out.println("\033c");
	    System.out.println("          (oo)\n  +========\\/\n / || %%% ||\n*  ||-----||\n   \"\"     \"\"\n");
	    waits();
	    System.out.println("\033c");
	    System.out.println("          (oo)\n  +========\\/\n/ || %%% ||\n*  ||-----||\n   \"\"     \"\"\n");
	    waits();
	    System.out.println("\033c");
	    System.out.println("          (oo)\n _+========\\/\n   || %%% ||\n*  ||-----||\n   \"\"     \"\"\n");
	    waits();
	    System.out.println("\033c");
	    System.out.println("          (oo)\n \\+========\\/\n   || %%% ||\n*  ||-----||\n   \"\"     \"\"\n");
	    
	}
    }
    
} // end class Animation


