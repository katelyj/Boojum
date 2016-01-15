
import java.util.ArrayList;

public class Dealer {

    private double likability;
    private double luck;
    private int totNum;
    private int totVals;
    private int leftVals;
    private int choVals;


    public Dealer (ArrayList chosenValues, int[] values, double luc, double like){
    luck = luc;
    likability = like;
    totVals = values.length - chosenValues.size(); //number of briefcasees that are still closed
    for (int i = 0; i < chosenValues.size(); i++){
        choVals += (int)chosenValues.get(i);//sum of all of the open briefcases
    }
    for (int i = 0; i < values.length; i++){
        totVals += values[i];//sum of all of the values (ever)
    }
    leftVals = totVals - choVals;//difference between value that were opened and all the values, leaving you with only the values of briefcases that weren't opened
    }


    public int deal() {
    int mean = leftVals / totVals; // average of totVals in briefcases not opened
    return (int)luck * mean; // lucky, you get a better deal than the average. likable, you get a worse deal (luck is lower)
    }
}
