import java.util.ArrayList;
import java.util.Scanner;
/**
 * This class asks user for information to start simulation such as simulation time, probability, number of street, street name, and ma green light time for each street
 * it will then pass the information to the simulate method which will start the simulation and provide us simulation summary
 *
 * Start for application, asks user for following values: simulationTime (int), arrivalProbability (double),
 * numRoads (int), a name for each road, and a "green" time for each road.
 * @author Prattusha Biswas 114587992, and recitation 02
 */

public class IntersectionSimulator {

    public static void main(String[] args) {

        System.out.println("Welcome to IntersectionSimulator 2023");

        System.out.print("\nInput the simulation time: ");
        Scanner stdin = new Scanner(System.in);
        int simulation = stdin.nextInt();
        stdin.nextLine();

        System.out.print("Input the arrival probability: ");  // chance of car entering a lane, for all lanes.
        double probability = stdin.nextDouble();
        stdin.nextLine();

        System.out.print("Input number of Streets: ");
        int numOfStreets = stdin.nextInt();
        stdin.nextLine();

        String[] twoWayRoads = new String[numOfStreets];
        for (int i = 0; i < numOfStreets; i++) {
            System.out.printf("Input Street %d name: ", i + 1);
            String street = stdin.nextLine();
            twoWayRoads[i]=street;
            for (int j = 0; j < i; j++) {
                if (twoWayRoads[j].equals(street)) {
                    System.out.print("\nDuplicate Detected.");
                    System.out.printf("\nInput Street %d name: ", i + 1);
                    street = stdin.nextLine();
                    break;
                }
            }
        }
        int[] greenTime = new int[numOfStreets];
        for (int i = 0; i < numOfStreets; i++) {
            System.out.printf("Input max green time for %s: ", twoWayRoads[i]);
            int stMaxTime = stdin.nextInt();
            stdin.nextLine();
            greenTime[i] = stMaxTime;
        }

        System.out.println("\nStarting Simulation...\n");
        simulate(simulation, probability, twoWayRoads, greenTime);

    }

    /**
     * This method does the actual simulation. Above, a Activity Diagram is presented to demonstrate how the simulation works.
     * This method actually implements the algorithm described by that diagram, using Intersection, BooleanSourceHW4, and TwoWayRoad.
     */
    public static void simulate(int simulationTime, double arrivalProbability, String[] roadNames, int[] maxGreenTimes) {
        try {
        Vehicle car = new Vehicle(1);
        TwoWayRoad[] twoWayRoads = new TwoWayRoad[roadNames.length];
            for (int i=0; i< twoWayRoads.length;i++){
                twoWayRoads[i]= new TwoWayRoad(roadNames[i],maxGreenTimes[i]);

            }
            Intersection intersection = new Intersection(twoWayRoads);
            BooleanSource carArrived = new BooleanSource(arrivalProbability);


            int carPassed = 0;
            int waitTime = 0;
            int totalWaitTime = 0;
            int longestWaitTime = 0;
            int numCarsWaiting = 0;
            double result = 0.00;


            String light = "";
            int simulation = simulationTime;
            int timeStep = 1;
            int timer = 0;
            String arrivingCars="";

            boolean carsWaiting = true;

            /* There is still vehicle in the lane or time < simTime*/
            while (carsWaiting || simulation!=0) {
                System.out.println("################################################################################\n");

                if (simulation > 0) {
                    for (int j = 0; j < intersection.getNumRoads(); j++) { //going through the roads
                        for (int k = 0; k < twoWayRoads[j].NUM_WAYS; k++) { //forward or backwards
                            for (int l = 0; l < twoWayRoads[k].NUM_LANES; l++) {// left middle or right
                                if (carArrived.occursHW4()) {

                                    String direction = "";
                                    Vehicle v = new Vehicle(timeStep);
                                    if (k == 0) {
                                        direction = "FORWARD";
                                    } else {
                                        direction = "BACKWARDS";
                                    }

                                    String lane = "";
                                    if (l == 0) {
                                        lane = "LEFT";
                                    } else if (l == 1) {
                                        lane = "MIDDLE";
                                    } else {
                                        lane = "RIGHT";
                                    }
                                    try {
                                        intersection.enqueueVehicle(j, k, l, v);
                                        numCarsWaiting++;
                                        String cars = String.format("Car[%03d]", v.getSerialId()); // create the cars string
                                        String message = String.format("%s entered %s, going %s in %s lane.\n", cars, roadNames[j], direction, lane); // create the message string
                                        arrivingCars += message;

                                       // String cars = String.format("Car[%03d]", v.getSerialId());

                                        //arrivingCars += (cars + " entered %s, going %s in %s lane.\n",roadNames[j], direction, lane);
                                    } catch (FullQueueException e) {
                                        System.out.print("queue is full");
                                    }
                                }
                            }
                        }
                    }
                }


                System.out.print("Time Step: " + timeStep++);
                int lightIndex = intersection.getLightIndex();
                timer = intersection.getCountdownTimer();

                if(timer==1){
                    light = "Left Signal";
                } else if (twoWayRoads[lightIndex].getLightValue() == LightValue.GREEN) {//find out what road i am on then print light color for the current road
                    light = "Green Light";
                    // System.out.print("lightIndex:"+lightIndex);
                }


              //  System.out.print("TIMESTEP:"+timeStep);
               if(timeStep==2){
                    System.out.printf("\n\nGreen Light for %s.",roadNames[lightIndex]);
                }else {
                    System.out.printf("\n\n%s for %s.", light, roadNames[lightIndex]); //light for specific street
                  // ++timer;
               }
                        //timer = intersection.getCountdownTimer();

                        //timer decrements by one until it reaches 0. then it will switch the next road to green
                        System.out.printf("\nTimer = %d", timer);


                //ARRIVING cars
                System.out.println("\n\nARRIVING CARS:");
                if(simulation>0) {
                    System.out.print(arrivingCars);
                }else {
                    System.out.print("Cars no longer arriving");
                }
                //PASSING CARS
                System.out.println("\nPASSING CARS:");
                ArrayList<Vehicle> pass = intersection.timeStep();

                carPassed += pass.size(); //number of cars passed
                numCarsWaiting -= pass.size(); //number of cars waiting

               // System.out.println("PASSING CARS:");
                if (!pass.isEmpty()) {
                    //wait time is current timer step im on - time arrived
                    for(Vehicle v: pass){
                    String cars = String.format("Car[%03d]", v.getSerialId());
                    System.out.printf(cars +" passes through. Wait time of %d.\n", ((timeStep - v.getTimeArrived())-1));
                    waitTime = (timeStep - v.getTimeArrived())-1;
                        if (waitTime > longestWaitTime) { // check if current wait time is longer than previous max
                            longestWaitTime = waitTime; // update longest wait time
                        }
                        totalWaitTime += waitTime; // add current wait time to total
                }}
                if (simulation > 0)
                    simulation--;
                if(numCarsWaiting==0){
                    carsWaiting= false;
                }
                intersection.display();


                if (totalWaitTime == 0) {
                    // handle the case where waitTime is zero
                    result=0.00;
                } else {
                     result = (double)totalWaitTime/carPassed ;
                    // use the result variable as needed
                }
                System.out.print("STATISTICS: \n");
                System.out.printf("    Cars currently waiting:  %d cars", numCarsWaiting);
                System.out.printf("\n    Total cars passed:       %d cars", carPassed);
                System.out.printf("\n    Total wait time:         %d turns", totalWaitTime);
                System.out.printf("\n    Average wait time:       %.2f turns\n", result);


            }
            System.out.print("################################################################################\n" +
                    "################################################################################\n" +
                    "################################################################################\n" +
                    "\n" +
                    "SIMULATION SUMMARY:\n");

            System.out.print("\n    Total Time: " + --timeStep + " steps");
            System.out.print("\n    Total vehicles: " + carPassed+ " vehicles");
            System.out.print("\n    Longest wait time: " + longestWaitTime+" turns");
            System.out.print("\n    Total wait time: " + totalWaitTime +" turns");
            System.out.printf("\n    Average wait time: %.2f turns", result);
            System.out.print("\n\n End simulation.");
        } catch(EmptyQueueException e){
            //e.printStackTrace();
                System.out.print("queue is empty");
            }

    }
}
