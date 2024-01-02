package rkis3;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Determines the behavior of the forward on the field
 */
public class Fielder implements Player{
    /**
     * The player name
     */
    private String name;

    /**
     * Getter for getting the field 'Name'
     * @return Name of player
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for assigning a value to a field 'Name'
     * @param name Name of player
     */
    public void setName(String name) {
        if (name.length() > 0){
            this.name = name;
        }
    }

    /**
     * The constructor with params, inputs by user.
     * @param name Name of player
     */
    public Fielder(String name) {
        if (name.length() > 0){
            this.name = name;
        }
    }

    /**
     * The default constructor.
     */
    public Fielder(){
        this.name = "Empty";
    }

    /**
     * Redefined rkis3.Player Activity method.
     */
    public void play() {
        System.out.println("The forward is released on the field!");
    }

    /**
     * Redefined break method for the player
     */
    public void relax() {
        System.out.println("The player must be tired, take a 5-minute break!");
    }

    /**
     * Displays a message that the fielder has been initialized
     */
    @PostConstruct
    public void init() {
        System.out.println("rkis3.Fielder initialized.");
    }

    /**
     * Displays a message that the fielder has been destroyed
     */
    @PreDestroy
    public void destroy() {
        System.out.println("rkis3.Fielder destroyed.");
    }
}