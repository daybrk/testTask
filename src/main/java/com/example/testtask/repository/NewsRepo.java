package com.example.testtask.repository;

import com.example.testtask.entity.NewsEntity;
import org.springframework.data.repository.CrudRepository;


public interface NewsRepo extends CrudRepository<NewsEntity, Long> {
}
