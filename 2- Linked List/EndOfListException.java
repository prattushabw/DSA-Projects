package hw2;
/**
 * This class Represents a EndOfListException Exception
 *
 * @author Prattusha Biswas 114587992, and recitation 02
 */

public class EndOfListException extends Exception {

    /**
     * Exception class which indicates that the user attempted to access a SlideListNode that does not exist
     *
     * @param errorMessage
     * prints an error message when user reaches start or end of the linked-list
     */
        public EndOfListException(String errorMessage){
            super(errorMessage);
        }
    }


