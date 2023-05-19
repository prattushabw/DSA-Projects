/**
 * The MissDistanceComparator class implements the java.util.Comparator interface
 * and compares NearEarthObject instances based on their miss distance.
 *
 * @author Prattusha Biswas 114587992, and recitation 02
 */

/**
 * Compares two NearEarthObject instances based on their miss distance.
 * @param left the first NearEarthObject to compare
 * @param right the second NearEarthObject to compare
 * @return an integer value that represents the result of the comparison
 */
public class MissDistanceComparator implements java.util.Comparator<NearEarthObject> {

    @Override
    public int compare(NearEarthObject left, NearEarthObject right) {
        if (left.getMissDistance() == right.getMissDistance())
            return 0;
        else if (left.getMissDistance() > right.getMissDistance())
            return 1;
        else
            return -1;
    }
}

