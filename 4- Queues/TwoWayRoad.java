import java.util.ArrayList;
/**
 * Represents a two-way road with multiple lanes for vehicles to queue up in.
 * The road has a traffic light that controls the flow of vehicles.
 * @author Prattusha Biswas 114587992, and recitation 02
 */

public class TwoWayRoad {
    public static final int NUM_WAYS = 2;
    public final int FORWARD_WAY = 0;
    public final int BACKWARD_WAY = 1;

    public static final int NUM_LANES = 3;
    public final int LEFT_LANE = 0;
    public final int MIDDLE_LANE = 1;
    public final int RIGHT_LANE = 2;
    private String name;
    private int greenTime; //maximum total number of steps this road can be active
    private int leftSignalGreenTime; //The number of steps this road remains in the LEFT_SIGNAL state
    private VehicleQueue lanes[][];
    private LightValue lightValue;

    /**
     * Constructs a new TwoWayRoad object with the given name and green time.
     *
     * @param initName      the name of the road
     * @param initGreenTime the maximum total number of steps the road can be active
     * @throws IllegalArgumentException if initGreenTime is less than or equal to 0, or if initName is null
     */
    public TwoWayRoad(String initName, int initGreenTime) {
        if (initGreenTime <= 0 || initName == null) {
            throw new IllegalArgumentException();
        } else {
            name = initName;
            greenTime = initGreenTime;
            leftSignalGreenTime = (int) Math.round(1.0 / NUM_LANES * initGreenTime);

           // System.out.print("LEFTT: "+leftSignalGreenTime);
            this.lanes = new VehicleQueue[NUM_WAYS][NUM_LANES];
            for (int i = 0; i < NUM_WAYS; i++) {
                for (int j = 0; j < NUM_LANES; j++) {
                    this.lanes[i][j] = new VehicleQueue();
                }
            }
            this.lightValue = LightValue.RED;
        }
    }

    /**
     * Removes and returns the next set of vehicles that can proceed through the road.
     * The method checks the state of the traffic light and dequeues vehicles from the
     * appropriate lanes that have waiting vehicles.
     *
     * @param timerVal the current time value, which is used to determine the traffic light state
     * @return an ArrayList of the next set of vehicles that can proceed through the road
     * @throws EmptyQueueException      if there are no vehicles waiting in any of the lanes
     * @throws IllegalArgumentException if the timerVal is less than 0
     */
    public ArrayList<Vehicle> proceed(int timerVal) throws EmptyQueueException {
        //for the cars passed in lanes
        ArrayList<Vehicle> proceed = new ArrayList<>();
        if (timerVal < 0) {
            throw new IllegalArgumentException();
        }
        // while()
        if (timerVal == 0) {
            this.lightValue = LightValue.RED;
        }
        if (timerVal > this.leftSignalGreenTime) {
            this.lightValue = LightValue.GREEN;
        }
        else {
            this.lightValue = LightValue.LEFT_SIGNAL;
        }
      // System.out.println("TIMERVAL"+timerVal);
        if (this.lightValue == lightValue.GREEN) {

            if (!lanes[FORWARD_WAY][MIDDLE_LANE].isEmpty()) {
                proceed.add(lanes[FORWARD_WAY][MIDDLE_LANE].dequeue());
            }
            if (!lanes[BACKWARD_WAY][MIDDLE_LANE].isEmpty()) {
                proceed.add(lanes[BACKWARD_WAY][MIDDLE_LANE].dequeue());
            }
            if (!lanes[FORWARD_WAY][RIGHT_LANE].isEmpty()) {
                proceed.add(lanes[FORWARD_WAY][RIGHT_LANE].dequeue());
            }
            if (!lanes[BACKWARD_WAY][RIGHT_LANE].isEmpty()) {
                proceed.add(lanes[BACKWARD_WAY][RIGHT_LANE].dequeue());
            }

        } else if (this.lightValue == LightValue.LEFT_SIGNAL) {
            if (!lanes[FORWARD_WAY][LEFT_LANE].isEmpty()) {
                proceed.add(lanes[FORWARD_WAY][LEFT_LANE].dequeue());
            }
            if (!lanes[BACKWARD_WAY][LEFT_LANE].isEmpty()) {
                proceed.add(lanes[BACKWARD_WAY][LEFT_LANE].dequeue());
            }
        }
        return proceed;
    }


    /**
     * Adds a vehicle to the queue of the specified lane.
     *
     * @param wayIndex  The way index (0 or 1).
     * @param laneIndex The lane index (0, 1, or 2).
     * @param vehicle   The vehicle to enqueue.
     * @throws IllegalArgumentException If any argument is out of range or vehicle is null.
     */
    public void enqueueVehicle(int wayIndex, int laneIndex, Vehicle vehicle) {
        if (wayIndex < 0 || wayIndex >= NUM_WAYS || laneIndex < 0 || laneIndex >= NUM_LANES || vehicle == null) {
            throw new IllegalArgumentException();
        }
        this.lanes[wayIndex][laneIndex].enqueue(vehicle);
    }

    /**
     * Returns true if the specified lane is empty.
     *
     * @param wayIndex  The way index (0 or 1).
     * @param laneIndex The lane index (0, 1, or 2).
     * @return True if the specified lane is empty, false otherwise.
     * @throws IllegalArgumentException If any argument is out of range.
     */
    public boolean isLaneEmpty(int wayIndex, int laneIndex) {
        if (wayIndex < 0 || wayIndex >= NUM_WAYS || laneIndex < 0 || laneIndex >= NUM_LANES) {
            throw new IllegalArgumentException();
        }
        //way index: if you are going forward or backward
        return this.lanes[wayIndex][laneIndex].isEmpty();
    }

    /**
     * Returns the duration of the green light.
     *
     * @return The duration of the green light.
     */
    int getGreenTime() {
        return greenTime;
    }


    /**
     * Returns the current state of the traffic light.
     *
     * @return The current state of the traffic light.
     */
    LightValue getLightValue() {
        return lightValue;
    }

    /**
     * Sets the current state of the traffic light.
     *
     * @param lightValue The new state of the traffic light.
     */
    void setLightValue(LightValue lightValue) {
        this.lightValue = lightValue;
    }

    /**
     * Returns a string representation of the TwoWayRoad, including the lane's name, current state of the traffic light,
     * and the current vehicles in the lane.
     *
     * @return A string representation of the TwoWayRoad class.
     */

    String name() {
        return name;
    }

    //toString for route 216: and road
    public String toString() {
        // Vehicle newVehicle= new Vehicle();
        String x;
        String car = "";

        car += "\n" + name + ":\n";

        car += "                       FORWARD               BACKWARD\n" +
                "==============================               ===============================\n";
        for (int j = 0; j < NUM_LANES; j++) {
            VehicleQueue forward = lanes[0][j];
            int c;
            if (j == 0) {
                c = 2;
            } else if (j == 2) {
                c = 0;
            } else {
                c = j;
            }

            //if lane has vehicles then
            car += String.format("%25s", "");

            for (Vehicle v : forward.VehicleQueue) {
                car += String.format("[%03d]", v.getSerialId());
            }
            if (j == 0) {
                car += String.format("%4s", "[L]");
                if (this.lightValue == LightValue.GREEN || this.lightValue == LightValue.RED) {
                    //  System.out.print(lightValue);
                    car += String.format("%2s", "x       ");
                }
            } else if (j == 1) {
                car += String.format("%4s", "[M]");
                if (this.lightValue == LightValue.LEFT_SIGNAL || this.lightValue == LightValue.RED) {
                    car += String.format("%2s", "x       ");
                }
            } else if (j == 2) {
                car += String.format("%4s", "[R]");
                if (this.lightValue == LightValue.LEFT_SIGNAL || this.lightValue == LightValue.RED) {
                    car += String.format("%2s", "x       ");
                }
            }


            VehicleQueue backward = lanes[1][c];

            if (c == 0) {
                if (this.lightValue == LightValue.GREEN || this.lightValue == LightValue.RED) {
                    car += "x";
                }
                car += String.format("%2s", "[L]");

            } else if (c == 1) {
                if (this.lightValue == LightValue.LEFT_SIGNAL || this.lightValue == LightValue.RED) {
                    car += "x";
                }
                car += String.format("%2s", "[M]");
            } else if (c == 2) {
                if (this.lightValue == LightValue.LEFT_SIGNAL || this.lightValue == LightValue.RED) {
                    car += "x";
                }
                car += String.format("%1s", "[R]");
            }
            for (Vehicle v : backward.VehicleQueue) {
                car += String.format("%2s[%03d]", "", v.getSerialId());

            }
            car += ("\n------------------------------               -------------------------------\n");
        }


        return car;

    }
}
