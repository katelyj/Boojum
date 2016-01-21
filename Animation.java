public class Animation{
    private String person;
    public Animation(String p){
	person = p;
    }
    public void dance(){
	if (person.equals("wave")){
	    System.out.println("\n\n
__::*****************::__\n
/ \033[34m O \033[37m       \033[34m O \033[37m \n
|    \\_______/   | \n
\\                   / \n
\\____________________/ \n
      |           | \n
      |           | ");
	}
    }
}
