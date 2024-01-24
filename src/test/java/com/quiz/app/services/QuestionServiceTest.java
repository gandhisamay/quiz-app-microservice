package com.quiz.app.services;

import com.quiz.app.daos.QuestionDao;
import com.quiz.app.models.Question;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class QuestionServiceTest {

    @Mock
    private QuestionDao questionDao;

    @InjectMocks
    private QuestionService questionService;

    @Test
    void getAllQuestions() {
    }

    @Test
    void isNotNull() {
        Assertions.assertThat(questionService).isNotNull();
    }

    @Test
    void createNewQuestion() {
//        ResponseEntity<String> entity = new ResponseEntity<String>("success", HttpStatus.CREATED);
        Mockito.when(questionDao.save(Mockito.any(Question.class))).thenReturn(Mockito.any(Question.class));

        Question question = Question.builder().
                title("Title").
                option1("o1").
                option2("o2").
                option3("o3").
                option4("o4").
                category("SQL").
                answer("o2").build();


        ResponseEntity<String> resp = questionService.createNewQuestion(question);

        Assertions.assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        Assertions.assertThat(resp.hasBody()).isTrue();
        Assertions.assertThat(resp.getBody()).isEqualTo("success");
    }

    @Test
    void findById() {
    }
}