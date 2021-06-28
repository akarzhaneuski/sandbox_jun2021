package db.migration;
import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.UUID;

public class V00000008__modifyPasswordEmployee extends BaseJavaMigration {
    @Override
    public void migrate(Context context) throws Exception {
        JdbcTemplate jdbcTemplate =
                new JdbcTemplate(new SingleConnectionDataSource(context.getConnection(), true));
//        jdbcTemplate.execute("ALTER TABLE employee ADD COLUMN someKey VARCHAR(50)");
        String pas;
        String key;
        Integer id;
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        for (int i = 1; i < 10; i++) {
//            key = UUID.randomUUID().toString();
            id = i;
            pas = passwordEncoder.encode("pass" + i);
            jdbcTemplate.update("UPDATE employee SET password = ? WHERE id = ?", pas, id);

        }

    }
}
