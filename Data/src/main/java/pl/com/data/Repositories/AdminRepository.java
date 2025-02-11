package pl.com.data.Repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AdminRepository {
    private final JdbcTemplate jdbcTemplate;

    public AdminRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void performAdminTask(String task) {
        jdbcTemplate.execute(task);
    }
}
