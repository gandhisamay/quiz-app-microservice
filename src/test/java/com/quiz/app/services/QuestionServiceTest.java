package com.quiz.app.services;

import com.quiz.app.daos.QuestionDao;
import com.quiz.app.models.Question;
import com.quiz.app.models.Quiz;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class QuestionServiceTest {

  @Mock
  private QuestionDao questionDao;

  @InjectMocks
  private QuestionService questionService;

  private Question question;

  @BeforeEach
  public void setup() {
    question = Question.builder().id(23).title("Title").option1("o1").option2("o2").option3("o3").option4("o4")
        .category("SQL").answer("o2").build();
  }

  @Test
  void getAllQuestions() {

    List<Question> questions = IntStream.range(0, 10).mapToObj(i -> question).toList();

    Mockito.when(questionDao.findAll()).thenReturn(questions);

    List<Question> response = questionService.getAllQuestions();

    Assertions.assertThat(response).isNotNull();
    Assertions.assertThat(response.size()).isEqualTo(questions.size());
  }

  @Test
  void isNotNull() {
    Assertions.assertThat(questionService).isNotNull();
  }

  @Test
  void createNewQuestion() {
    Mockito.when(questionDao.save(Mockito.any(Question.class))).thenReturn(Mockito.any(Question.class));

    ResponseEntity<String> resp = questionService.createNewQuestion(question);

    Assertions.assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    Assertions.assertThat(resp.hasBody()).isTrue();
    Assertions.assertThat(resp.getBody()).isEqualTo("success");
  }

  @Test
  void findById() {
      Mockito.when(questionDao.findByQuestionId(question.getId())).thenReturn(question);

      ResponseEntity<Question> resp = questionService.findById(question.getId());

      Assertions.assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.OK) ;
      Assertions.assertThat(resp.hasBody()).isTrue();
      Assertions.assertThat(resp.getBody()).isNotNull();
  }


}
