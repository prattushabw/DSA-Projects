package hw2;

/**
 * This class Represents a SlideList which implements a double linked-list data structure
 *
 * @author Prattusha Biswas 114587992, and recitation 02
 */
public class SlideList {

    /**
     * Objects
     *
     * @head
     * start of the list
     * @tail
     * end of the list
     * @cursor
     * allow a user to traverse the list
     */
    private SlideListNode head;
    private SlideListNode tail;
    private SlideListNode cursor;
    private int size;
    int sum=0;

    /**
     *Default constructor which initializes this object to an empty list of Slides.
     *
     * @custom.postcondition
     * This SlideList has been initialized with head, tail, and cursor all set to null.
     *
     */
    SlideList(){
        head=null;
        tail=null;
        cursor= null;
    }

    /**
     *Returns the total number of Slides in the slideshow.
     *
     * @return
     * The count of all Slides in the slideshow.
     */
    int size(){
        SlideListNode temp = head;
        int size = 0;
        while (temp != null) {
            size++;
            temp=temp.getNext();
        }
        return size;
    }

    /**
     *Returns the total duration of the slideshow.
     *
     * @return
     * The sum of all individual Slide durations in the slideshow.
     */
    double duration(){
        double dur=0.0;
        SlideListNode sumDuration =head; //pointer to head
        while(sumDuration != null){//while pointer is not null
            dur+=sumDuration.getData().getDuration();//duration is added to gets sum of duration
            sumDuration = sumDuration.getNext();//setting the pointer to the next node to add all the duration in the list
        }
       return dur;
    }

    /**
     *Returns the total number of bullet points in the slideshow.
     *
     * @return
     * The sum of all bullet points of all individual Slides in the slideshow.
     */
    int numBullets(){
        int bull=0;
        SlideListNode sumBullets = head;
        while(sumBullets!=null){
            bull+=sumBullets.getData().getNumBullets();
            sumBullets = sumBullets.getNext();
        }
        return bull;
    }

    /**
     *Gets the reference to the Slide wrapped by the SlideListNode currently referenced by cursor
     *
     * @custom.precondition
     * if cursor is null, return null.
     * @return
     * The reference of the Slide wrapped by the SlideListNode currently referenced by cursor.
     */
    Slide getCursorSlide(){
        if(cursor==null){
            return null;
        }
        return cursor.getData();
    }

    /**
     * Returns the cursor to the start of the list.
     *
     * @custom.postcondition
     * If head is not null, the cursor now references the first SlideListNode in this list.
     * If head is null, the cursor is set to null as well
     */
    void resetCursorToHead(){
        if(this.head==null){
            cursor=null;
        }else if(this.head != null){
            cursor=this.head;
        }
    }

    /**
     * Moves the cursor to select the next SlideListNode in the list.
     *
     * @throws EndOfListException
     * Thrown if cursor is at the tail of the list.
     */
    void cursorForward() throws EndOfListException {
        if(cursor ==null){
            throw new EndOfListException("cursor is null");
        } else if(cursor.getNext()==null){
            throw new EndOfListException("you reached the end of the list.");
        }else{
           this.cursor= cursor.getNext();
        }
    }

    /**
     * Moves the cursor to select the previous SlideListNode in the list.
     *
     * @throws EndOfListException
     * Thrown if cursor is at the head of the list.
     */
    void cursorBackward()throws EndOfListException{
       if(cursor ==null){
           throw new EndOfListException("cursor is null");
       } else if(cursor.getPrev()==null){
            throw new EndOfListException("you reached start of the list.");
        } else{
            this.cursor=cursor.getPrev();
        }
    }

    /**
     *Inserts the indicated Slide before the cursor.
     *
     * @param newSlide
     * The Slide object to be wrapped and inserted into the list before the cursor.
     *
     * @custom.precondition
     * newSlide is not null.
     * @custom.postcondition
     * newSlide has been wrapped in a new SlideListNode object
     * If cursor was previously not null, the newly created SlideListNode has been inserted into the list before the cursor.
     * If cursor was previously null, the newly created SlideListNode has been set as the new head of the list
     * The cursor now references the newly created SlideListNode.
     *@throws IllegalArgumentException
     * Thrown if newSlide is null.
     */
    void insertBeforeCursor(Slide newSlide){
        if(newSlide == null) {
            throw new IllegalArgumentException();
        }
        SlideListNode newPrevSlide = new SlideListNode(newSlide);
        if(this.cursor==null){
            this.cursor=newPrevSlide;
        }else if (this.cursor.getPrev() == null) {
            this.head = newPrevSlide;
        } else {
            newPrevSlide.setPrev(cursor.getPrev());
            cursor.getPrev().setNext(newPrevSlide);
        }
        newPrevSlide.setNext(this.cursor);
        this.cursor.setPrev(newPrevSlide);
        this.cursor = newPrevSlide;
    }


    //cursor is empty, head and tail, head but not tail, tail but not head, or somewhere in the middle


    /**
     *Inserts the indicated Slide after the tail of the list.
     *
     * @param newSlide
     * The Slide object to be wrapped and inserted into the list after the tail of the list.
     *
     * @custom.precondition
     * newSlide is not null.
     * @custom.postcondition
     * newSlide has been wrapped in a new SlideListNode object
     * If tail was previously not null, the newly created SlideListNode has been inserted into the list after the tail.
     * If tail was previously null, the newly created SlideListNode has been set as the new head of the list
     * The tail now references the newly created SlideListNode.
     *@throws IllegalArgumentException
     * Thrown if newSlide is null.
     */
    void appendToTail(Slide newSlide){
        if(newSlide == null) {
            throw new IllegalArgumentException();
        }
        SlideListNode newTail = new SlideListNode(newSlide);

        if(this.head == null && this.tail==null){
            this.head= newTail;
            this.tail= newTail;
            this.cursor=newTail;
        }else{
            tail.setNext(newTail);
            newTail.setPrev(this.tail);
            tail=newTail;
        }}


    /**
     *Removes the SlideListNode referenced by cursor and returns the Slide inside.
     *
     * @custom.precondition
     * cursor is not null.
     * @custom.postcondition
     * The SlideListNode referenced by cursor has been removed from the list.
     * All other SlideListNodes in the list exist in the same order as before.
     * The cursor now references the previous SlideListNode
     * @return
     * The reference to the Slide contained within the SlideListNode that was removed
     *@throws IllegalArgumentException
     * Thrown if cursor is null.
     */
    Slide removeCursor() throws EndOfListException {
          if(this.cursor==null) {
              throw new EndOfListException("cursor is empty");
          } else if (cursor.getPrev() == null) {
              head = cursor.getNext();
              cursor = head;
            } else if (cursor.getNext() == null) {
             tail = cursor.getPrev();
             cursor = tail;
          } else{
              cursor.getPrev().setNext(cursor.getNext());
              cursor.getNext().setPrev(cursor.getPrev());

              cursor=cursor.getNext();
          }
          size--;
          return cursor.getData();
        }


    /*void String() {
        SlideListNode cursor = new SlideListNode();
        cursor=head;
        while (cursor.getNext() != null) {
            System.out.print(cursor.toString()) ;
            cursor=cursor.getNext();
        }

    }*/
    /**
     *public toString method to print the total number of slide,duration, and number of bullets
     * <p>
     * sets string total to an empty string
     * @return
     * the total number of slides, duration, and bullets in the entire slideshow
     */
        public String toString(){
        String  line= "===============================================\n";
        String str="";
        int i=1;
        SlideListNode temp=cursor;
        this.resetCursorToHead();
        SlideListNode temp2=new SlideListNode(getCursorSlide());
        while(cursor != null ){
            if(temp.equals(cursor)){
                str+="-> ";
            }
            str+=i;
            str+=cursor.getData();
            this.cursor= cursor.getNext();
            i++;

        }
        cursor=temp;
        str += line+"Total: " + this.size()+" slide(s), "+ String.format("%.1f", this.duration()) + " minute(s), "+ this.numBullets()+" bullet(s)"+"\n"+line;
        return str;
    }

    public String toStringTo (){

        String result="";
        int num=1;

        String  line= "===============================================\n";
       // if(debug){
         //   System.out.println("Num Bullets is: "+cursor.getData().getNumBullets());
        //}
        for(int i=0; i<cursor.getData().getNumBullets();i++) {
            result += "  "+num+ "        "+cursor.getData().getBullet(i)+"\n";
            num++;
        }
        return line+"" +this.cursor.getData().getTitle()+"\n"+line +result+line;
    }


}
