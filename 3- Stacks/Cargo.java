/**
 * This class represents a container which holds the ‘cargo’ the program will place on the ship.
 *
 * @author Prattusha Biswas 114587992, and recitation 02
 */
public class Cargo {
    /**
     * Variables
     * <p>
     * It contains the following private member variables:
     * name (String), weight (double) and strength (CargoStrength).
     */
    private final String name;
    private final double weight;
    private CargoStrength strength;

    /**
     * This is a default Constructor used to create a new cargo object
     * This object has been initialized to an empty cargo
     *
     * @custom.precondition
     * The name of the cargo set to an empty string
     * The double weight of the slide set to 0.0
     */
    Cargo(){
        name="";
        weight=0.0;
    }

    /**
     * This is a Constructor used to create a new cargo object
     *
     * @param initName
     * Non-null name for the cargo item.
     * @param initWeight
     * The weight for the cargo.
     * @param initStrength
     * Either FRAGILE, MODERATE, or STURDY.
     * @custom.precondition
     * initName is not null.
     * initWeight > 0.
     * @custom.postcondition
     * This object has been initialized to a Cargo object with the given name, weight, and strength.
     * @throws IllegalArgumentException
     * If initName is null.
     * If initWeight ≤ 0.
     */
    public Cargo(String initName, double initWeight, CargoStrength initStrength){
        if(initName==null && initWeight<=0){
            throw new IllegalArgumentException();
        }
        name=initName;
        weight=initWeight;
        strength=initStrength;
    }

    /**
     * Public getter method for the name variable.
     *
     * @return
     * The name of the cargo.
     */
    String getName(){
        return name;
    }

    /**
     * Public getter method for the weight variable.
     *
     * @return
     * The weight of the cargo.
     */
    double getWeight(){
        return weight;
    }


    /**
     * Public getter method for the getStrength object.
     *
     * @return
     * The getStrength of the cargo.
     */
    CargoStrength getStrength(){
        if(strength== null){
            return null;
        }
        return strength;
    }

}
