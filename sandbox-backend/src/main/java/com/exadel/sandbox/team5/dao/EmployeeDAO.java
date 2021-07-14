package com.exadel.sandbox.team5.dao;

import com.exadel.sandbox.team5.entity.Employee;
import com.exadel.sandbox.team5.util.Notification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeDAO extends CommonRepository<Employee> {

    Employee getByLogin(String login);

    @Query(value = """
            SELECT new com.exadel.sandbox.team5.util.Notification(e.email, d.id, d.name)  FROM Employee e
                JOIN e.subscriptions c
                JOIN Discount d ON d.category.id=c.id 
            WHERE d.isNew=true AND e.email IS NOT null
                GROUP BY e.email,d.id,d.name
            """)
    List<Notification> getNotificationData();

    @Query(value = """
            SELECT new java.lang.String(e.email) FROM Employee e
                WHERE e.email IS NOT null
            """)
    List<String> getAllEmails();
}
