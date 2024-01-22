package com.quiz.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quiz.app.models.Question;
import com.quiz.app.services.QuestionService;

/**
 * QuestionController
 */
@RestController
@RequestMapping(value = "question")
public class QuestionController {

  @Autowired
  QuestionService questionService;

  @GetMapping(value = "/all")
  public List<Question> getAllQuestions() {
    return questionService.getAllQuestions();
  }

  @PostMapping(value = "/new")
  public ResponseEntity<String> createNewQuestion(@RequestBody Question question) {
    return questionService.createNewQuestion(question);
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<Question> findById(@PathVariable int id) {
    return questionService.findById(id);
  }
}
