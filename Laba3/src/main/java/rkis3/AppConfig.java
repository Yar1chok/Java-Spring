package rkis3;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Spring configuration class that defines settings and binds for the application context
 */
@Configuration
@PropertySource("classpath:config.properties")
public class AppConfig {
    /**
     * A field for storing the value from the settings file representing the number of goals conceded by the goalkeeper
     */
    @Value("${goalkeeper.goalsConceded}")
    private int goalkeeperGoalsConceded;

    /**
     * A field for storing the value from the settings file representing the player's name on the field
     */
    @Value("${fielder.name}")
    private String fielderName;

    /**
     * The method that creates the 'goalkeeper' bin - the goalkeeper.
     * @return New object of class 'goalkeeper'
     */
    @Bean
    public Goalkeeper goalkeeper() {
        return new Goalkeeper();
    }

    /**
     * The method that creates the 'fielder' bin - the goalkeeper.
     * @return New object of class 'fielder'
     */
    @Bean
    public Fielder fielder() {
        return new Fielder();
    }

    /**
     * A method that creates a 'rkis3.FootballTeam' bean - a football team with the specified parameters
     * @param goalkeeper rkis3.Goalkeeper in football team
     * @param fielder The forward in football team
     * @return Returns a new object of the Football Team class with the specified parameters
     */
    @Bean
    public FootballTeam footballTeam(Goalkeeper goalkeeper, Fielder fielder) {
        return new FootballTeam("Spartak", 12, goalkeeper, fielder);
    }
}
