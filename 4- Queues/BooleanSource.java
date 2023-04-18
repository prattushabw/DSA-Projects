import java.io.*;

/**
 * A class called BooleanSourceHW4 which abstracts a random occurrence generator.
 *
 * @author Prattusha Biswas 114587992, and recitation 02
 */
public class BooleanSource {
    // Constants
    private final static String RANDOM_FILE_FLAGS = "hw4randomFlags.txt";
    private final static String RANDOM_FILE_NUMBERS = "hw4randomNumbers.txt";
    private final static int MAX_SIZE = 20000;

    // Instance variables
    private double probability;
    private int indexFlags = 0;
    private int indexNumbers = 0;
    private double randomFLags[] = new double[MAX_SIZE];
    private double randomNumbers[] = new double[MAX_SIZE];

    /**
     * Constructs a BooleanSource object with the given probability.
     *
     * @param initProbability the probability of a boolean value occurring
     * @throws IllegalArgumentException if the probability is not between 0 and 1
     */
    public BooleanSource(double initProbability) throws IllegalArgumentException {
        if (initProbability < 0.0 || initProbability > 1.0)
            throw new IllegalArgumentException();
        readRandomFile(RANDOM_FILE_FLAGS, randomFLags);
        readRandomFile(RANDOM_FILE_NUMBERS, randomNumbers);
        probability = initProbability;
    }

    /**
     * Returns a boolean value that has a probability of occurrence equal to the probability
     * given at construction time.
     *
     * @return a boolean value
     */
    public boolean occursHW4() {
        boolean flag;
        if (indexFlags < MAX_SIZE)
            flag = (randomFLags[indexFlags++] < probability);
        else
            flag = (Math.random() < probability);
        return flag;
    }

    /**
     * Returns a random double value.
     *
     * @return a random double value
     */
    public double randomHW4() {
        if (indexNumbers < MAX_SIZE)
            return randomNumbers[indexNumbers++];
        else
            return Math.random();
    }

    /**
     * Reads a random file and populates an array with its contents.
     *
     * @param filename    the name of the file to read
     * @param randomArray the array to populate with random values
     */
    public static void readRandomFile(String filename, double[] randomArray) {
        try {
            String currentLine;
            BufferedReader objReader = new BufferedReader(new FileReader(filename));
            for (int i = 0; i < MAX_SIZE; i++) {
                currentLine = objReader.readLine();
                randomArray[i] = Double.parseDouble(currentLine);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading from " + filename);
            e.printStackTrace();
        }
    }
}

