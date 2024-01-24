package com.quiz.app.models;

import java.util.List;

// import jakarta.persistence.Entity;
import lombok.Data;

/**
 * UserResponse
 */

@Data
public class UserResponse {

  private int quizId;

  public int getQuizId() {
    return quizId;
  }

  public void setQuizId(int quizId) {
    this.quizId = quizId;
  }

  private List<Response> responses;

  public List<Response> getResponses() {
    return responses;
  }

  public void setResponses(List<Response> responses) {
    this.responses = responses;
  }
}
