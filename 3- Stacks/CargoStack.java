import java.util.Stack;

/**
 * This class Represents a CargoStack so  Cargo objects can be physically stacked on top of one another using a java.util package
 *
 * @author Prattusha Biswas 114587992, and recitation 02
 */
public class CargoStack {


    /**
     * Variables
     * <p>
     * cargoStack:
     * implementation of the cargo stack java.util.package
     * totalWeight:
     * The total weight of the cargo stack
     */
    Stack<Cargo> cargoStack;

    Stack<Cargo> temp = new Stack<>();
    /**
     * This is a default Constructor used to create a new slide object
     * This object has been initialized to an empty Slide
     *
     * @custom.precondition
     * The cargoStack object is initialized
     * The double totalWeight of the cargoStack is set to 0.0
     */
    CargoStack(){
        cargoStack= new Stack<Cargo>();

    }

    /**
     *Pushes the indicated cargo to the top of the stack
     *
     * @param cargo
     *The cargo object to be wrapped and inserted into the top of the stack.
     * @custom.pgetrecondition
     * cargo is not null.
     *@throws IllegalArgumentException
     * Thrown if cargo is null.
     */
    void push(Cargo cargo) throws CargoStrengthException, EmptyStackException {
        //System.out.println("NAME:"+cargo.getName());
        // System.out.println("SIZE:"+cargoStack.size());
        //  System.out.println("STRE:"+cargo.getStrength());
        if (cargo == null) {
            throw new IllegalArgumentException();
        }else if(cargoStack.size() > 0 && cargo.getStrength().toString().charAt(0) > peek().getStrength().toString().charAt(0)) {
            //System.out.println("CARGo:"+cargo.getStrength().toString().charAt(0) );
            // System.out.println("PEEk:"+ peek().getStrength().toString().charAt(0));
            throw new CargoStrengthException("Cannot place " + cargo.getStrength() + " cargo on top of " + peek().getStrength() + " cargo.");
        } else{
            cargoStack.push(cargo);
        }
    }

    /**
     * clears the dock
     *
     * @return
     * the dock is cleared
     */
    void clearStack() {
        cargoStack.clear();
    }


    /**
     *Pops the cargo at the top of stack.
     *
     * @custom.precondition
     * stack is not null.
     * @custom.postcondition
     * the cargo was removed from the top of the stack.
     * @return
     * The reference to the cargo contained within the stack.
     *@throws EmptyStackException
     * Thrown if stack is empty.
     */
    Cargo pop()throws EmptyStackException {
        if(isEmpty()){
            throw new EmptyStackException("stack is empty.");
        }
        return cargoStack.pop();
    }

    /**
     *Peek at the cargo at the top of stack.
     *
     * @custom.precondition
     * stack is not null.
     * @custom.postcondition
     * the user is able to look at what cargo is on top of the stack.
     * @return
     * The reference to the cargo contained within the stack.
     *@throws EmptyStackException
     * Thrown if stack is empty.
     */
    public Cargo peek()throws EmptyStackException{
        if(isEmpty()){
            throw new EmptyStackException("stack is empty.");
        }
        return cargoStack.peek();
    }

    /**
     *Returns the total number of cargos in the stack.
     *
     * @return
     * The count of all cargos in the stack.
     */
    int size(){
        return cargoStack.size();
    }

    /**
     *Checks to see if stack is empty
     *
     * @return
     * returns true if stack is empty and false if it is not empty.
     */
    boolean isEmpty(){
        return cargoStack.isEmpty();
    }

    /**
     *print cargo strength of the stacks
     *
     *@custom.precondition
      * stack is not empty.
      * @custom.postcondition
     * the method will print out the strength of each cargo
     * @return
     * print the cargo strength of each stack
     *@throws EmptyStackException
     * Thrown if stack is empty.
     * @throws CargoStrengthException
     * thrown if cargo breaks strength rule
     */

    void printStack() throws EmptyStackException, CargoStrengthException {
        // initialize an empty string to hold the cargo strengths
        String strengthsString = "";


        while (!cargoStack.isEmpty()) {
            Cargo x = cargoStack.pop();
            temp.push(x);
        }

        while (!temp.isEmpty()) {
            Cargo x = temp.pop();
            cargoStack.push(x);
            strengthsString += x.getStrength().getStrength() + ",";
        }

        // remove the last comma if there is more than one cargo strength
        if (strengthsString.endsWith(",")) {
            strengthsString = strengthsString.substring(0, strengthsString.length() - 1);
        }

        System.out.print(strengthsString);
    }}
