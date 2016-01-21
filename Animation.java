public class Animation{
    private String person;
    public Animation(String p){
	person = p;
    }
    public void waits() {
	try {
	    Thread.sleep(300); // 1000 milliseconds is one second
	} catch(InterruptedException ex) {
	    Thread.currentThread().interrupt();
	}
    }
    public void dance(){
	if (person.equals("wave")){
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
}


