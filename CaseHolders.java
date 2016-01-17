public class CaseHolders {
    private String[] posResponse;
    private String[] negResponse;
    private String[] regResponse;
    private String[] likes; //alternate neg for likable people
    private String[] unlike; //alternate pos for lucky people (worse ones)
    private int brief;
    private String likability;

    public CaseHolders(){
	posResponse = new String[] {"Wow!","Great!","Awesome!","Groovy!","Yum!","Noice!","Amazing!","Superb!","Brilliant!","Spectacular!","Marvelous!","Magnificant!"};
	negResponse = new String[] {"Gross...","Terrible...","Oof...","Harsh...","I'm disappointed...","Awful...","Ew...","Oh no...","Disgraceful...","That's just bad...","Fail..."};
	regResponse = new String[] {"Alright.","OK.","Fine.","Kay.","Medium.","Average.","Not great.","Meh.","Eh.","Shrug."};
	likes = new String[] {"You'll do better next time","No worries","That's OK", "Nice try","You can do it!","Don't be sad","We all make mistakes","It happens","I still love you!"};
	unlike = new String[] {"kinda good, I guess","You're just lucky","Remember, there is no skill","don't get excited","people have done better","<sarcastically> well done","whatever","sure, you got one good draw","I'll make sure it doesn't happen again"};
    }
			    
    public CaseHolders(int value, String like){
	this();
	likability = like;
	brief = value;
    }

    public String response(){
	if (likability.equals("true")){
	    if (brief > 1000){
		double prob = Math.random();
		int pos = (int)(prob * likes.length);
		return likes[pos];
	    }
	    else {
		double prob = Math.random();
		int pos = (int)(prob * posResponse.length);
		return posResponse[pos];
	    }
	}
	else if (likability.equals("false")){
	    if (brief > 1000){
		double prob = Math.random();
		int pos = (int)(prob * negResponse.length);
		return negResponse[pos];
	    }
	    else {
		double prob = Math.random();
		int pos = (int)(prob * unlike.length);
		return unlike[pos];
	    }
	}
	else {	    
	    if (brief > 1000){
		double prob = Math.random();
		int pos = (int)(prob * negResponse.length);
		return negResponse[pos];
	    }
	    else {
		double prob = Math.random();
		int pos = (int)(prob * posResponse.length);
		return posResponse[pos];
	    }
	}
    }
}
		
	
    
