import java.util.Stack;


/**
 * This class Represents a container ship which holds stacks of containers.
 *
 * @author Prattusha Biswas 114587992, and recitation 02
 */
public class CargoShip {

    /**
     * Variables & Objects
     * <p>
     * stacks:
     * stacks array implemented from the CargoStack class
     * maxHeight:
     * int variable of the max height of the container ship of all the cargos.
     * double maxWeight:
     * double variable of the max weight of the container ship of all the cargos.
     */
    CargoStack[] stacks;
    private final int maxHeight;
    private final double maxWeight;
    //private CargoStack dockStack;

    double totalWeight = 0;
    /**
     * Default Constructor.
     *
     * @param numStacks
     * The number of stacks this ship can support.
     * @param initMaxHeight
     * The maximum height of any stack on this ship.
     * @param initMaxWeight
     * The maximum weight for all the cargo on the ship.
     * @custom.precondition
     * numStacks > 0.
     * initMaxHeight > 0.
     * initMaxWeight > 0.
     * @custom.postcondition
     * This object has been initialized to a CargoShip object with the
     * indicated number of stacks, maxHeight, and maxWeight.
     * @throws IllegalArgumentException
     * if either of the parameters are now within the appropriate bounds.
     */
    public CargoShip(int numStacks, int initMaxHeight, double initMaxWeight){
        if(numStacks<0 && initMaxWeight<0 && initMaxHeight<0){
            throw new IllegalArgumentException();
        }
        stacks= new CargoStack[numStacks];
        for(int i =0;i<numStacks;i++){
            stacks[i]= new CargoStack();
        }
        maxHeight= initMaxHeight;
        maxWeight= initMaxWeight;
    }

    /**
     * Pushes a cargo container to the indicated stack on the cargo ship
     *
     * @param cargo
     * The container to place on the stack.
     * @param stack
     * The index of the stack on the ship to push cargo onto.
     * @custom.precondition
     * This CargoShip is initialized and not null.
     * cargo is initialized and not null.
     * 1 ≤ stack ≤ stacks.length.
     * The size of the stack at the desired index is less than maxHeight.
     * The total weight of all Cargo on the ship and cargo.getWeight()is less than maxWeight.
     * @custom.postcondition
     * The cargo has been successfully pushed to the given stack
     * Or the appropriate exception has been thrown
     * @throws IllegalArgumentException
     * If cargo is null or stack is not in the appropriate bounds.
     * @throws FullStackException
     * If the stack being pushed to is at the max height.
     * @throws ShipOverweightException
     * If cargo made the ship exceed maxWeight and sink.
     * @throws CargoStrengthException
     * If the cargo was stacked on top of a weaker cargo
     */
    public void pushCargo(Cargo cargo, int stack) throws FullStackException, ShipOverweightException, CargoStrengthException, EmptyStackException {
      //the  try{
        // System.out.print(stacks[stack - 1].size());
        if(cargo == null || stack<0 || stack>stacks.length){
            throw new IllegalArgumentException();
        }else if (stacks[stack-1].size()>=maxHeight){
            throw new FullStackException("you reached the maxed height");
          //  dockStack.push(cargo);
        }else if(shipWeight() + cargo.getWeight() > maxWeight){
            //what is the imaginary bonus points for "yellow submarine" references in the exception message
            throw new ShipOverweightException("you reached the max weight, your cargo sunk!");
           // stacks.push(cargo);
        }else if(stacks[stack - 1].size()>0 && cargo.getStrength().ordinal() > stacks[stack-1].peek().getStrength().ordinal()){
            throw new CargoStrengthException("you stacked heavy cargo over weaker cargo");
           // stacks[stack].push(cargo);

        }else {
            stacks[stack - 1].push(cargo);
            totalWeight += cargo.getWeight();
        }
    }



    /**
     * Pops a cargo from one of the specified stack.
     *
     * @param stack
     * The index of the stack to remove the cargo from.
     * @custom.precondition
     * This CargoShip is initialized and not null.
     * 1 ≤ stack ≤ stacks.length.
     * @custom.postcondition
     * The cargo has been successfully been popped from the stack, and returned
     * Or the appropriate exception has been thrown
     * @throws IllegalArgumentException
     * If stack is not in the appropriate bounds.
     * @throws EmptyStackException
     * If the stack being popped from is empty.
     */
    public Cargo popCargo(int stack) throws EmptyStackException{
        if(stack<1 || stack>stacks.length){
            throw new IllegalArgumentException();
        }else if(stacks[stack-1].isEmpty()){
            throw new EmptyStackException("the cargo being popped is empty");
        }
        totalWeight -= stacks[stack-1].peek().getWeight();
        return stacks[stack-1].pop();
    }

    double shipWeight(){
        return totalWeight;
    }

    /**
     * Finds and prints a formatted table of all the cargo on the ship with a given name.
     *
     * @param name
     * The name of the cargo to find and print.
     * @custom.precondition
     * This CargoShip is initialized and not null.
     * @custom.postcondition
     * The details of the cargo with the given name have been found and printed
     * Or the user has been notified that no such cargo has been found.
     *  @throws EmptyStackException
     * thrown if stack is empty.
     * @throws CargoStrengthException
     * thrown if cargo breaks strength rule
     *
     */
    public void findAndPrint(String name) throws EmptyStackException, CargoStrengthException {
        //while prev-stack isn't empty pop the top push to temp and increment depth
        //while temp stack isn't empty pop from the top push to prev-stack and reset to 0

        int count=0; //Init count
        double weight=0; //init weight
        boolean found = false; //Init found to false bc cargo not yet found
        boolean menuPrinted = false;

        for (int i = 0; i < stacks.length; i++) { //For each stack on ship
            Stack<Cargo> temp = new Stack<Cargo>(); //Temp stack to restore stacks
            int depth = 0; //Init depth to 0
            while (!stacks[i].isEmpty()) { //While: not empty
                Cargo current = stacks[i].pop();
                temp.push(current);
                depth++;
                if(current.getName().equals(name)) {
                    if (!menuPrinted) {
                        System.out.printf("\n\nCargo '%s' found at the following locations:\n", name);
                        System.out.print("Stack   Depth   Weight   Strength\n" +
                                "=======+=======+========+==========\n");
                        menuPrinted = true;
                    }
                    System.out.printf("%4d   |%4d   |%6.0f  |%9s\n", i + 1, depth-1, current.getWeight(), current.getStrength());
                    weight += current.getWeight();
                    count++;
                    found = true;
                }
            }
            while(!temp.isEmpty()){
                stacks[i].push(temp.pop());
            }

            }
            if (!found) {
             System.out.printf("Cargo '%s' could not be found on the ship\n", name);
            }
            else {
              System.out.printf("\nTotal Count:  %d\n" + "Total Weight: %.0f", count, weight);
          }
        }

    /**
     * finds a cargo with a name in the ship and places back in dock
     *
     *@custom.precondition
     * stack is not empty.
     * @custom.postcondition
     * the method will print out the strength of each cargo
     * @return
     * returns the CargoStack and prints each of the steps for removing the cargo with the specific name
     *@throws EmptyStackException
     * Thrown if stack is empty.
     * @throws CargoStrengthException
     * thrown if cargo breaks strength rule
     * @throws ShipOverweightException
     * thrown if ship becomes over weight
     * @throws FullStackException
     * thrown if the stack meets max height
     */

    public CargoStack findAndRemove(String name) throws EmptyStackException, CargoStrengthException, ShipOverweightException, FullStackException {
        /**
         * first go through all the stacks in the array and pop them as you find the name if it is at the top, move to dock
         * then create a new stack and go through a stack and pop them if they are not the same name and move to another stack that
         * is under the max-height. when you find the name pop and move to dock.
         * print every step that is made and count every step as well.
         */
        boolean found = false;
        int count = 0;
        boolean menuPrinted = false;
        CargoStack dock = new CargoStack();
        Stack<Cargo> tempDock = new Stack<Cargo>();
        Stack<Cargo> notName = new Stack<Cargo>();

        int stack;

        for (int i = stacks.length - 1; i > 0; i--) {  //goes through each stack in the ship
            //int i = stacks.length;
            while (!stacks[i].isEmpty()) {
                if (stacks[i].peek().getName().equals(name)) {
                    dock.push(stacks[i].pop());
                    totalWeight -= dock.peek().getWeight();
                    System.out.printf("\n\nStep %d: Moved cargo from stack %d to dock.\n", ++count, i + 1);
                    for (int k=0,j = stacks.length - 1; j >=0; j--,k++) {
                        if (stacks[j].size() == 0) {
                            System.out.print("  \nStack " + (k + 1) + ": ");
                        } else {
                            System.out.print("  \nStack " + (k + 1) + ": ");
                            stacks[j].printStack();
                        }
                    }

                    System.out.print("  \nDock: ");
                    dock.printStack();
                    //System.out.print(tempDock.push(dock.pop()).getStrength().getStrength());


                    System.out.printf("\n\nTotal Weight = %.0f / %.0f", shipWeight(),maxWeight);

                    found = true;
                } else if (!stacks[i].peek().getName().equals(name)) {


                        notName.push(stacks[i].pop());
                    for (int l = stacks.length - 1; l > 0; l--) {
                        if(l==i){
                                continue;
                            }if (stacks[l].size() < maxHeight){
                              stacks[l].push(notName.pop());
                            System.out.printf("\n\nStep %d: Moved cargo from stack %d to stack %d.\n", ++count, l, i);
                        }

                        for (int k=0,j = stacks.length - 1; j >=0; j--,k++) {
                            if (stacks[j].size() == 0) {
                                System.out.print("  \nStack " + (k + 1) + ": ");
                            } else {
                                System.out.print("  \nStack " + (k + 1) + ": ");
                                stacks[j].printStack();
                            }
                        }

                        System.out.print("  \nDock: ");
                        dock.printStack();
                        //System.out.print(tempDock.push(dock.pop()).getStrength().getStrength());


                        System.out.printf("\n\nTotal Weight = %.0f / %.0f", shipWeight(),maxWeight);
                }
                        }
                    }
                }
        if (!found) {
            System.out.printf("\nCargo '%s' could not be found on the ship.", name);
        } else {

            System.out.printf("\nCargo '%s' removed from the ship.", name);
            System.out.printf("\nSteps taken: "+count);
        }
          while(!tempDock.isEmpty()){
                dock.push(tempDock.pop());
                System.out.println("NOO");
        }
             return dock;
         }

    }


