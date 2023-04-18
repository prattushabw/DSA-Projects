/**
 * This class Represents a EmptyStackException Exception
 *
 * @author Prattusha Biswas 114587992, and recitation 02
 */
public class EmptyStackException extends Exception{

    /**
     *  Should be thrown if the user attempts to pop a CargoStack
     *  that currently has no Cargo items on it.
     *
     * @param errorMessage
     * prints an error message when stack is empty
     */
    EmptyStackException(String errorMessage){
        super(errorMessage);
    }
}
