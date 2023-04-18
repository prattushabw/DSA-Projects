import java.util.Scanner;

/**
 * This class is the main method where the user can test varies cases, the user can set the max weight, max height, and number of stacks
 *
 * @author Prattusha Biswas 114587992, and recitation 02
 */
public class ShipLoader{

    public static void main (String[] args) throws EmptyStackException, CargoStrengthException {
        /**
         * main method which will display welcome to ShipLeader and display menu options:
         * C) Create new cargo
         * L) Load cargo from dock
         * U) Unload cargo from ship
         * M) Move cargo between stacks
         * K) Clear dock
         * P) Print ship stacks
         * S) Search for cargo
         * Q) Quit
         *
         * when user inputs a letter in the menu, then the following code will run
         */

        System.out.print("       Cargo Ship Parameters\n" +
                "-----------------------------------\n");
        System.out.print("Number of stacks: ");
        Scanner stdin = new Scanner(System.in);
        int num = stdin.nextInt();
        stdin.nextLine();

        System.out.print("Maximum height of stacks: ");
        int height =  stdin.nextInt();
        //if height instance of String throw exception
        stdin.nextLine();

        System.out.print("Maximum total cargo weight: ");
        double maxWeight =  stdin.nextDouble();
        stdin.nextLine();

        CargoShip newShip = new CargoShip(num,height,maxWeight);

        System.out.print("\nCargo ship created.\n" +
                "Pulling ship in to dock...\n" +
                "Cargo ship ready to be loaded.\n\n");


        CargoStack newStack = new CargoStack();
        Cargo newCargo= new Cargo();
        System.out.println("Please select an option:\n" +
                "  C) Create new cargo\n" +
                "  L) Load cargo from dock\n" +
                "  U) Unload cargo from ship\n" +
                "  M) Move cargo between stacks\n" +
                "  K) Clear dock\n" +
                "  P) Print ship stacks\n" +
                "  S) Search for cargo\n" +
                "  Q) Quit");

        boolean run = true;
        String input = null;
        int index;
        boolean hasError = false;
        String name;
        double weight;



        while (run) {
            System.out.print("\n\nPlease select a menu option: ");
            input = stdin.nextLine();
            input = input.toUpperCase();

            switch(input) {
                case "C":
                    CargoStrength strength = null;

                    try {

                            System.out.print("\nEnter the name of the cargo: ");
                            name = stdin.nextLine();


                            System.out.print("Enter the weight of the cargo: ");
                            weight = stdin.nextDouble();
                            stdin.nextLine();

                            System.out.print("Enter the container strength (F/M/S): ");
                            String str = stdin.nextLine();
                            str = str.toUpperCase();
                            if (str.equals("F")) {
                                strength = CargoStrength.FRAGILE;
                            } else if (str.equals("M")) {
                                strength = CargoStrength.MODERATE;
                            } else if (str.equals("S")) {
                                strength = CargoStrength.STURDY;
                            }

                                newCargo = new Cargo(name, weight, strength);
                                newStack.push(newCargo);

                                System.out.printf("\n\nCargo '%s' pushed onto the dock.\n", name);


                        for (int i = 0; i < newShip.stacks.length; i++) {
                            if (newShip.stacks[i].size() == 0) {
                                System.out.print("  \nStack " + (i + 1) + ": ");
                            } else {
                                System.out.print("  \nStack " + (i + 1) + ": ");
                                newShip.stacks[i].printStack();
                            }
                        }

                        System.out.print("  \nDock: ");
                        newStack.printStack();

                        System.out.printf("  \nTotal Weight = %.0f / %.0f", newShip.shipWeight(),maxWeight);

                    } catch (EmptyStackException e) {
                        // e.printStackTrace();
                        System.out.print("Operation failed! Cargo stack is empty.");

                    } catch (CargoStrengthException e) {
                        //e.printStackTrace();
                        System.out.print("Operation failed! Cargo at top of stack cannot support weight.");
                    }
                    break;
                case "L":
                    //Load cargo from dock <stackIndex>
                    try {
                        System.out.print("Select the load destination stack index: ");
                        index = stdin.nextInt();
                        stdin.nextLine();


                        newShip.pushCargo(newStack.pop(), index);


                        System.out.printf("\nCargo '%s' moved from dock to stack %d.\n", newShip.stacks[index-1].peek().getName(), index);

                        for (int i = 0; i < newShip.stacks.length; i++) {
                            if (newShip.stacks[i].size() == 0) {
                                System.out.print("  \nStack " + (i + 1) + ": ");
                            } else {
                                System.out.print("  \nStack " + (i + 1) + ": ");
                                newShip.stacks[i].printStack();
                            }
                        }
                        System.out.print("  \nDock: ");
                        newStack.printStack();


                        System.out.printf("  \nTotal Weight = %.0f / %.0f", newShip.shipWeight(),maxWeight);

                    } catch (EmptyStackException e) {
                      //  e.printStackTrace();
                        System.out.println("Operation failed! Cargo stack is empty.");
                    } catch (ShipOverweightException e) {
                        // e.printStackTrace();
                        newStack.push(newCargo);
                        System.out.println("Operation failed! Cargo would put ship overweight.");
                    } catch (CargoStrengthException e) {
                        //e.printStackTrace();
                        newStack.push(newCargo);
                        System.out.println("Operation failed! Cargo at top of stack cannot support weight.");

                    } catch (FullStackException e) {
                        // e.printStackTrace();
                        newStack.push(newCargo);
                        System.out.println("Operation failed! Cargo stack is at maximum height.");
                    }
                    break;
                case "U":
                    //Unload cargo from ship <srcStackIndex>
                    try {
                        System.out.print("Select the unload source stack index: ");
                        index = stdin.nextInt();
                        stdin.nextLine();

                        // code that unloads the cargo from ship back to dock
                        newStack.push(newShip.popCargo(index));

                        System.out.printf("Cargo '%s' moved from stack %d to dock.", newStack.peek().getName(),index);

                        for (int i = 0; i < newShip.stacks.length; i++) {
                            if (newShip.stacks[i].size() == 0) {
                                System.out.print("  \nStack " + (i + 1) + ": ");
                            } else {
                                System.out.print("  \nStack " + (i + 1) + ": ");
                                newShip.stacks[i].printStack();
                            }
                        }

                        System.out.print("  \nDock: ");
                        newStack.printStack();

                        System.out.printf("  \nTotal Weight = %.0f / %.0f", newShip.shipWeight(),maxWeight);

                    } catch (EmptyStackException e) {
                        // e.printStackTrace();
                        System.out.println("Operation failed! Cargo stack is empty.");
                    } catch (CargoStrengthException e) {
                        //e.printStackTrace();
                        System.out.println("Operation failed! Cargo at top of stack cannot support weight.");
                    }

                    break;
                case "M":
                    //Move cargo between stacks <srcStackIndex> <dstStackIndex>
                    try {
                        System.out.print("Source stack index: ");
                        index = stdin.nextInt();
                        stdin.nextLine();

                        System.out.print("Destination stack index: 2: ");
                        int index2 = stdin.nextInt();
                        stdin.nextLine();


                       // System.out.print(newShip.stacks[index-1].peek().getName());
                        newShip.pushCargo(newShip.popCargo(index), index2);

                        System.out.printf("Cargo '%s' moved from stack %d to stack %d.", newShip.stacks[index2-1].peek().getName(),index,index2);

                        for (int i = 0; i < newShip.stacks.length; i++) {
                            if (newShip.stacks[i].size() == 0) {
                                System.out.print("  \nStack " + (i + 1) + ": ");
                            } else {
                                System.out.print("  \nStack " + (i + 1) + ": ");
                                newShip.stacks[i].printStack();
                            }
                        }

                        System.out.print("  \nDock: ");
                        newStack.printStack();

                        System.out.printf("  \nTotal Weight = %.0f / %.0f", newShip.shipWeight(),maxWeight);


                    } catch (EmptyStackException e) {
                         // e.printStackTrace();
                        System.out.print("Operation failed! Cargo stack is empty.");
                    } catch (ShipOverweightException e) {
                        //  e.printStackTrace();
                        System.out.print("Operation failed! Cargo would put ship overweight.");
                    } catch (CargoStrengthException e) {
                        // e.printStackTrace();
                        System.out.print("Operation failed! Cargo at top of stack cannot support weight.");
                    } catch (FullStackException e) {
                        //  e.printStackTrace();
                        System.out.print("Operation failed! Cargo stack is at maximum height.");
                    }
                    break;
                case "K":
                    //Clear dock
                    try{
                        newStack.clearStack();
                        System.out.print("Dock cleared.\n");
                        for (int i = 0; i < newShip.stacks.length; i++) {
                            if (newShip.stacks[i].size() == 0) {
                                System.out.print("  \nStack " + (i + 1) + ": ");
                            } else {
                                System.out.print("  \nStack " + (i + 1) + ": ");
                                newShip.stacks[i].printStack();
                            }
                        }

                        System.out.print("  \nDock: ");
                        newStack.printStack();

                        System.out.printf("  \nTotal Weight = %.0f / %.0f", newShip.shipWeight(),maxWeight);

                    }catch(EmptyStackException e){
                        //e.printStackTrace();
                        System.out.println("Operation failed! Cargo stack is empty.");
                    } catch (CargoStrengthException e) {
                        System.out.print("Operation failed! Cargo at top of stack cannot support weight.");
                    }
                    break;
                case "P":
                    // Print ship stacks
                    try{
                        for(int i=0;i<newShip.stacks.length;i++) {
                            System.out.print("\nStack "+(i+1) +": ");
                            newShip.stacks[i].printStack();
                        }

                        System.out.print("  \nDock: ");
                        newStack.printStack();

                        System.out.printf("  \nTotal Weight = %.0f / %.0f", newShip.shipWeight(),maxWeight);

                    } catch (EmptyStackException e) {
                        System.out.println("Operation failed! Cargo stack is empty.");
                    } catch (CargoStrengthException e) {
                        System.out.print("Operation failed! Cargo at top of stack cannot support weight.");
                    }
                    break;
                case "S":
                    //Search for cargo <name>
                    try {
                       // newStack.clearStack();
                        System.out.print("Enter the name of the cargo: ");
                         name = stdin.nextLine();

                        newShip.findAndPrint(name);
                    } catch (EmptyStackException e) {
                        //  e.printStackTrace();
                        System.out.println("Operation failed! Cargo stack is empty.");
                    } catch (CargoStrengthException e) {
                        //  e.printStackTrace();
                        System.out.println("Operation failed! Cargo at top of stack cannot support weight");
                    }
                    break;
                case "R":
                    // R) Remove <name>
                    //  and then proceed to automatically remove all cargo instances with the indicated name from the cargo ship.
                    // You may only use the stacks of the ship and the dock to maneuver cargo around
                    //
                    try {
                    System.out.print("What item would you like to remove? ");
                     name = stdin.nextLine();

                    newStack.clearStack();
                    System.out.print("Dock cleared.\n");
                    System.out.printf("Removing cargo '%s'.",name);

                        CargoStack returnedDock = newShip.findAndRemove(name);

                    } catch (EmptyStackException e) {
                        e.printStackTrace();
                        System.out.println("Operation failed! Cargo stack is empty.");
                    } catch (ShipOverweightException e) {
                        e.printStackTrace();
                        System.out.print("Operation failed! Cargo would put ship overweight.");
                    } catch (CargoStrengthException e) {
                        e.printStackTrace();
                        System.out.println("Operation failed! Cargo at top of stack cannot support weight");
                    } catch (FullStackException e) {
                        e.printStackTrace();
                        System.out.print("Operation failed! Cargo stack is at maximum height.");
                    }


                    break;
                case "Q":
                    System.out.println("\nProgram terminating normally...");
                    System.exit(0);
            }

        }



    }
}
