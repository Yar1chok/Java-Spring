package rkis3;

import java.beans.ConstructorProperties;

/**
 * Class football team
 */
public class FootballTeam {
    /**
     * The name of team
     */
    private String nameTeam;

    /**
     * The count of people in football team
     */
    private int countPeopleInTeam;

    /**
     * The goalkeeper of the team
     */
    private Goalkeeper goalkeeper;

    /**
     * The goalkeeper in the football team
     */
    private Fielder fielder;

    /**
     * Getter for getting the name of football team
     * @return The name of team
     */
    public String getNameTeam() {
        return nameTeam;
    }

    /**
     * Setter for assigning a value to a field 'nameTeam'
     * @param nameTeam The name of team
     */
    public void setNameTeam(String nameTeam) {
        if (nameTeam.length() > 0) {
            this.nameTeam = nameTeam;
        }
    }

    /**
     * Getter for getting the count of people in football team
     * @return The count of people in football team
     */
    public int getCountPeopleInTeam() {
        return countPeopleInTeam;
    }

    /**
     * Setter for assigning a value to a field 'countPeopleInTeam'
     * @param countPeopleInTeam The count of people in football team
     */
    public void setCountPeopleInTeam(int countPeopleInTeam) {
        if (countPeopleInTeam > 1 && countPeopleInTeam <= 12) {
            this.countPeopleInTeam = countPeopleInTeam;
        }
    }

    /**
     * Getter for getting the goalkeeper in football team
     * @return rkis3.Goalkeeper in football team
     */
    public Goalkeeper getGoalkeeper() {
        return goalkeeper;
    }

    /**
     * Setter for assigning a value to a field 'goalkeeper'
     * @param goalkeeper rkis3.Goalkeeper in football team
     */
    public void setGoalkeeper(Goalkeeper goalkeeper) {
        this.goalkeeper = goalkeeper;
    }

    /**
     * Getter for getting the forward in football team
     * @return The forward in football team
     */
    public Fielder getFielder() {
        return fielder;
    }

    /**
     * Setter for assigning a value to a field 'fielder'
     * @param fielder The forward in football team
     */
    public void setFielder(Fielder fielder) {
        this.fielder = fielder;
    }

    /**
     * The constructor with params, inputs by user.
     * @param nameTeam The name of team
     * @param countPeopleInTeam The count of people in football team
     * @param goalkeeper rkis3.Goalkeeper in football team
     * @param fielder The forward in football team
     */
    @ConstructorProperties({"nameTeam", "countPeopleInTeam", "goalkeeper", "fielder"})
    public FootballTeam(String nameTeam, int countPeopleInTeam, Goalkeeper goalkeeper, Fielder fielder) {
        this.nameTeam = nameTeam;
        this.goalkeeper = goalkeeper;
        this.fielder = fielder;
        if (countPeopleInTeam > 1 && countPeopleInTeam <= 12) {
            this.countPeopleInTeam = countPeopleInTeam;
        }
    }

    /**
     * The default constructor.
     */
    public FootballTeam() {
    }

    /**
     * Displays the status of the command
     */
    public void teamStatus(){
        int goalsConceded = getGoalkeeper().getGoalsConceded();
        String nameFielder = getFielder().getName();
        if (goalsConceded == 0){
            System.out.println("A team called " + getNameTeam() + " consisting of " + getCountPeopleInTeam() +
                    " people is holding up well and did not miss the ball in the goal. Defender " + nameFielder + " well done");
        } else if (goalsConceded > 1){
            System.out.println("A team called " + getNameTeam() + " consisting of " + getCountPeopleInTeam() +
                    " people is copes not very well and miss the " + goalsConceded + " balls in gates. " +
                    "It's time for Defender " + nameFielder + " to find a replacement.");
        }
    }
}