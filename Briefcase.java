/*=============================================
  class Briefcase -- The cases of $$!
  =============================================*/

public class Briefcase {

    // ~~~~~~~~~~~ INSTANCE VARIABLES ~~~~~~~~~~~

    private int number;
    private int value;
    private boolean open;

    // ~~~~~~~~~~~ CONSTRUCTOR ~~~~~~~~~~~

    public Briefcase(int x) {
	number = x;
	value = 0;
	open = false;
    }

    // ~~~~~~~~~~~ METHODS ~~~~~~~~~~~

    public void setValue(int x) {
	value = x;
    }
    
}
