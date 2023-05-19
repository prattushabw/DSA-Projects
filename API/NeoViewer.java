import java.io.IOException;
import java.util.Scanner;

/**
 * The NeoViewer class is the main class for the NEO Viewer program.
 *  The program uses the NEO database class to store and manage NEO data.
 *
 * @author Prattusha Biswas 114587992, and recitation 02
 */


public class NeoViewer {
    /**
     * The main method that starts the NEO Viewer program.
     * It creates a new NEO database object and displays the menu options for the user to choose from.
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        boolean run = true;
        NeoDatabase database = new NeoDatabase();
        ReferenceIDComparator id = new ReferenceIDComparator();
        DiameterComparator diameter = new DiameterComparator();
        ApproachDateComparator approachDate = new ApproachDateComparator();
        MissDistanceComparator missDistance = new MissDistanceComparator();
        
        System.out.println("Welcome to NEO Viewer!");
        System.out.println("Option Menu:\n" +
                "    A) Add a page to the database\n" +
                "    S) Sort the database \n" +
                "    P) Print the database as a table.\n" +
                "    Q) Quit");

        while (run) {
            System.out.print("Select a menu option: ");
            String choice = stdin.nextLine();
            choice = choice.toUpperCase();

            switch (choice) {
                case "A":
                    System.out.print("Enter the page to load: ");
                    int num = stdin.nextInt();
                    stdin.nextLine();
                    try {
                        String link = database.buildQueryURL(num);
                        database.addAll(link);

                        System.out.println("Page loaded successfully!");
                    } catch (IOException e) {
                        System.out.println("IO Exception");
                    }
                    break;
                case "S":
                    System.out.println("  R) Sort by referenceID\n" +
                            "  D) Sort by diameter\n" +
                            "  A) Sort by approach date\n" +
                            "  M) Sort by miss distance");

                    System.out.print("Select a menu option: ");
                    String choice2 = stdin.nextLine();
                    choice2 = choice2.toUpperCase();
                    switch (choice2) {
                        case "R":
                            database.sort(id);
                            System.out.println("Table sorted on referenceID.");
                            break;
                        case "D":
                            database.sort(diameter);
                            System.out.println("Table sorted on diameter.");
                            break;
                        case "A":
                            database.sort(approachDate);
                            System.out.println("Table sorted on approach date.");
                            break;
                        case "M":
                            database.sort(missDistance);
                            System.out.println("Table sorted on miss distance.");
                            break;
                    }
                    break;
                case "P":
                    database.printTable();
                    break;
                case "Q":
                    System.out.println("Program terminating normally...");
                    System.exit(0);
                    break;
            }
        }
    }
}