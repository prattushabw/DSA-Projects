/**
 * This class Represents a FullStackException Exception
 *
 * @author Prattusha Biswas 114587992, and recitation 02
 */
public class FullStackException extends Exception{

    /**
     *  Should be thrown if the user attempts to push a Cargo object
     *  onto a stack which is currently at the maximum height.
     *
     * @param errorMessage
     * prints an error message when stack is full.
     */
    FullStackException(String errorMessage){
        super(errorMessage);
    }
}
