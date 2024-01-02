package rkis3;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Determines the behavior of the goalkeeper on the field
 */
public class Goalkeeper implements Player{
    /**
     * The number of goals conceded by the goalkeeper
     */
    private int goalsConceded;

    /**
     * Getter for getting the number of goals conceded
     * @return The number of goals conceded by the goalkeeper
     */
    public int getGoalsConceded() {
        return goalsConceded;
    }

    /**
     * Setter for assigning a value to a field 'goalsConceded'
     * @param goalsConceded The number of goals conceded by the goalkeeper
     */
    public void setGoalsConceded(int goalsConceded) {
        if (goalsConceded >= 0){
            this.goalsConceded = goalsConceded;
        }
    }

    /**
     * The constructor with params, inputs by user.
     * @param goalsConceded The number of goals conceded by the goalkeeper
     */
    public Goalkeeper(int goalsConceded) {
        if (goalsConceded >= 0){
            this.goalsConceded = goalsConceded;
        }
    }

    /**
     * The default constructor.
     */
    public Goalkeeper(){
        this.goalsConceded = 0;
    }

    /**
     * Redefined rkis3.Player Activity method.
     */
    public void play() {
        System.out.println("The goalkeeper is released on the field!");
    }

    /**
     * Redefined break method for the player
     */
    public void relax() {
        if (getGoalsConceded() >= 2) {
            System.out.println("It's time for goalkeeper to get some rest, you'll be replaced!");
        } else {
            System.out.println("The goalkeeper didn't miss the ball, he's good!");
        }
    }

    /**
     * Displays a message that the goalkeeper has been initialized
     */
    @PostConstruct
    public void init() {
        System.out.println("rkis3.Goalkeeper initialized.");
    }

    /**
     * Displays a message that the goalkeeper has been destroyed
     */
    @PreDestroy
    public void destroy() {
        System.out.println("rkis3.Goalkeeper destroyed.");
    }
}
