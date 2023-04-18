/**
 * fully-documented class named StorageManager
 * This class will allow the user to interact with the storage database by listing the storage boxes occupied, allowing
 * the user to add or remove storage boxes, searching for a box by id, and listing all the boxes for a user.
 *
 * @author Prattusha Biswas 114587992, and recitation 02
 */
import java.io.*;
import java.util.*;

public class StorageManager {
    static Scanner stdin = new Scanner (System.in);
    /**
     * Hash table for <code>Storage</code> objects
     */
    private static StorageTable newStorageTable;
    /**
     * The main method of the `StorageManager` class. This method creates a new
     * `StorageTable` object and initializes the user interface.
     *
     * <p>P - Print all storage boxes</p>
     * <p>A - Insert into storage box</p>
     * <p>R - Remove contents from a storage box</p>
     * <p>C - Select all boxes owned by a particular client</p>
     * <p>F - Find a box by ID and display its owner and contents</p>
     * <p>Q - Quit and save workspace</p>
     * <p>X - Quit and delete workspace</p>
     *
     * @param args
     * Array of command-line arguments that are passed to the main function
     */
    public static void main(String[] args) {


        newStorageTable = new StorageTable();
        Storage newStorage;

        System.out.println("Hello, and welcome to Rocky Stream Storage Manager");
        boolean check = new File("storage.obj").exists();
        if(check){
            try {
                FileInputStream file = new FileInputStream("storage.obj");
                ObjectInputStream inStream = new ObjectInputStream(file);
                newStorageTable = (StorageTable) inStream.readObject();
                inStream.close();

            } catch (FileNotFoundException e) {
                System.out.println("\n'storage.obj' file could not be found."+
                        "Using empty StorageTable...");
            } catch (IOException e) {
               // e.printStackTrace();
                System.out.println("");
            } catch (ClassNotFoundException e) {
                System.out.println("");
            }
        }

        boolean run=false;
        int id;
        String client;
        String contents;
        System.out.print("\nP - Print all storage boxes\n" +
                "A - Insert into storage box\n" +
                "R - Remove contents from a storage box\n" +
                "C - Select all boxes owned by a particular client\n" +
                "F - Find a box by ID and display its owner and contents\n" +
                "Q - Quit and save workspace\n" +
                "X - Quit and delete workspace ");
        while (!run) {
            System.out.print("\n\nPlease select an option: ");

            String choice = stdin.nextLine();
            choice = choice.toUpperCase();

            switch (choice) {
                case "P":
                    System.out.println(newStorageTable);
                    break;
                case "A":
                    while (true) {
                        System.out.print("Please enter id: ");
                        if (stdin.hasNextInt()) {
                            id = stdin.nextInt();
                            break;
                        }
                            stdin.nextLine();
                    }
                    stdin.nextLine();
                    System.out.print("Please enter client: ");
                    client = stdin.nextLine();

                    System.out.print("Please Enter Contents: ");
                    contents= stdin.nextLine();

                    newStorage = new Storage(id,client,contents);
                    newStorageTable.putStorage(id, newStorage);
                    System.out.printf("\nStorage %d set",id);
                    break;
                case "R":
                    while (true) {
                        System.out.print("Please enter id: ");
                        if (stdin.hasNextInt()) {
                            id = stdin.nextInt();
                            break;
                        }
                        stdin.nextLine();
                    }
                    stdin.nextLine();

                    newStorageTable.remove(id);
                    System.out.printf("Box %d is now removed.\n",id);
                    break;
                case "C":
                    System.out.print("Please enter the name of the client: ");
                    client = stdin.nextLine();
                    boolean found = false;

                    StringBuilder clientFound = new StringBuilder(String.format("%-10s%-38s%s%n%s", "Box#", "Contents", "Owner", "-".repeat(64)));

                    // Loop through each entry in the newStorageTable map using an entry set.
                    for(Map.Entry<Integer, Storage> entry : newStorageTable.entrySet() ){

                        // Get the Storage object for the current key in the entry set.
                        newStorage = newStorageTable.getStorage(entry.getKey());

                        // If the client for the current Storage object matches the given client,
                        // format the Storage object's information into a string and append it to the
                        // StringBuilder object. Set found to true to indicate that at least one
                        // matching Storage object was found.
                        if (newStorage.getClient().equals(client)) {
                            clientFound.append(String.format("%n%-10d%-38s%s", newStorage.getId(), newStorage.getContents(), newStorage.getClient()));
                            found=true;
                        }
                    }
                    if (found)
                        System.out.println("\n"+clientFound);
                    else
                        System.out.printf("Could not find any storage box owned by %s%n",client);
                    break;
                case "F":
                    while (true) {
                        System.out.print("Please enter id: ");
                        if (stdin.hasNextInt()) {
                            id = stdin.nextInt();
                            break;
                        }
                        stdin.nextLine();
                    }
                    stdin.nextLine();

                    newStorage = newStorageTable.getStorage(id);
                    if (newStorage==null) {
                        System.out.println("Storage box could not be found.");
                    } else {
                        System.out.println("Box " + newStorage.getId());
                        System.out.println("Contents: " + newStorage.getContents());
                        System.out.println("Owner: " + newStorage.getClient());
                    }
                    break;
                case "Q":
                    System.out.println("Storage Manager is quitting, current storage is saved for next session");
                    try {
                        FileOutputStream file = new FileOutputStream("storage.obj");
                        ObjectOutputStream outStream = new ObjectOutputStream(file);
                        outStream.writeObject(newStorageTable);
                        outStream.close();
                    } catch (FileNotFoundException e) {
                        System.out.println("File could not be found");
                    } catch (IOException e) {
                        System.out.println("An IO error has occurred.");
                    }
                    break;
                case "X":
                    System.out.println("Storage Manager is quitting, all data is being erased.");
                    File file = new File("storage.obj");
                    file.deleteOnExit();
                    System.exit(0);
                    break;

            }
        }
    }
}