package hw2;

import java.util.*;
/**
 * This class Represents a PresentationManager which is where user can manipulate the slides, bullets, and duration
 *
 * @author Prattusha Biswas 114587992, and recitation 02
 */
public class PresentationManager {

    public static void main(String[] args) throws EndOfListException {

        /**
         * main method which will display welcome to PresentationManager and display menu options:
         * F) Move cursor forward
         * B) Move cursor backward
         * D) Display cursor slide
         * E) Edit cursor slide
         * P) Print presentation summary
         *A) Append new slide to tail
         *I) Insert new slide before cursor
         *R) Remove slide at cursor
         *H) Reset cursor to head
         * Q) Quit
         *
         * when user inputs a letter in the menu, then the following cod will run
         */

        System.out.println("Welcome to PresentationManager!\n" + "\n" +
                "    Please select an option:\n"+
                "    F) Move cursor forward\n" +
                "    B) Move cursor backward\n" +
                "    D) Display cursor slide\n" +
                "    E) Edit cursor slide\n" +
                "    P) Print presentation summary\n" +
                "    A) Append new slide to tail\n" +
                "    I) Insert new slide before cursor\n" +
                "    R) Remove slide at cursor\n" +
                "    H) Reset cursor to head\n" +
                "    Q) Quit");
        // Slide slideShow= new Slide();

        Slide slideBullet = new Slide();
        SlideList slideLink = new SlideList();

        boolean run = true;
        String input = null;
        int count = 0;
        String ans = "";
        int bulletNum = 0;

        boolean hasError=false;

        while (run) {
            System.out.print("\nPlease select a menu option: ");
            Scanner stdin = new Scanner(System.in);
            input = stdin.nextLine();
            input = input.toUpperCase();

            switch (input) {
                case "A":
                    slideBullet = new Slide();
                    bulletNum=0;
                    System.out.print("Enter the slide title: ");
                    String title = stdin.nextLine();
                    slideBullet.setTitle(title);

                    System.out.print("Enter the slide duration: ");
                    double duration = stdin.nextDouble();
                    stdin.nextLine();
                    slideBullet.setDuration(duration);

                    try {
                        do {
                            // int num=1;
                            System.out.print("Bullet" + (bulletNum+1) + ": ");
                            String bullet = "";
                            while (bullet.equals("")) {
                                bullet = stdin.nextLine();
                            }

                            slideBullet.setBullet(bullet, bulletNum);
                            bulletNum++;

                            if(bulletNum>=5){
                                throw new IllegalArgumentException();
                            }
                            System.out.print("Add another bullet point? (y/n) ");
                            ans = stdin.nextLine();

                            if (!ans.equals("y") && !ans.equals("n")) {
                                hasError = true;
                                break;
                            }

                        } while (ans.equals("y"));

                        if (hasError) {
                            System.out.println("Incorrect input");
                        } else {
                            slideLink.appendToTail(slideBullet);
                            System.out.println("Slide \"" + slideBullet.getTitle() + "\" added to presentation.");

                        }
                    } catch(IllegalArgumentException e){

                            System.out.println("No more bullets allowed. Slide is full.");
                            continue;

                    }
                break;
            case "P":
                if(slideLink.getCursorSlide()==null){
                    System.out.print("==============================================\n" +
                            "Total: 0 slide(s), 0.0 minute(s), 0 bullet(s)");
                }else {
                int i = 1;
                System.out.println("Slideshow Summary:\n" +
                        "==============================================\n" +
                        " Slide    Title         Duration   Bullets\n" +
                        "----------------------------------------------\n" +
                            slideLink);
                }
                break;
            case "F":
                try {
                    slideLink.cursorForward();
                    System.out.println("The cursor moved forward to slide \"" + slideLink.getCursorSlide().getTitle() + "\".");
                }catch (EndOfListException e){
                    System.out.println("End of list cannot move forward");
            }
                break;
            case "I":
                slideBullet = new Slide();
                bulletNum=0;
                System.out.print("Enter the slide title: ");
                 title = stdin.nextLine();
                slideBullet.setTitle(title);

                System.out.print("Enter the slide duration: ");
                 duration = stdin.nextDouble();
                stdin.nextLine();
                slideBullet.setDuration(duration);

                try {
                    do {
                        // int num=1;
                        System.out.print("Bullet" + (bulletNum+1) + ": ");
                        String bullet = "";
                        while (bullet.equals("")) {
                            bullet = stdin.nextLine();
                        }

                        slideBullet.setBullet(bullet, bulletNum);
                        bulletNum++;

                        if(bulletNum>=5){
                            throw new IllegalArgumentException();
                        }
                        System.out.print("Add another bullet point? (y/n) ");
                        ans = stdin.nextLine();

                        if (!ans.equals("y") && !ans.equals("n")) {
                            hasError = true;
                            break;
                        }

                    } while (ans.equals("y"));


                if (hasError) {
                    System.out.println("Incorrect input");
                } else {
                        System.out.println("Slide \"" + slideBullet.getTitle() + "\" added to presentation.");
                    }} catch(IllegalArgumentException e){
                        System.out.println("No more bullets allowed. Slide is full.");
                    }
                slideLink.insertBeforeCursor(slideBullet);

                break;
            case "B":
                try {
                    slideLink.cursorBackward();
                    System.out.println("The cursor moved backward to slide \"" + slideLink.getCursorSlide().getTitle() + "\".");
                }catch (EndOfListException e){
                    System.out.println("you reached start of the list.");
                }
                break;
            case "D":
                if(slideLink.getCursorSlide()==null){
                    System.out.print("Empty slideshow");
                }else {
                    System.out.println(slideLink.toStringTo());
                }
                break;
            case "E":
                //edit cursor slide
                if(slideLink.getCursorSlide()==null){
                    System.out.print("Empty slideshow");
                }else {
                System.out.print("Edit title, duration, or bullets? (t/d/b): ");
                input= stdin.nextLine();

                if(input.equals("t")){
                    System.out.print("What would you like to change title to: ");
                     title = stdin.nextLine();
                     slideBullet.setTitle(title);
                }else if (input.equals("d")){
                System.out.print("What would you like to change duration to: ");
                duration = stdin.nextDouble();
                slideBullet.setDuration(duration);
                stdin.nextLine();
                }else if (input.equals("b")){
                    System.out.print("Bullet index: ");
                    int index= stdin.nextInt();
                    stdin.nextLine();

                    System.out.print("Delete or edit? (d/e): ");
                    String edit = stdin.nextLine();
                    if(edit.equals("d")){
                        slideBullet.newBullets(index-1);
                        System.out.println("Bullet "+ index+" has been deleted.");
                    } else if (edit.equals("e")){
                        System.out.print("Bullet "+ index+": ");
                        String newBullet= stdin.nextLine();
                        slideBullet.setBullet(newBullet, index-1);
                        System.out.println("Bullets are updated ");
                    }
            }}
                break;
            case "R":
                // Remove slide at cursor
                if(slideLink.getCursorSlide()==null){
                    System.out.print("Empty slideshow");
                }else {
                    Slide removed = slideLink.getCursorSlide();
                    slideLink.removeCursor();
                    System.out.print("Slide " + removed.getTitle() + " has been removed.");
                    slideLink.resetCursorToHead();
                }
                break;
            case "H":
                if(slideLink.getCursorSlide()==null){
                    System.out.print("Empty slideshow");
                }else {
                    slideLink.resetCursorToHead();
                    System.out.println("cursor is set to head");
                }
                break;
            case "Q":
                System.out.println("\nProgram terminating normally...");
                System.exit(0);
        }
    }
}

}

