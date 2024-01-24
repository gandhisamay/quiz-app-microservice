package com.quiz.app.controllers;

import com.quiz.app.models.Question;
import com.quiz.app.services.QuestionService;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(controllers = QuestionController.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
public class QuestionControllerTest{

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private QuestionService questionService;

    private Question question;

    @BeforeEach
    public void setup(){
        question = Question.builder().id(23).title("Title").option1("o1").option2("o2").option3("o3").option4("o4")
                .category("SQL").answer("o2").build();
    }

    @Test
    void getAllQuestions() throws Exception{

        Question mockedQuestion = Mockito.mock(Question.class);

        System.out.println(mockedQuestion.toString());
        List<Question> questions = IntStream.range(0, 5).mapToObj(i -> mockedQuestion).collect(Collectors.toList());
        Mockito.when(questionService.getAllQuestions()).thenReturn(questions);

       ResultActions response = mockMvc.perform(get("/question/all"));
       response.andExpect(MockMvcResultMatchers.status().isOk());

    }
}