package rkis4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import javax.sql.DataSource;

/**
 * This class defines beans for the data source, JDBC template, and {@code WatchDao} that can be used throughout the application.
 */
@Configuration
@ComponentScan(basePackages = "rkis4")
@PropertySource("classpath:application.properties")
public class SpringConfig {
    @Autowired
    private Environment env;

    /**
     * Defines a data source bean that provides database connection information.
     * @return The configured data source.
     */
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("dataSource.driverClassName"));
        dataSource.setUrl(env.getProperty("dataSource.url"));
        dataSource.setUsername(env.getProperty("dataSource.username"));
        dataSource.setPassword(env.getProperty("dataSource.password"));
        return dataSource;
    }

    /**
     * Defines a JDBC template bean that can be used for executing SQL queries.
     * @return The configured JDBC template.
     */
    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

    /**
     * Defines a bean that can be used to perform database operations related to watches.
     * @return The configured {@code WatchDao}.
     */
    @Bean
    public WatchDao watchDao() {
        return new WatchDao();
    }
}
