package com.example.testtask.controller;

import com.example.testtask.entity.NewsEntity;
import com.example.testtask.entity.NewsTypeEntity;
import com.example.testtask.repository.NewsRepo;
import com.example.testtask.repository.NewsTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/news")
public class NewsController {

    @Autowired
    private NewsTypeRepo newsTypeRepo;
    @Autowired
    private NewsRepo newsRepo;

    //CRUD Типов новости

    // Создание типа новости
    @PostMapping("/type-creation")
    public ResponseEntity newsTypeCreation(@RequestBody NewsTypeEntity newsTypeEntity) {
        try {
            if (newsTypeEntity.getTypeName() == null || newsTypeEntity.getTypeColor() == null) {
                return ResponseEntity.badRequest().body("Данные введены не корректно");
            } else {
                newsTypeRepo.save(newsTypeEntity);
                return ResponseEntity.ok("Тип новости добален");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    // Получение всех типов новостей
    @GetMapping("/type-found")
    public ResponseEntity findAllNewsType() {
        try {
            return ResponseEntity.ok(newsTypeRepo.findAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    // Получение определённого типа новости
    @GetMapping("/type-found/{newsTypeId}")
    public ResponseEntity findNewsType(@PathVariable(value = "newsTypeId") Long newsTypeId) {
        try {
            if (newsTypeId == null) {
                return ResponseEntity.badRequest().body("Данные введены не корректно");
            } else {
                return ResponseEntity.ok(newsTypeRepo.findById(newsTypeId));
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    // Удаление типа новости
    @DeleteMapping("/type-delete/{newsTypeId}")
    public ResponseEntity newsTypeDelete(@PathVariable(value = "newsTypeId") Long newsTypeId) {
        try {
            if (newsTypeId == null) {
                return ResponseEntity.badRequest().body("Данные введены не корректно");
            } else {
                newsTypeRepo.deleteById(newsTypeId);
                return ResponseEntity.ok("Тип новости удалена");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    // Обновление типа новости
    @PutMapping("/type-update/{newsTypeId}")
    public ResponseEntity updateNewsType(@PathVariable(value = "newsTypeId") Long newsTypeId,
                                         @RequestBody NewsTypeEntity updateType){
        try {
            if (newsTypeId == null || updateType.getTypeName() == null || updateType.getTypeColor() == null) {
                return ResponseEntity.badRequest().body("Данные введены не корректно");
            } else {
                NewsTypeEntity newsTypeEntity = newsTypeRepo.findById(newsTypeId).orElseThrow(() -> new NoSuchElementException(""));
                newsTypeEntity.setTypeName(updateType.getTypeName());
                newsTypeEntity.setTypeColor(updateType.getTypeColor());
                newsTypeRepo.save(newsTypeEntity);
                return ResponseEntity.ok("Тип новости обновлён");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    //CRUD Новостей

    // Создание новости
    @PostMapping("/creation/{newsTypeId}")
    public ResponseEntity newsCreation(@PathVariable(value = "newsTypeId") Long newsTypeId,
                                       @RequestBody NewsEntity newsEntity) {
        try {
            if (newsEntity.getNewsName() == null || newsEntity.getShortDescription() == null
                    || newsEntity.getFullDescription() == null || newsTypeId == null) {
                return ResponseEntity.badRequest().body("Данные введены неверно");
            } else {
                NewsTypeEntity newsTypeEntity = newsTypeRepo.findById(newsTypeId).orElseThrow(() -> new NoSuchElementException(""));
                newsEntity.setNewsTypeId(newsTypeEntity);
                newsRepo.save(newsEntity);
                return ResponseEntity.ok("Тип новости добавлен");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    // Обновление новости
    @PutMapping("/update/{newsId}/{newsTypeId}")
    public ResponseEntity newsUpdate(@PathVariable(value = "newsId") Long newsId,
                                     @PathVariable(value = "newsTypeId") Long newsTypeId,
                                     @RequestBody NewsEntity newsUpdate) {
        try {
            if (newsId == null || newsUpdate.getNewsName() == null || newsUpdate.getShortDescription() == null ||
                    newsUpdate.getFullDescription() == null || newsTypeId == null) {
                return ResponseEntity.badRequest().body("Данные введены неверно");
            } else {
                NewsTypeEntity newsTypeEntity = newsTypeRepo.findById(newsTypeId).orElseThrow(() -> new NoSuchElementException(""));
                NewsEntity newsEntity = newsRepo.findById(newsId).orElseThrow(() -> new NoSuchElementException(""));
                newsEntity.setNewsName(newsUpdate.getNewsName());
                newsEntity.setShortDescription(newsUpdate.getShortDescription());
                newsEntity.setFullDescription(newsUpdate.getFullDescription());
                newsEntity.setNewsTypeId(newsTypeEntity);
                newsRepo.save(newsEntity);
                return ResponseEntity.ok("Новость обновлена");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    // Получение новостей определённого типа
    @GetMapping("/type/{newsTypeId}")
    public ResponseEntity getSortByTypeNews(@PathVariable(value = "newsTypeId") Long newsTypeId) {
        try {
            if (newsTypeId == null) {
                return ResponseEntity.badRequest().body("Id типа новости не введён");
            } else {
                List<NewsEntity> listNewsByType = new ArrayList<>();
                Iterable<NewsEntity> listNews = newsRepo.findAll();
                for (Iterator<NewsEntity> it = listNews.iterator(); it.hasNext(); ) {
                    NewsEntity newsEntity = it.next();
                    if (newsEntity.getNewsTypeId().getTypeId() == newsTypeId) {
                        listNewsByType.add(newsEntity);
                    }
                }
                return ResponseEntity.ok(listNewsByType);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    // Удаление новости
    @DeleteMapping("/delete/{newsId}")
    public ResponseEntity newsDelete(@PathVariable(value = "newsId") Long newsId) {
        try {
            if (newsId != null) {
                newsRepo.deleteById(newsId);
                return ResponseEntity.ok("Новость удалена");

            } else {
                return ResponseEntity.badRequest().body("Id новости не введён");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    // Получение всех новостей
    @GetMapping("")
    public ResponseEntity getAllNews() {
        try {
            Iterable<NewsEntity> listNews = newsRepo.findAll();
            return ResponseEntity.ok(listNews);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    // Получение определённой новости
    @GetMapping("/{newsId}")
    public ResponseEntity getNews(@PathVariable(value = "newsId") Long newsId) {

        try {
            if (newsId == null) {
                return ResponseEntity.badRequest().body("Произошла ошибка");
            } else {
                return ResponseEntity.ok(newsRepo.findById(newsId));
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }
}
