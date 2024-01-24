package com.quiz.app.controllers;

import java.util.List;
import com.quiz.app.models.QuizQuestions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.quiz.app.models.QuestionWrapper;
import com.quiz.app.models.UserResponse;
import com.quiz.app.services.QuizService;

/**
 * QuizController
 */
@RestController
@RequestMapping(value = "quiz")
public class QuizController {

  @Autowired
  QuizService quizService;

  @GetMapping(value = "/create/category")
  public ResponseEntity<Integer> createNewQuizByCategory(@RequestParam String title, @RequestParam String category,
      @RequestParam int noQues) {

    return quizService.createNewQuizByCategory(title, category, noQues);
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<List<QuestionWrapper>> getQuiz(@PathVariable int id) {
    return quizService.getQuiz(id);
  }

  @PostMapping(value = "/score")
  public ResponseEntity<Integer> getUserScore(@RequestBody UserResponse userResponse) {
    return quizService.getUserScore(userResponse);
  }

  @PostMapping(value = "/create/accept")
  public ResponseEntity<String> createNewQuizAcceptingQuestions(@RequestBody QuizQuestions quizQuestions) {
    return quizService.createNewQuizAcceptingQuestions(quizQuestions);
  }

}
