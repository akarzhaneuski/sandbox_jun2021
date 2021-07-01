package db.migration;
import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class V00000017__modifyPasswordEmployee extends BaseJavaMigration {
    @Override
    public void migrate(Context context) {
        JdbcTemplate jdbcTemplate =
                new JdbcTemplate(new SingleConnectionDataSource(context.getConnection(), true));
        String pas;
        Integer id;
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        for (int i = 1; i < 10; i++) {
            id = i;
            pas = passwordEncoder.encode("pass" + i);
            jdbcTemplate.update("UPDATE employee SET password = ? WHERE id = ?", pas, id);
        }
    }
}
