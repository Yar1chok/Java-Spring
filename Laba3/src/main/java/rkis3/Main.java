package rkis3;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Program launch point
 */
public class Main {
    /**
     * Entry point to the program.
     * @param args Command line options
     */
    public static void main(String[] args) {
        System.out.println("------------------------------------------");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        FootballTeam footballTeam = context.getBean("footballTeam", FootballTeam.class);
        System.out.println("------------------------------------------");
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
        context.close();
        System.out.println("------------------------------------------");
    }
}
