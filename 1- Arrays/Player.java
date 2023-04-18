/**
 * This class Represents a player which has a name, num of hits, and num of errors
 *
 * @author Prattusha Biswas 114587992
 */
public class Player {

    String name;
    int numHits;
    int numErrors;
    /**
     * This is a Constructor used to create a new Person object
     *
     * @param name
     * The name of the player
     * @param numHits
     * The number of hits
     * @param numErrors
     * The number of errors
     */
    Player (){
        this.name="";
        this.numHits= 0;
        this.numErrors= 0;
    }

    Player (String name, int numHits, int numErrors){
        this.name=this.getName();
        this.numHits= this.getNumHits();
        this.numErrors= this.getNumErrors();
    }
    /**
     * This is a get name method
     *
     * @return name
     * return name

     */
    //getters
   String getName(){ return name; }
    /**
     * This is a get numHits method
     *
     * @return numHits
     * return num of Hits

     */
   int getNumHits(){
        return numHits;
   }
    /**
     * This is a get numErrors method
     *
     * @return numErrors
     * return num of errors

     */
   int getNumErrors(){
        return numErrors;
   }
    /**
     * This is a get clone method
     *
     * @param name
     * @param numHits
     * @paramnumErrors
     * @return newPlayer
     * return newPlayer

     */

    public Object clone(){
        Player newPlayer = new Player(this.name, this.numHits, this.numErrors);

      //  Player playerTwo = newPlayer;
        return newPlayer;
    }
    /**
     * This is a get equal method
     *
     * @param name
     * @param numHits
     * @paramnumErrors
     * @return false
     * return false

     */
    //equal method
    public boolean equal(Object obj){
        if(obj instanceof Player){

            Player p = (Player) obj;
            return this.name.equals(p.name) && this.numHits==p.numHits && this.numErrors==p.numErrors;
        }
        return false;
    }
    /**
     * This is a setter name method
     *
     * @return name
     * return num of name

     */
    //setters
    void setName(String name){
        this.name=name;
    }
    /**
     * This is a get numHits method
     *
     * @return numHits
     * return num of numHits

     */
    void setNumHits(int numHits){
        if (numHits < 0){
            throw new IllegalArgumentException();
        } else{
            this.numHits=numHits;
        }
    }
    /**
     * This is a get numErrrors method
     *
     * @return numErrrors
     * return num of numErrrors

     */
    void setNumErrors(int numErrors){
        if (numErrors < 0){
            throw new IllegalArgumentException();
        } else {
            this.numErrors= numErrors;
        }
    }
    /**
     * This is a get toString method
     *
     * @return toString
     * return num of toString

     */
    //toString
    public String toString(){
        return this.name + " has " + this.numHits + " number of hits and " + this.numErrors + " number of errors.";
    }

    }

