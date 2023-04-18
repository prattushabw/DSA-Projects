/**
 * This class is an EmptyQueueException class
 * @author Prattusha Biswas 114587992, and recitation 02
 */
public class EmptyQueueException extends Exception{

    /**
     *
     *
     * @param errorMessage
     * prints an error message when queue is empty
     */
    EmptyQueueException(String errorMessage){
        super(errorMessage);
    }
}
