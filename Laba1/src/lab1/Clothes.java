package lab1;

import java.util.Objects;

/**
 * The class about lab1.Clothes.
 */
public class Clothes {
    /**
     * The cost of clothes.
     */
    private double cost;
    /**
     * The name of clothes.
     */
    private String name;

    /**
     * The default constructor.
     */
    public Clothes() {
        this.cost = 0.0;
        this.name = "Empty";
    }

    /**
     * The constructor with params, inputs by user.
     * @param name The name of thing.
     * @param cost The cost of thing.
     */
    public Clothes(String name, double cost) {
        this.name = name;
        this.cost = cost;
    }

    /**
     * This setter check input cost of thing and assigns a new value of cost clothes.
     */
    public void setCost(double cost) {
        if (cost > 0.0) {
            this.cost = cost;
        }
    }

    /**
     * This setter check input name of thing and assigns a new value of name clothes.
     */
    public void setName(String name) {
        if (name.length() > 0) {
            this.name = name;
        }
    }

    /**
     * This getter returns the cost of thing.
     * @return The cost of thing.
     */
    public double getCost() {
        return cost;
    }

    /**
     * This getter returns the name of thing.
     * @return The name of thing.
     */
    public String getName() {
        return name;
    }

    /**
     * Compares two objects.
     * @param o Compared object.
     * @return Equality object.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // Проверка не ссылается ли объект по ссылке obj на текущий объект
        if (o == null || getClass() != o.getClass()) return false; // Проверка не передан ли null или объект по ссылке obj другого класса
        Clothes clothes = (Clothes) o; // Создаем класс с нашей ссылкой
        return Double.compare(clothes.cost, cost) == 0 && name.equals(clothes.name); // Сравниваем поля классов.
    }

    /**
     * Return hash of the object.
     * @return Hash.
     */
    @Override
    public int hashCode() {
        return Objects.hash(cost, name);
    }

    /**
     * Converts an object to a string
     * @return Object in string format.
     */
    @Override
    public String toString() {
        return String.format("""
                * Name - %s
                * Cost - %s""", getName(), getCost());
    }
}
