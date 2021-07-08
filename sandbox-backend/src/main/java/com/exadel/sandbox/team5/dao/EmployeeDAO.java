package com.exadel.sandbox.team5.dao;

import com.exadel.sandbox.team5.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeDAO extends CommonRepository<Employee> {

    Employee getByLogin(String login);

    @Query(value = """
            SELECT new java.lang.String(e.email) FROM Employee e
                JOIN e.subscriptions s
            WHERE :category IN (s.id)
            GROUP BY e.id
            """)
    List<String> getMailsBySubscriptions(@Param("category") Long categoryId);
}
