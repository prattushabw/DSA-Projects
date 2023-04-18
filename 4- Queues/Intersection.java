import java.util.ArrayList;

/**
 * The Intersection class represents a traffic intersection that connects several roads.
 * It keeps track of the roads that are intersecting and what will be happening in each time step
 *
 * @author Prattusha Biswas 114587992, and recitation 02
 */

public class Intersection {
    /**
     * The maximum number of roads that an intersection can have.
     */
    private static final int MAX_ROADS = 4;

    /**
     * The array of roads that connect to the intersection.
     */
    private TwoWayRoad[] roads;

    /**
     * The index of the road that currently has the green light.
     */
    private int lightIndex;

    /**
     * The number of time steps left until the current light changes.
     */
    private int countdownTimer;

    /**
     * The queue of all vehicles that have passed through the intersection.
     * Returns the number of vehicles that have passed through the intersection.
     *
     * @return the number of vehicles that have passed through the intersection
     */
    public static VehicleQueue vehiclesPassed = new VehicleQueue();

    int getVehiclesPassed() {
        return vehiclesPassed.size();
    }

    /**
     * Creates a new Intersection object with the given array of roads.
     *
     * @param initRoads the array of roads that connect to the intersection
     * @throws EmptyQueueException      if one of the roads has an empty vehicle queue
     * @throws IllegalArgumentException if the array of roads is null or contains null elements, or if its length exceeds MAX_ROADS
     */
    public Intersection(TwoWayRoad[] initRoads) throws EmptyQueueException {
        //TwoWayRoad road = new TwoWayRoad();
        roads = initRoads;
        lightIndex = 0;
        countdownTimer = roads[lightIndex].getGreenTime();
        for (int i = 0; i < roads.length; i++) {
            if (initRoads == null || initRoads[i] == null || initRoads.length > MAX_ROADS) {
                throw new IllegalArgumentException();
            }
        }
    }

    /**
     * Advances the simulation by one time step and returns an ArrayList containing all vehicles that have passed through the intersection.
     *
     * @return an ArrayList containing all vehicles that have passed through the intersection
     * @throws EmptyQueueException if one of the roads has an empty vehicle queue
     */
    public ArrayList<Vehicle> timeStep() throws EmptyQueueException {

        ArrayList<Vehicle> vehiclesPassed;
        boolean switching = false;
        TwoWayRoad current = this.roads[lightIndex];
        for (int i = 0; i < roads.length; i++) {
            if (i != lightIndex) {
                roads[i].setLightValue(LightValue.RED);
            }
        }
        vehiclesPassed = current.proceed(countdownTimer);
        if (countdownTimer == 1) {
            //switch to a different road
            if (lightIndex == roads.length-1) {
               lightIndex = 0;
            }
            else {
                lightIndex++;
            }
            switching = true;
            countdownTimer = this.roads[lightIndex].getGreenTime();
        }
        if (!switching) {
            countdownTimer--;
        }
        return vehiclesPassed;
    }

    /**
     * Enqueues a vehicle on the specified road, way, and lane.
     *
     * @param roadIndex the index of the road where the vehicle will be enqueued
     * @param wayIndex  the index of the way where the vehicle will be enqueued
     * @param laneIndex the index of the lane where the vehicle will be enqueued
     * @param vehicle   the vehicle to be enqueued
     * @throws FullQueueException       if the vehicle queue on the specified road, way, and lane is full
     * @throws IllegalArgumentException if the vehicle is null or any of the indices are out of bounds
     */
    public void enqueueVehicle(int roadIndex, int wayIndex, int laneIndex, Vehicle vehicle) throws FullQueueException {
        if (vehicle == null || roadIndex < 0 || roadIndex >= roads.length || wayIndex < 0 || wayIndex >= TwoWayRoad.NUM_WAYS || laneIndex < 0 || laneIndex >= TwoWayRoad.NUM_LANES) {
            throw new IllegalArgumentException();
        }
        roads[roadIndex].enqueueVehicle(wayIndex, laneIndex, vehicle);
    }

    /**
     * Prints the intersection to the terminal in a neatly formatted manner.
     */
    public void display() {
        String roadName = "";
        for (int i = 0; i < roads.length; i++) {
            TwoWayRoad road = roads[i];
            roadName += road.toString() + "\n";
        }
        System.out.println(roadName);
    }

    /**
     * Returns the number of roads in this Intersection.
     *
     * @return the number of roads in this Intersection
     */
    int getNumRoads() {
        return roads.length;
    }

    /**
     * Returns the index of the road that currently has the green light.
     *
     * @return the index of the road that currently has the green light
     */
    int getLightIndex() {
        return lightIndex;
    }

    /**
     * Returns the countdown timer for the current green light.
     *
     * @return the countdown timer for the current green light
     */
    int getCountdownTimer() {
        return countdownTimer;
    }


}

