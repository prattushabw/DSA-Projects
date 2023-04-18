package hw2;
    /**
    * This class Represents a slide which has a title, bullets and duration
    *
    * @author Prattusha Biswas 114587992, and recitation 02
    */
    public class Slide {

        /**
        * Variables
        * <p>
        * MAX_BULLETS:
        * max number of bullets allowed on a slide is set to 5
        * title:
        * The title of the slide set to an empty string
        * bullets:
        * The number of bullets in a slide set to MAX_BULLETS
        * duration:
        * The double duration of the slide set to 0.0
        * numBullets:
        * number of bullets in a slide set to 0
        */
    public static final int MAX_BULLETS = 5;
    private String title;
    private String[] bullets;
    private double duration;
    private int numBullets;


        /**
        * This is a default Constructor used to create a new slide object
        * This object has been initialized to an empty Slide
        *
        * @custom.precondition
        * The title of the slide set to an empty string
        * The number of bullets in a slide set to MAX_BULLETS
        * The double duration of the slide set to 0.0
        * number of bullets in a slide set to 0
        */
    public Slide() {
        this.title = "";
        this.duration = 0.0;
        this.bullets = new String[MAX_BULLETS];
        this.numBullets = 0;
    }
        /**
        * This is a Constructor used to create a new slide object
        *
        * @param title
        * The title of the slide
        * @param bullets
        * The number of bullets in a slide
        * @param duration
        * The double duration of the slide
        */
    public Slide(String title, String[] bullets, double duration, int numBullets) {
        this.title = title;
        this.bullets = bullets;
        this.duration = duration;
        this.numBullets = numBullets;
    }

        /**
         * Public getter method for the title member variable.
        *
        * @return
        * the title of the slide
        */
    String getTitle() {
        return this.title;
    }

        /**
         *Public setter method for the title member variable.
         *
         * @param newTitle
         * The new title of this slide. This parameter should not be null
         * @custom.precondition
         * newTitle is not null.
         *@throws IllegalArgumentException
         * Thrown if newTitle is null.
         */
     void setTitle(String newTitle) {
        if (newTitle == null) {
            throw new IllegalArgumentException();
        } else {
            this.title = newTitle;
        }
    }
        /**
         * Public getter method for the duration member variable.
         *
         * @return
         * The duration of the Slide.
         */
    double getDuration() {
        return this.duration;
    }
        /**
         *Public setter method for the duration member variable.
         *
         * @param newDuration
         * The new duration of this slide. This parameter should be greater than 0.
         * @custom.precondition
         * newDuration is greater than 0.
         *@throws IllegalArgumentException
         * Thrown if newDuration is less than or equal to 0.
         */
    void setDuration(double newDuration) {
        if (newDuration <= 0) {
            throw new IllegalArgumentException();
        } else {
            this.duration = newDuration;
        }
    }
        /**
         * Gets the total number of bullet point in the Slide.
         *
         * @return
         * The number of bullet points in the Slide.
         */

    int getNumBullets() {
        int sum=0;
        for (int i = 0; i < bullets.length-1; i++) {
            if (bullets[i] != null) {
                sum++;
            }
        }
        return sum;
    }
        /**
         *Gets the bullet point at index i.
         *
         * @param i
         * The index to retrieve from the array. This value must be between 1 and MAX_BULLETS, inclusive.
         * @custom.precondition
         * 1 ≤ i ≤ MAX_BULLETS.
         * @return
         *The String representing the bullet point at the given index
         *@throws IllegalArgumentException
         * Thrown if i is not in the valid range.
         */
    String getBullet(int i) {
        String sentence = "";
      if (i < 0 && MAX_BULLETS <= i) {
            throw new IllegalArgumentException();
       } return bullets[i];
      /*else {
        for (i = 0; i < numBullets; i++) {
                sentence = bullets[i];
            }
        }
        return sentence;*/
    }

    void newBullets(int i){
        //System.out.println("Num Bullets is: "+numBullets);
        for(i=i; i<this.getNumBullets();i++){
            bullets[i]= bullets[i+1];
        }
        String[] newBullets=new String[this.getNumBullets()-1];
        for(int j=0; j<newBullets.length; j++){
            newBullets[j]=bullets[i];


        }
    }

        /**
         *A method that sets bullet as the i'th bullet point for bullets.
         *
         * @param bullet
         * The String to place as the ith bullet point in bullets.
         * @param i
         * The index to place bullet in the array.
         * @custom.precondition
         * 1 ≤ i ≤ MAX_BULLETS.
         * @custom.postcondition
         * The bullet point at index i has been updated to the String bullet.
         * There are no holes in the bullets array.
         *@throws IllegalArgumentException
         * Thrown if i is not in the valid range.
         */
    void setBullet(String bullet, int i){
        if(i<0 && i>=MAX_BULLETS){
            throw new IllegalArgumentException();
        }else{
            bullets[i]=bullet;
        } if(bullet==null) { //if string bullet is null
            String[] newBullet = new String[bullets.length]; //creating newBullet to have an array without holes
            for (int k = 0, j = 0; k < newBullet.length; k++) { //go through the bullets array
                if (bullets[k] != null) {// if the bullet index is not null
                    newBullet[j++] = this.bullets[k];// add the bullet to the new array to have bullets without any empty bullets
                }
            }
            this.bullets = newBullet;
        }
        //else {
        //    for (i = 0; i < this.numBullets; i++) {
         //       if(bullet != null){
                  //  bullets[i]=bullet;
             //   }else {
                //    bullets[i]=bullets[i+1];
            //    }numBullets--;
           // }
        }

        /**
         *public toString method to print title,duration, and number of bullets
         * <p>
         * sets string slide to an empty string
         * @return
         * slide that contains the information of the title, bullet info, and duration
         */
        public String toString(){
        String slide= "";
        String  line= "\n===============================================\n";
        slide += "        " +this.title+"            "+ this.getDuration()+"          "+ this.getNumBullets()+"\n" ;
        return slide;
        }


    }

