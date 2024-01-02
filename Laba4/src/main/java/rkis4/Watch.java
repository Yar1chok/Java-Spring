package rkis4;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * The class represents a watch entity in the application. It is annotated with @Entity,
 *  indicating that it is a JPA (Java Persistence API) entity that can be stored in a relational database.
 */
@Entity
public class Watch {
    /**
     * The number record of ID in database.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * Field the name of brand watches
     */
    private String brand;
    /**
     * Field the type of watches
     */
    private String typeWatch;
    /**
     * Field the price of watches
     */
    private double price;
    /**
     * Field the warranty period of watches
     */
    private int warrantyPeriod;
    /**
     * Field the weight of watches
     */
    private double weight;

    /**
     * Gets the warranty period of the watch.
     * @return The warranty period in years.
     */
    public int getWarrantyPeriod() {
        return warrantyPeriod;
    }

    /**
     * Sets the warranty period of the watch, ensuring it is greater than zero.
     * @param warrantyPeriod The warranty period in years.
     */
    public void setWarrantyPeriod(int warrantyPeriod) {
        if (warrantyPeriod > 0) {
            this.warrantyPeriod = warrantyPeriod;
        }
    }

    /**
     * Gets the ID of the watch.
     * @return The ID.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the ID of the watch, ensuring it is greater than zero.
     * @param id The ID.
     */
    public void setId(Integer id) {
        if (id > 0) {
            this.id = id;
        }
    }

    /**
     * Gets the brand of the watch.
     * @return The brand name.
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Sets the brand of the watch, ensuring it is not empty.
     * @param brand The brand name.
     */
    public void setBrand(String brand) {
        if (brand.length() > 0) {
            this.brand = brand;
        }
    }

    /**
     * Gets the type of watch.
     * @return The type of watch.
     */
    public String getTypeWatch() {
        return typeWatch;
    }

    /**
     * Sets the type of watch, ensuring it is not empty.
     * @param typeWatch The type of watch.
     */
    public void setTypeWatch(String typeWatch) {
        if (typeWatch.length() > 0) {
            this.typeWatch = typeWatch;
        }
    }

    /**
     * Gets the price of the watch.
     * @return The price.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the price of the watch, ensuring it is greater than zero.
     * @param price The price.
     */
    public void setPrice(double price) {
        if (price > 0) {
            this.price = price;
        }
    }

    /**
     * Gets the weight of the watch.
     * @return The weight.
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Sets the weight of the watch, ensuring it is greater than zero.
     * @param weight The weight.
     */
    public void setWeight(double weight) {
        if (weight > 0) {
            this.weight = weight;
        }
    }

    /**
     * Constructs a watch object with all properties initialized.
     * @param id The ID of the watch.
     * @param brand The brand of the watch.
     * @param typeWatch The type of watch.
     * @param price The price of the watch.
     * @param warrantyPeriod The warranty period in years.
     * @param weight The weight of the watch.
     */
    public Watch(Integer id, String brand, String typeWatch, double price, int warrantyPeriod, double weight) {
        if (id > 0 && price > 0 && weight > 0 && brand.length() > 0 && typeWatch.length() > 0 && warrantyPeriod > 0) {
            this.id = id;
            this.brand = brand;
            this.typeWatch = typeWatch;
            this.price = price;
            this.warrantyPeriod = warrantyPeriod;
            this.weight = weight;
        }
    }

    /**
     * Default constructor
     */
    public Watch() {
        this.id = 0;
        this.brand = "Empty";
        this.typeWatch = "Empty";
        this.price = 0.0;
        this.warrantyPeriod = 0;
        this.weight = 0.0;
    }

    /**
     * Overrides the default method to provide a formatted string representation of a watch object.
     * @return A formatted string representing the watch object's properties.
     */
    @Override
    public String toString() {
        return String.format("""
                * Watch - %d
                * Brand - %s
                * Type of watch - %s
                * Price - %f
                * Warranty Period - %d
                * Weight - %f""", getId(), getBrand(), getTypeWatch(), getPrice(), getWarrantyPeriod(), getWeight());
    }
}