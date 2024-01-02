package lab1;

import java.util.Objects;

/**
 * The class about lab1.Hats.
 */
public class Hats extends Clothes{
    /**
     * The head coverage of lab1.Hats.
     */
    private double headCoverage;
    /**
     * The material of lab1.Hats.
     */
    private String material;

    /**
     * The default constructor.
     */
    public Hats() {
        this.headCoverage = 0.0;
        this.material = "Empty";
    }

    /**
     * The constructor with params, inputs by user.
     * @param name The name of thing.
     * @param cost The cost of thing.
     * @param material The material of thing.
     * @param headCoverage The head coverage of thing.
     */
    public Hats(String name, double cost, String material, double headCoverage) {
        super(name, cost);
        this.material = material;
        this.headCoverage = headCoverage;
    }

    /**
     * This getter returns the head coverage of thing.
     * @return The head coverage of thing.
     */
    public double getHeadCoverage() {
        return headCoverage;
    }

    /**
     * This getter returns the material of thing.
     * @return The material of thing.
     */
    public String getMaterial() {
        return material;
    }

    /**
     * This setter check input head coverage of thing and assigns a new value of head coverage thing.
     */
    public void setHeadCoverage(double headCoverage) {
        if (headCoverage > 0.0) {
            this.headCoverage = headCoverage;
        }
    }

    /**
     * This setter check input material of thing and assigns a new value of material thing.
     */
    public void setMaterial(String material) {
        if (material.length() > 0) {
            this.material = material;
        }
    }

    /**
     * Compares two objects.
     * @param o Compared object.
     * @return Equality object.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Hats hats = (Hats) o;
        return Double.compare(hats.headCoverage, headCoverage) == 0 && Objects.equals(material, hats.material);
    }

    /**
     * Return hash of the object.
     * @return Hash.
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), headCoverage, material);
    }

    /**
     * Converts an object to a string
     * @return Object in string format.
     */
    @Override
    public String toString() {
        return String.format("""
                * Head Coverage - %s
                * Material - %s""", getHeadCoverage(), getMaterial());
    }
}
