package lab1;

import java.util.Objects;

/**
 * The class about lab1.Shoes.
 */
public class Shoes extends Clothes{
    /**
     * The size of lab1.Shoes.
     */
    private int size;
    /**
     * The type of lab1.Shoes.
     */
    private String type;

    /**
     * The default constructor.
     */
    public Shoes() {
        this.size = 0;
        this.type = "Empty";
    }

    /**
     * The constructor with params, inputs by user.
     * @param name The name of thing.
     * @param cost The cost of thing.
     * @param type The size of thing.
     * @param size The type of thing.
     */
    public Shoes(String name, double cost, String type, int size) {
        super(name, cost);
        this.size = size;
        this.type = type;
    }

    /**
     * This getter returns the size of thing.
     * @return The size of thing.
     */
    public int getSize() {
        return size;
    }

    /**
     * This setter check input size of thing and assigns a new value of size thing.
     */
    public void setSize(int size) {
        if (size > 0) {
            this.size = size;
        }
    }

    /**
     * This getter returns the type of thing.
     * @return The type of thing.
     */
    public String getType() {
        return type;
    }

    /**
     * This setter check input type of thing and assigns a new value of type thing.
     */
    public void setType(String type) {
        if (type.length() > 0) {
            this.type = type;
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
        Shoes shoes = (Shoes) o;
        return size == shoes.size && Objects.equals(type, shoes.type);
    }

    /**
     * Return hash of the object.
     * @return Hash.
     */
    @Override
    public int hashCode() {
        return Objects.hash(size, type);
    }

    /**
     * Converts an object to a string
     * @return Object in string format.
     */
    @Override
    public String toString() {
        return String.format("""
                * Size - %s
                * Type - %s""", getSize(), getType());
    }
}
