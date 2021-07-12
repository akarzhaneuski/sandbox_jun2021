package com.exadel.sandbox.team5.dao;

import com.exadel.sandbox.team5.entity.Params;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ParamsDAO extends CommonRepository<Params> {

    @Modifying
    @Query(value = """
            UPDATE params p SET p.value=:date WHERE p.name='lastExecution';
            """,nativeQuery = true)
    void updateLastExecutionTime(String date);
}
