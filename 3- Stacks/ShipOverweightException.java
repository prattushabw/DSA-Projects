/**
 * This class Represents a ShipOverweightException Exception
 *
 * @author Prattusha Biswas 114587992, and recitation 02
 */
public class ShipOverweightException extends Exception{

    /**
     *  Should be thrown if the user attempts to push a Cargo object
     *  on to any stack of the CargoShip which would put it over its
     *  maximum weight limit.
     *
     * @param errorMessage
     * prints an error message when user cargo is over weight.
     */
    public ShipOverweightException(String errorMessage){
        super(errorMessage);
    }
}
