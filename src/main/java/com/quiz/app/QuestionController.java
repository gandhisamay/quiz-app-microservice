package com.quiz.app;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuestionController {
  @GetMapping("/questions")
  public String getAllQuestions() {
    return "Lets see if this basic setup works";
  }
}
