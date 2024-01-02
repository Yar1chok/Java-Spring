package rkis4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

/**
 * This class utilizes the Spring JDBC framework to interact with the database and uses a JdbcTemplate for executing SQL queries.
 */
@Component
public class WatchDao {
    JdbcTemplate jdbcTemplate;
    /**
     * Sets the data source for the JdbcTemplate to use for database interactions.
     * @param dataSource The data source to be injected.
     */
    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * Retrieves all watch records from the database.
     * @return A list of all watch records in the database.
     */
    public List<Watch> findAll(){
        return jdbcTemplate.query("select * from watch", new
                BeanPropertyRowMapper<>(Watch.class));
    }

    /**
     * Inserts a new watch record into the database.
     * @param watch The watch object to be inserted.
     * @return The number of rows affected (usually 1 if the insertion is successful).
     */
    public int insert(Watch watch){
        return jdbcTemplate.update("insert into watch " + "(brand, type_watch, price, warranty_period, weight) "
                + "values (?,?,?,?,?)",
                watch.getBrand(),
                watch.getTypeWatch(), watch.getPrice(), watch.getWarrantyPeriod(), watch.getWeight());
    }

    /**
     * Deletes a watch record from the database based on the provided ID.
     * @param id The ID of the watch record to be deleted.
     * @return The number of rows affected (usually 1 if the deletion is successful).
     */
    public int deleteById(int id) {
        String sql = "DELETE FROM watch WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    /**
     * Updates a watch record in the database based on the provided watch object.
     * @param watch The watch object with updated values.
     * @return The number of rows affected (usually 1 if the update is successful).
     */
    public int updateById(Watch watch) {
        String sql = "UPDATE watch SET brand = ?, type_watch = ?, price = ?, warranty_period = ?, weight = ? WHERE id = ?";
        return jdbcTemplate.update(sql, watch.getBrand(), watch.getTypeWatch(), watch.getPrice(), watch.getWarrantyPeriod(), watch.getWeight(), watch.getId());
    }

    /**
     * Searches for watch records in the database based on a specific field and its corresponding value.
     * @param fieldName The name of the field to search on.
     * @param value The value to search for in the specified field.
     * @return A list of watch records that match the search criteria.
     */
    public List<Watch> findByField(String fieldName, Object value) {
        String sql = "SELECT * FROM watch WHERE " + fieldName + " = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Watch.class), value);
    }

    /**
     * Retrieves all the IDs of watch records in the database.
     * @return An array of Integer objects containing all the watch IDs.
     */
    public Integer[] getAllIds() {
        String sql = "SELECT id FROM watch";
        List<Integer> idList = jdbcTemplate.queryForList(sql, Integer.class);
        return idList.toArray(new Integer[0]);
    }
}
