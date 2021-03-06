/*=============================================
  class CaseHolders -- Generates audience responses to the opened briefcase.
  =============================================*/

public class CaseHolders {

    // ~~~~~~~~~~~ INSTANCE VARIABLES ~~~~~~~~~~~

    private int likability;
    private String[] posResponse;
    private String[] negResponse;
    private String[] regResponse;
    private String[] likePos; // alternate pos for likable people
    private String[] likeNeg; // alternate neg for likable people
    private String[] unlikePos; // alternate pos for unlikable people
    private String[] unlikeNeg; // alternate neg for unlikable people
    private String[] insults; // for players who are just duds

    // ~~~~~~~~~~~ CONSTRUCTORS ~~~~~~~~~~~

    public CaseHolders() {
	posResponse = new String[] {"Wow!","Great!","Awesome!","Groovy!","Yum!","Noice!","Amazing!","Superb!",
				    "Brilliant!","Spectacular!","Marvelous!","Magnificant!","Booyah!"};
	negResponse = new String[] {"Gross...","Terrible...","Oof...","Harsh...","I'm disappointed...",
				    "Awful...","Ew...","Oh no...","Disgraceful...","That's just bad...","Fail..."};
	regResponse = new String[] {"Alright.","OK.","Fine.","Kay.","Average.","Not great.","Meh.","You're getting there.",
				    "Eh.","Shrug."};
	likePos = new String[] {"You're amazing! Keep on going!","You're on your way to success, my friend!",
				"Call me when you're famous?","That's magnificant, you beautiful human you!",
				"Brilliant! As always :)","Wow, you're my hero!","Wow!!! What a champion!"};
	likeNeg = new String[] {"You'll do better next time!","No worries!","That's OK!", "Nice try!",
				"Don't be sad!","We all make mistakes!","It happens!","I still love you!"};
	unlikePos = new String[] {"Kinda good, I guess.","You just got lucky.","Remember, there is no skill.",
				  "Don't get too excited.","People have done better.","<sarcastically> Well done, loser.","Whatever.",
				  "Sure, you got one good draw."};
	unlikeNeg = new String[] {"You're trash.","LOL, you suck.","Why don't you just give up now?","*cough* *cough* Loser.",
				  "Nice try. Not.","I totally saw that one coming.","Wow, what a failure. Like you in general, tbh."};
	insults = new String[] {"Whimp.","Fine, have it your way.","I guess. Dud.","Okay. Whatever."};
    }
			    
    public CaseHolders(int like) {
	this();
	likability = like;
    }

    // ~~~~~~~~~~~ METHODS ~~~~~~~~~~~

    // generates the response
    public String response(int brief) {
	
	if ( likability == 1 ) { // if the player is likable, CaseHolders is rooting for them!
	    if ( brief < 50000 ) { // they got lucky!
		double prob = Math.random();
		int pos = (int)(prob * likePos.length);
		return likePos[pos];
	    }
	    else { // they did not get lucky :(
		double prob = Math.random();
		int pos = (int)(prob * likeNeg.length);
		return likeNeg[pos];
	    }
	}
	
	else if ( likability == -1 ) { // if the player is unlikable, Caseholders is not rooting for them...
	    if ( brief < 50000 ) { // they got lucky!
		double prob = Math.random();
		int pos = (int)(prob * unlikePos.length);
		return unlikePos[pos];
	    }
	    else { // they did not get lucky :(
		double prob = Math.random();
		int pos = (int)(prob * unlikeNeg.length);
		return unlikeNeg[pos];
	    }
	}
	
	else { // if the player is neutral, whatever
	    if ( brief > 50000 ) { // they did not get lucky :(
		double prob = Math.random();
		int pos = (int)(prob * negResponse.length);
		return negResponse[pos];
	    }
	    else if (brief > 500) { // they did okay (no preference either way)
		double prob = Math.random();
		int pos = (int)(prob * regResponse.length);
		return regResponse[pos];
	    }
	    else { // they got lucky!
		double prob = Math.random();
		int pos = (int)(prob * posResponse.length);
		return posResponse[pos];
	    }
	}
	
    }
    
    // for insulting the player
    public String insult() {
	double prob = Math.random();
	int pos = (int)(prob * insults.length);
	return insults[pos];
    }

    // the response at the very end
    public String finalResponse(int val) {

	if ( val >= 1000000 ) { // the user has won $1,000,000! (or more?)
	    return "Oh, my! That's absolutely amazing! Tell me your secrets?";
	}
	else if ( val > 75000 ) { // the user did really well
	    return "You did amazingly. You should, like, enter the lottery or something!";
	}
	else if ( val > 1000 ) { // the user did pretty well
	    return "Wow, you did pretty well! You should be very proud!";
	}
	else if ( val > 100 ) { // the user did decently
	    return "You did alright! Maybe play again?";
	}
	else if ( val > 10 ) { // the user did pretty badly
	    return "Wow, that's pretty bad. You probably shouldn't enter the lottery...";
	}
	else if ( val > 0 ) { // ouch
	    return "Ouch. Better luck next time.";
	}
	else { // the user has won $0 (or less?)
	    return "That's just sad...";
	}
	
    }
    
} // end class CaseHolders
