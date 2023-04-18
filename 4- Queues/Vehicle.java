/**
 * This class represents the vehicle and creates a new car with a serialID and the time it arrived when called upon
 *
 * @author Prattusha Biswas 114587992, and recitation 02
 */
public class Vehicle {

    private static int serialCounter = 0;
    private int serialId;
    private int timeArrived;

    /**
     * Constructs a new Vehicle instance with the given arrival time.
     * Automatically increments the serialCounter and sets the serialId to its new value.
     *
     * @param initTimeArrived The time the vehicle arrived at the intersection.
     * @throws IllegalArgumentException if initTimeArrived is less than or equal to 0.
     */
    public Vehicle(int initTimeArrived){
        if(initTimeArrived<=0){
            throw new IllegalArgumentException();
        }else{
            //serialCounter++;
            serialId=serialCounter++;
            timeArrived=initTimeArrived;
        }
    }

    /**
     * Returns the unique serial ID of this vehicle.
     *
     * @return The serial ID of this vehicle.
     */
    public int getSerialId() {
        return serialId;
    }

    /**
     * Returns the arrival time of this vehicle.
     *
     * @return The time this vehicle arrived at the intersection.
     */
    public int getTimeArrived() {
        return timeArrived;
    }
}

