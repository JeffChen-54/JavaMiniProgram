/**
 * <h1>RandomGenerator</h1>
 * The RandomGenerator class implements an application that put the users into the Rides randomly.
 * <p>
 * <b>Note:</b> RandomGenerator class is useful when you want to put users in Rides with
 * different or same probability.
 *
 * @author  Jeffery Chen
 * @version 1.0
 * @since   2021-10-18
 */

public class RandomGenerator {
    /**
     * This method is used to put users in Rides with same probability.
     *
     * @param rides This is the array of rides that users can be put in.
     * @return Ride This returns the Ride that the user will be put in.
     */
    public static Ride selectRide(Ride[] rides){
        return rides[(int) (Math.random()*rides.length)];
    }
    /**
     * This method is used to put users in Rides with different probability.
     *
     * @param rides This is the array of rides that users can be put in.
     * @param probabilities This is the array pf probabilities that the users will be put in each Ride.
     * @return  Ride This returns the Ride that the user will be put in.
     */
    public static Ride selectRide(Ride[] rides, double[] probabilities){
        double random = Math.random();
        double pro = 0;
        for (int i=0; i<probabilities.length;i++){
            pro += probabilities[i];
            if(pro>random) return rides[i];
        }
        return rides[rides.length-1];
    }
}
