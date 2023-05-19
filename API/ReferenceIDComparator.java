/**
 * The ReferenceIDComparator class implements the java.util.Comparator interface
 *The compare method is used to compare two NearEarthObject objects and return their relative order in a sorted list.
 *
 * @author Prattusha Biswas 114587992, and recitation 02
 */

public class ReferenceIDComparator implements java.util.Comparator<NearEarthObject>{
    @Override
    public int compare(NearEarthObject left, NearEarthObject right) {
        if (left.getReferenceID() == right.getReferenceID())
            return 0;
        else if (left.getReferenceID() > right.getReferenceID())
            return 1;
        else
            return -1;
    }
}
