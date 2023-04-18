/**
 * This class represents the light values a lane may be: RED, GREEN, or LEFT_SIGNAL
 *
 * @author Prattusha Biswas 114587992, and recitation 02
 */
public enum LightValue {
    GREEN("G"), RED("R"), LEFT_SIGNAL("L");

    private String lightValue;
    LightValue(String lightValue) {
        this.lightValue = lightValue;
    }

    public String getStrength() {
        return lightValue;
    }
}
