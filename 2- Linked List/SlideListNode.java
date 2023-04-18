package hw2;
/**
 * This class Represents a SlideListNode that wraps a Slide object to allow it to be inserted into a doubly linked-list data structure.
 *
 * @author Prattusha Biswas 114587992, and recitation 02
 */

public class SlideListNode {

    /**
     * Objects
     *
     * @data
     * Slide object reference
     * @prev
     * SlideListNode references serving as ‘link’ to the previous
     * @next
     * SlideListNode references serving as ‘link’ to the next
     */
    private Slide data;
    private SlideListNode next;
    private SlideListNode prev;

    /**
     *Default constructor.
     *
     * @param initialData
     * The data to be wrapped by this SlideListNode.
     *
     * @custom.precondition
     * initData is not null.
     * @custom.postcondition
     * This SlideListNode has been initialized to wrap the indicated Slide,
     * prev and next have been set to null.
     *@throws IllegalArgumentException
     * Thrown if initData is null.
     */
    SlideListNode(Slide initialData) {
    if(initialData ==null) {
        throw new IllegalArgumentException();
    }else{
        data=initialData;
        next=null;
        prev=null;
        }
    }

    /**
     *Gets the reference to the data member variable of the list node.
     *
     * @return
     * The reference of the data member variable.
     */
    Slide getData(){
        return data;
    }

    /**
     *Gets the reference to the next member variable of the list node.
     *
     * @custom.precondition
     * if next is null, return null.
     * @return
     * The reference of the next member variable.
     */
    SlideListNode getNext(){
        if(next==null){
            return null;
        }
        return next;
    }

    /**
     *Gets the reference to the prev member variable of the list node.
     *
     * @custom.precondition
     * if prev is null, return null.
     * @return
     * The reference of the prev member variable.
     */
    SlideListNode getPrev(){
        if(prev==null){
            return null;
        }
        return prev;
    }

    /**
     *Updates the data member variable with a reference to a new Slide.
     *
     * @param newData
     * Reference to a new Slide object to update the data member variable
     *
     * @custom.precondition
     * newData is not null.
     *@throws IllegalArgumentException
     * Thrown if newData is null.
     */
    void setData(Slide newData){
        if(newData == null){
            throw new IllegalArgumentException();
        }else{
            data=newData;
        }
    }

    /**
     *Updates the next member variable with a new SlideListNode reference.
     *
     * @param newNext
     * Reference to a new SlideListNode object to update the next member variable.
     */
    void setNext(SlideListNode newNext){
        next=newNext;
    }
    /**
     *Updates the prev member variable with a new SlideListNode reference.
     *
     * @param newPrev
     * Reference to a new SlideListNode object to update the prev member variable
     */
    void setPrev(SlideListNode newPrev){
        prev=newPrev;
    }


}
