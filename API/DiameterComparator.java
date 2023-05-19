/**
 * The DiameterComparator class implements the java.util.Comparator interface
 * and compares NearEarthObject instances based on their average diameter.
 *
 * @author Prattusha Biswas 114587992, and recitation 02
 */

/**
 * Compares two NearEarthObject instances based on their average diameter.
 * @param left the first NearEarthObject to compare
 * @param right the second NearEarthObject to compare
 * @return an integer value that represents the result of the comparison
 */
public class DiameterComparator implements java.util.Comparator<NearEarthObject>  {
    @Override
    public int compare(NearEarthObject left, NearEarthObject right) {
        return Double.compare(left.getAverageDiameter(), right.getAverageDiameter());
    }
}
