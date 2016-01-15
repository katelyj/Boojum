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

    // accessor for number
    public int getNumber() {
	return number;
    }

    // mutator for value
    public void setValue(int x) {
	value = x;
    }
    public int getValue() {
	return value;
    }

    public void setOpen(boolean b){
	open = b;
    }
    
    // returns if the briefcase is open or not
    public boolean isOpen() {
    	return open;
    }
    
}
