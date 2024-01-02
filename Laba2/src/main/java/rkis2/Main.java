package rkis2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Program launch point
 */
public class Main {
    /**
     * Entry point to the program.
     * @param args Command line options
     */
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        FootballTeam footballTeam  = context.getBean("footballTeam", FootballTeam.class);
        footballTeam.getGoalkeeper().play();
        footballTeam.getFielder().play();
        System.out.println("------------------------------------------");
        System.out.printf("The goalkeeper missed goals in his own goal : %d\n", footballTeam.getGoalkeeper().getGoalsConceded());
        System.out.println("------------------------------------------");
        System.out.printf("The name of one of the players in the field: %s\n", footballTeam.getFielder().getName());
        System.out.println("------------------------------------------");
        System.out.println("Football team:");
        footballTeam.teamStatus();
        System.out.println("------------------------------------------");
        footballTeam.getGoalkeeper().relax();
        footballTeam.getFielder().relax();
        System.out.println("------------------------------------------");
    }
}