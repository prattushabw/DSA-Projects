/**
 * This class Represents a CargoStrengthException Exception
 *
 * @author Prattusha Biswas 114587992, and recitation 02
 */
public class CargoStrengthException extends Exception{

    /**
     *  thrown if the user attempts to push a Cargo object onto another
     *  Cargo object that violates the CargoStrength rules.
     *
     * @param errorMessage
     * prints an error message when user violates the CargoStrength rules
     */
    CargoStrengthException(String errorMessage){
        super(errorMessage);
    }
}
