package lab1;

import java.util.Objects;

/**
 * The class about lab1.Overcoat.
 */
public class Overcoat extends Clothes{
    /**
     * The season of overcoat.
     */
    private String season;
    /**
     * The weight of overcoat.
     */
    private double weight;

    /**
     * The default constructor.
     */
    public Overcoat() {
        this.weight = 0.0;
        this.season = "Empty";
    }

    /**
     * The constructor with params, inputs by user.
     * @param name The name of thing.
     * @param cost The cost of thing.
     * @param season The season of thing.
     * @param weight The weight of thing.
     */
    public Overcoat(String name, double cost, String season, double weight) {
        super(name, cost);
        this.season = season;
        this.weight = weight;
    }

    /**
     * This getter returns the season of thing.
     * @return The season of thing.
     */
    public String getSeason() {
        return season;
    }

    /**
     * This getter returns the weight of thing.
     * @return The weight of thing.
     */
    public double getWeight() {
        return weight;
    }

    /**
     * This setter check input season of thing and assigns a new value of season thing.
     */
    public void setSeason(String season) {
        if (season.length() > 0) {
            this.season = season;
        }
    }

    /**
     * This setter check input weight of thing and assigns a new value of weight thing.
     */
    public void setWeight(double weight) {
        if (weight > 0.0) {
            this.weight = weight;
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
        Overcoat overcoat = (Overcoat) o;
        return Double.compare(overcoat.weight, weight) == 0 && Objects.equals(season, overcoat.season);
    }

    /**
     * Return hash of the object.
     * @return Hash.
     */
    @Override
    public int hashCode() {
        return Objects.hash(season, weight);
    }

    /**
     * Converts an object to a string
     * @return Object in string format.
     */
    @Override
    public String toString() {
        return String.format("""
                * Season - %s
                * Weight - %s""", getSeason(), getWeight());
    }
}
