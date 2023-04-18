/**
 * This class represents the strength values a cargo container may have: FRAGILE, MODERATE, or STURDY
 *
 * @author Prattusha Biswas 114587992, and recitation 02
 */
public enum CargoStrength {
    FRAGILE("F"), MODERATE("M"), STURDY("S");

    private String strength;
    CargoStrength(String strength) {
        this.strength = strength;
    }

    public String getStrength() {
        return strength;
    }

}