/**
 * The Zork class is the main class for the Zork game.
 * Runs the game, and prompts the user to either play or edit the game.
 *
 * @author Prattusha Biswas 114587992, and recitation 02
 */

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.zip.DataFormatException;

public class Zork {
    /**
     * The main method which runs the Zork game.
     * @param args an array of command line arguments
     */
    public static Scanner stdin = new Scanner(System.in);
    public static void main(String[] args) {

        boolean run = false;
        System.out.println("Hello and Welcome to Zork!");
        System.out.print("\nPlease enter the file name: ");
        String filename = stdin.nextLine();

        try {
            StoryTree.readTree(filename);
            System.out.println("\nLoading game from file...");
            System.out.println("File Loaded!");
            run = true;
        } catch (DataFormatException e) {
            System.out.println("File not accepted.\n");
            System.out.println("Creating new tree...\n");
            System.out.println("Tree created!\n");
        } catch (FileNotFoundException e) {
            System.out.println("File is not Found");
        }


        while (run) {
            System.out.print("\nWould you like to edit (E), play (P) or quit (Q)? ");
            String editPlayQuit = stdin.nextLine();
            editPlayQuit = editPlayQuit.toUpperCase();

            switch (editPlayQuit) {
                case "E":
                    editTree(StoryTree.tree);
                    break;
                case "P":
                    try {
                        playTree(StoryTree.tree);
                    } catch (NodeNotPresentException e) {
                        System.out.println("Node not found");
                    }
                    break;
                case "Q":
                    try {
                        System.out.println("\nGame being saved to SampleStory.txt...");
                        StoryTree.saveTree(filename, StoryTree.tree);
                        System.out.println("Program terminating normally.");
                    } catch (IOException e) {
                        System.out.println("I/O error occurred");
                    }
                    System.exit(0);
            }
        }
        try {
            System.out.println("Game being saved to " + filename + "...");
            StoryTree.saveTree(filename, StoryTree.tree);
            System.out.println("Save successful!");
            System.out.println("\nProgram terminating normally...");
        } catch (IOException e) {
            System.out.println("I/O error occurred");
        }
    }

    /**
     * This method allows the user to play a StoryTree game by interacting with the command line.
     * @param tree a StoryTree object representing the story game to be played.
     * @throws NodeNotPresentException if the selected child does not exist.
     */
    public static void playTree(StoryTree tree) throws NodeNotPresentException {
        String choice;
        String[][] root = tree.getOptions();
        System.out.println(root[0][1]);

        tree.selectChild("1");

        while (tree.getGameState().equals(GameState.GAME_NOT_OVER)) {
            String[][] options = tree.getOptions();
            System.out.println(tree.getCursorMessage());

            if(tree.getGameState().equals(GameState.GAME_OVER_WIN)){
                break;
            }else if(tree.getGameState().equals(GameState.GAME_OVER_LOSE)) {
                break;
            }
            for (int i = 0; i < options.length; i++) {
                    System.out.println((i + 1) + ") " + options[i][1]);
            }

            System.out.print("\nPlease make a choice: ");
            choice = stdin.nextLine();
            if (choice.equalsIgnoreCase("C"))
                System.out.printf("\nProbability of a win at this point: %3.1f%%%n\n",tree.winProbability()*100.0);
            else
             tree.selectChild(choice);
        }

        System.out.println(tree.getCursorMessage());
        System.out.println("Thanks for playing.\n");
    }


    /**
     * Allows the user to edit a StoryTree object by adding, deleting, and modifying nodes in the tree.
     * @param tree the StoryTree object to be edited.
     * @throws RuntimeException if a NodeNotPresentException is thrown.
     */
    public static void editTree(StoryTree tree) {
        System.out.print("\nZork Editor:");
        boolean run2 = false;
        System.out.print("\n    V: View the cursor's position, option and message.\n" +
                "    S: Select a child of this cursor (options are 1, 2, and 3).\n" +
                "    O: Set the option of the cursor.\n" +
                "    M: Set the message of the cursor.\n" +
                "    A: Add a child StoryNode to the cursor.\n" +
                "    D: Delete one of the cursor's children and all its descendants.\n" +
                "    R: Move the cursor to the root of the tree.\n" +
                "    P: Return the cursor to the parent node.\n" +
                "    Q: Quit editing and return to main menu.");

        while (!run2) {
            System.out.print("\n\nPlease select an option: ");
            String select = stdin.nextLine();
            select = select.toUpperCase();

            switch (select) {
                case "V":
                    System.out.println("Position: " + tree.cursor.getPosition());
                    System.out.println("Option: " + tree.cursor.getOption());
                    System.out.println("Message: " + tree.cursor.getMessage());
                    break;
                case "S":
                    /*if(tree.cursor.countChildren()==3)
                        System.out.println("no child");*/
                    String child;

                    System.out.print("Please select a child: [");
                    if (tree.cursor.getLeftChild() != null) {
                        System.out.print("1");
                    }
                    if (tree.cursor.getMiddleChild() != null) {
                        System.out.print(",2");
                    }
                    if (tree.cursor.getRightChild() != null) {
                        System.out.print(",3");
                    }
                    System.out.print("]");

                    child = stdin.nextLine();
                   // System.out.println(cursor);

                    try {
                        tree.selectChild(child);
                    } catch (NodeNotPresentException e) {
                        System.out.printf("Error. No child %s for the current node.",child);
                    }
                    break;
                case "O":
                    System.out.print("Please enter a new option: ");
                    String newOption = stdin.nextLine();
                    //String inputnoun = newOption.split(" ")[0];

                    tree.setCursorOption(newOption);
                    System.out.println("\nOption set.");
                    break;
                case "M":
                    System.out.print("Please enter a new Message: ");
                    String newMessage = stdin.nextLine();

                    tree.setCursorMessage(newMessage);
                    System.out.println("\nMessage set.");
                    break;
                case "A":
                    try {
                        System.out.print("Enter an option: ");
                        String newNodeO = stdin.nextLine();

                        System.out.print("Enter a message: ");
                        String newNodeM = stdin.nextLine();

                        tree.addChild(newNodeO, newNodeM);
                        System.out.println("\nChild added.");
                    } catch (TreeFullException e) {
                        System.out.println("Error");
                    }
                    break;
                case "D":
                    System.out.print("Please select a child: [");
                    if (tree.cursor.getLeftChild() != null) {
                        System.out.print("1");
                    }
                    if (tree.cursor.getMiddleChild() != null) {
                        System.out.print(",2");
                    }
                    if (tree.cursor.getRightChild() != null) {
                        System.out.print(",3");
                    }
                    System.out.print("] ");
                    String remove = stdin.nextLine();

                    try {
                        tree.removeChild(remove);
                        System.out.println("\nSubtree deleted.");
                    } catch (NodeNotPresentException e) {
                        System.out.printf("Error. No child %s for the current node.",remove);
                    }
                    break;
                case "R":
                    try {
                        tree.resetCursor();
                        System.out.print("Cursor moved to root.");
                        tree.selectChild("1");
                    } catch (NodeNotPresentException e) {
                        System.out.println("Error. No child 1 for the current node.");
                    }
                    break;
                case "P":
                    tree.returnToParent();
                    System.out.print("Returned to parent");
                    break;
                case "Q":
                    return;
            }
        }
    }
}