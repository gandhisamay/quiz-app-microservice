package com.quiz.app.models;

import lombok.Data;

/**
 * Response
 */

@Data
public class Response {

  private int id;
  private String response;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getResponse() {
    return response;
  }

  public void setResponse(String response) {
    this.response = response;
  }
}
