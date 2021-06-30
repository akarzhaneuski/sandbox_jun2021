package com.exadel.sandbox.team5.dao;

import com.exadel.sandbox.team5.entity.Tag;
import com.exadel.sandbox.team5.util.PairSL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagDAO extends JpaRepository<Tag, Long> {
}
