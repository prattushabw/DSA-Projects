import java.util.ArrayList;
/**
 * This class represents the vehicle queue which stores the cars being enqueue in a queue and dequeued from the queue.
 *
 * @author Prattusha Biswas 114587992, and recitation 02
 */
public class VehicleQueue extends ArrayList<Vehicle>{

    ArrayList<Vehicle> VehicleQueue = new ArrayList<>();
    //Vehicle car=null;
    /**
     * Inserts the specified vehicle into this queue
     *
     * @param vehicle the vehicle to add
     * @return
     */
    void enqueue(Vehicle vehicle){
        VehicleQueue.add(vehicle);
    }

    /**
     * Retrieves and removes the rear of this queue
     *
     * @return removes the rear of this queue, or null if this queue is empty
     * @throws EmptyQueueException is queue os empty
     */
    Vehicle dequeue() throws EmptyQueueException{
        if(isEmpty()){
            throw new EmptyQueueException("Queue is empty.");
        }
        return VehicleQueue.remove(0);
    }

    /**
     * Returns the number of vehicles in this queue.
     *
     * @return the number of vehicles in this queue
     */
    public int size() {
        return VehicleQueue.size();
    }

    /**
     * Returns true if this queue contains no vehicles.
     *
     * @return true if this queue contains no vehicles, else false
     */
    public boolean isEmpty() {
        if(VehicleQueue.size()==0){
            return true;
        }
        return false;
    }

}


//import java.util.LinkedList;
//
//
//public class VehicleQueue extends LinkedList<Vehicle>{
//
//    LinkedList<Vehicle> VehicleQueue = new LinkedList<>();
//
//    /**
//     * Inserts the specified vehicle into this queue
//     *
//     * @param vehicle the vehicle to add
//     * @return
//     */
//    void enqueue(Vehicle vehicle){
//        addLast(vehicle);
//    }
//
//    /**
//     * Retrieves and removes the rear of this queue
//     *
//     * @return removes the rear of this queue, or null if this queue is empty
//     * @throws EmptyQueueException is queue os empty
//     */
//    Vehicle dequeue() throws EmptyQueueException{
//        if(VehicleQueue.isEmpty()){
//            throw new EmptyQueueException("Queue is empty.");
//        }
//        return pollFirst();
//    }
//
//    /**
//     * Returns the number of vehicles in this queue.
//     *
//     * @return the number of vehicles in this queue
//     */
//    public int size() {
//        return super.size();
//    }
//
//    /**
//     * Returns true if this queue contains no vehicles.
//     *
//     * @return true if this queue contains no vehicles, else false
//     */
//    public boolean isEmpty() {
//        return super.isEmpty();
//    }
//
//}

