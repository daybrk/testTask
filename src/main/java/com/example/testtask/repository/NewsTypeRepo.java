package com.example.testtask.repository;

import com.example.testtask.entity.NewsTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface NewsTypeRepo extends CrudRepository<NewsTypeEntity, Long> {
}
