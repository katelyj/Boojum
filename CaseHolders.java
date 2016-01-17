/*=============================================
  class CaseHolders -- Generates audience responses to the opened briefcase.
  =============================================*/

public class CaseHolders {

    // ~~~~~~~~~~~ INSTANCE VARIABLES ~~~~~~~~~~~
    
    private String[] posResponse;
    private String[] negResponse;
    private String[] regResponse;
    private String[] likePos; // alternate pos for likable people
    private String[] likeNeg; // alternate neg for likable people
    private String[] unlikePos; // alternate pos for unlikable people
    private String[] unlikeNeg; // alternate neg for unlikable people
    private int likability;

    // ~~~~~~~~~~~ CONSTRUCTORS ~~~~~~~~~~~

    public CaseHolders() {
	posResponse = new String[] {"Wow!","Great!","Awesome!","Groovy!","Yum!","Noice!","Amazing!","Superb!",
				    "Brilliant!","Spectacular!","Marvelous!","Magnificant!","Booyah!"};
	negResponse = new String[] {"Gross...","Terrible...","Oof...","Harsh...","I'm disappointed...",
				    "Awful...","Ew...","Oh no...","Disgraceful...","That's just bad...","Fail..."};
	regResponse = new String[] {"Alright.","OK.","Fine.","Kay.","Average.","Not great.","Meh.","You're getting there.",
				    "Eh.","Shrug."};
	likePos = new String[] {"You're amazing! Keep on going!","You're on your way to success, my friend!",
				"Call me when you're famous?","That's magnificant, you beautiful human you.",
				"Brilliant! As always :)","Wow, you're my hero.","Wow!!! What a champion."};
	likeNeg = new String[] {"You'll do better next time","No worries","That's OK", "Nice try","You can do it!",
			      "Don't be sad","We all make mistakes","It happens","I still love you!"};
	unlikePos = new String[] {"Kinda good, I guess","You're just lucky","Remember, there is no skill",
			       "don't get excited","People have done better","<sarcastically> Well done","Whatever",
			       "Sure, you got one good draw","I'll make sure it doesn't happen again"};
	unlikeNeg = new String[] {"You're trash.","LOL, you suck.","Why don't you just give up now?","Haha, loser.",
				  "Nice try, loser."};
    }
			    
    public CaseHolders(int like) {
	this();
	likability = like;
    }

    // ~~~~~~~~~~~ METHODS ~~~~~~~~~~~

    // generates the response
    public String response(int brief) {
	
	if ( likability == 1 ) { // if the player is likable
	    if ( brief < 50000 ) {
		double prob = Math.random();
		int pos = (int)(prob * likePos.length);
		return likePos[pos];
	    }
	    else {
		double prob = Math.random();
		int pos = (int)(prob * likeNeg.length);
		return likeNeg[pos];
	    }
	}
	
	else if ( likability == -1 ) { // if the player is unlikable
	    if ( brief < 50000 ) {
		double prob = Math.random();
		int pos = (int)(prob * unlikePos.length);
		return unlikePos[pos];
	    }
	    else {
		double prob = Math.random();
		int pos = (int)(prob * unlikeNeg.length);
		return unlikeNeg[pos];
	    }
	}
	
	else { // if the player is neutral
	    if ( brief > 50000 ) {
		double prob = Math.random();
		int pos = (int)(prob * negResponse.length);
		return negResponse[pos];
	    }
	    else if (brief > 500) {
		double prob = Math.random();
		int pos = (int)(prob * regResponse.length);
		return regResponse[pos];
	    }
	    else {
		double prob = Math.random();
		int pos = (int)(prob * posResponse.length);
		return posResponse[pos];
	    }
	}
	
    }
    
} // end class CaseHolders
