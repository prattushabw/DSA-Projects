/**
 * This class is an FullQueueException class
 * @author Prattusha Biswas 114587992, and recitation 02
 */
public class FullQueueException extends Exception{

    /**
     *
     *
     * @param errorMessage
     * prints an error message when queue is full.
     */
    FullQueueException(String errorMessage){
        super(errorMessage);
    }
}
