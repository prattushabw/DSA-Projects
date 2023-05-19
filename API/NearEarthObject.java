import java.util.Date;
/**
 *  Constructs a NearEarthObject with the specified properties.
 *
 * @author Prattusha Biswas 114587992, and recitation 02
 */

public class NearEarthObject {

    private double maxDiameter;
    private long closestDateTimestamp;
    private double minDiameter;
    private int referenceID;

    private String name;

    private double absoluteMagnitude;

    private double averageDiameter;

    private boolean isDangerous;

    private Date closestApproachDate;

    private double missDistance;

    private String orbitingBody;

    /**
     * All the properties considered: Represents an astronomical object that can potentially pass close to Earth.
     *
     * @param referenceID           the reference ID of the object
     * @param name                  the name of the object
     * @param absoluteMagnitude     the absolute magnitude of the object
     * @param minDiameter           the minimum diameter of the object
     * @param maxDiameter           the maximum diameter of the object
     * @param isDangerous           whether the object is considered dangerous
     * @param closestDateTimestamp  the timestamp of the closest approach date
     * @param missDistance          the miss distance of the object
     * @param orbitingBody          the name of the body the object is orbiting
     */
    public NearEarthObject(int referenceID, String name, double absoluteMagnitude, double minDiameter, double maxDiameter,
                           boolean isDangerous, long closestDateTimestamp, double missDistance, String orbitingBody){
        this.referenceID= referenceID;
        this.name= name;
        this.absoluteMagnitude= absoluteMagnitude;
        this.averageDiameter = (minDiameter+maxDiameter)/2.0;
        this.isDangerous= isDangerous;
        this.closestApproachDate = new Date(closestDateTimestamp);
        this.missDistance= missDistance;
        this.orbitingBody= orbitingBody;
    }

    /**
     * Returns the minimum diameter of the object.
     *
     * @return the minimum diameter of the object
     */
    public double getMinDiameter() {
        return minDiameter;
    }

    /**
     * Sets the minimum diameter of the object.
     *
     * @param minDiameter the minimum diameter to set
     */
    public void setMinDiameter(double minDiameter) {
        this.minDiameter = minDiameter;
    }

    /**
     * Returns the timestamp of the closest approach date.
     *
     * @return the timestamp of the closest approach date
     */
    public long getClosestDateTimestamp() {
        return closestDateTimestamp;
    }

    /**
     * Sets the timestamp of the closest approach date.
     *
     * @param closestDateTimestamp the timestamp of the closest approach date to set
     */
    void setClosestDateTimestamp(long ClosestDateTimestamp){
        this.closestDateTimestamp= closestDateTimestamp;
    }

    /**
     * Returns the maximum diameter of the object.
     *
     * @return the maximum diameter of the object
     */
    public double getMaxDiameter() {
        return maxDiameter;
    }

    /**
     * Sets the maximum diameter of the object.
     *
     * @param maxDiameter the maximum diameter to set
     */
    public void setMaxDiameter(double maxDiameter) {
        this.maxDiameter = maxDiameter;
    }

    /**
     * Returns the reference ID of the object.
     *
     * @return the reference ID of the object
     */
    public int getReferenceID() {
        return referenceID;
    }

    /**
     * Sets the reference ID for this NearEarthObject.
     * @param referenceID the reference ID to set
     */
    public void setReferenceID(int referenceID) {
        this.referenceID = referenceID;
    }

    /**
     * Returns the name of this NearEarthObject.
     * @return the name of this NearEarthObject
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name for this NearEarthObject.
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the absolute magnitude of this NearEarthObject.
     * @return the absolute magnitude of this NearEarthObject
     */
    public double getAbsoluteMagnitude() {
        return absoluteMagnitude;
    }

    /**
     * Sets the absolute magnitude for this NearEarthObject.
     * @param absoluteMagnitude the absolute magnitude to set
     */
    public void setAbsoluteMagnitude(double absoluteMagnitude) {
        this.absoluteMagnitude = absoluteMagnitude;
    }

    /**
     * Returns the average diameter of this NearEarthObject.
     * @return the average diameter of this NearEarthObject
    */
    public double getAverageDiameter() {
        return averageDiameter;
    }

    /**
     * Sets the average diameter for this NearEarthObject.
     * @param averageDiameter the average diameter to set
     */
    public void setAverageDiameter(double averageDiameter) {
        this.averageDiameter = averageDiameter;
    }

    /**
     * Returns whether this NearEarthObject is considered dangerous.
     * @return true if this NearEarthObject is considered dangerous, false otherwise
     */
    public boolean isDangerous() {
        return isDangerous;
    }

    /**
     * Sets whether this NearEarthObject is considered dangerous.
     * @param dangerous true if this NearEarthObject is considered dangerous, false otherwise
     */
    public void setDangerous(boolean dangerous) {
        isDangerous = dangerous;
    }

    /**
     * Returns the date of the closest approach of this NearEarthObject.
     * @return the date of the closest approach of this NearEarthObject
     */
    public Date getClosestApproachDate() {
        return closestApproachDate;
    }

    /**
     * Sets the date of the closest approach for this NearEarthObject.
     * @param closestApproachDate the date of the closest approach to set
     */
    public void setClosestApproachDate(Date closestApproachDate) {
        this.closestApproachDate = closestApproachDate;
    }

    /**
     * Returns the miss distance of this NearEarthObject.
     * @return the miss distance of this NearEarthObject
     */
    public double getMissDistance() {
        return missDistance;
    }

    /**
     * Sets the miss distance for this NearEarthObject.
     * @param missDistance the miss distance to set
     */
    public void setMissDistance(double missDistance) {
        this.missDistance = missDistance;
    }

    /**
     * Returns the name of the body that this NearEarthObject is orbiting.
     * @return the name of the body that this NearEarthObject is orbiting
     */
    public String getOrbitingBody() {
        return orbitingBody;
    }

    /**
     * Sets the name of the body that this NearEarthObject is orbiting.
     * @param orbitingBody the name of the body that this NearEarthObject is orbiting to set
     */

    public void setOrbitingBody(String orbitingBody) {
        this.orbitingBody = orbitingBody;
    }
}
