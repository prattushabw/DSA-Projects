/**
 * The ApproachDateComparator class implements the java.util.Comparator interface
 * and compares NearEarthObject instances based on their closest approach date.
 *
 * @author Prattusha Biswas 114587992, and recitation 02
 */


/**Compares two NearEarthObject instances based on their closest approach date.
 * @param left the first NearEarthObject to compare
 * @param right the second NearEarthObject to compare
 * @return an integer value that represents the result of the comparison
 */
public class ApproachDateComparator implements java.util.Comparator<NearEarthObject> {
    @Override
    public int compare(NearEarthObject left, NearEarthObject right) {
        if (left.getClosestApproachDate().before(right.getClosestApproachDate()))
            return -1;
        else if (left.getClosestApproachDate().after(right.getClosestApproachDate()))
            return 1;
        else
            return 0;
    }
}

