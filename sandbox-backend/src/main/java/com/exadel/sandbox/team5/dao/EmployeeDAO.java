package com.exadel.sandbox.team5.dao;

import com.exadel.sandbox.team5.entity.Discount;
import com.exadel.sandbox.team5.entity.Employee;
import com.exadel.sandbox.team5.util.Notification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeDAO extends CommonRepository<Employee> {

    Optional<Employee> getByLogin(String login);

    @Query(value = """
            SELECT new com.exadel.sandbox.team5.util.Notification(e.email, d.id, d.name)  FROM Employee e
                JOIN e.subscriptions c
                JOIN Discount d ON d.category.id=c.id 
            WHERE d.isSent=false AND e.email IS NOT null
                GROUP BY e.email,d.id,d.name
            """)
    List<Notification> getNotificationData();

    @Query(value = """
            SELECT new java.lang.String(e.email) FROM Employee e
                WHERE e.email IS NOT null
            """)
    List<String> getAllEmails();

    @Query(value = """
            SELECT d FROM Employee e 
            JOIN e.favorites f
            JOIN Discount d ON f.id = d.id
            WHERE e.login LIKE (:login)
            """)
    Page<Discount> getFavorites(@Param("login") String login, Pageable pageable);
}
