package com.exadel.sandbox.team5.dao;

import com.exadel.sandbox.team5.entity.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<TagEntity, Integer> {
}
