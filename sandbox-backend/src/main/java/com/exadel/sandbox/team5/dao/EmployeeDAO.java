package com.exadel.sandbox.team5.dao;

import com.exadel.sandbox.team5.entity.Category;
import com.exadel.sandbox.team5.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface EmployeeDAO extends JpaRepository<Employee, Long> {

    Employee getByLogin(String login);

    @Query(value = """
            SELECT new java.lang.String(e.email) FROM Employee e
                JOIN e.subscriptions s
            WHERE :category IN (s.id)
            GROUP BY e.id
            """)
    List<String> getMailsBySubscriptions(@Param("category") Long categoryId);
}
