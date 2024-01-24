package com.quiz.app.services;

import com.quiz.app.daos.QuestionDao;
import com.quiz.app.daos.QuizDao;
import com.quiz.app.models.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@ExtendWith(MockitoExtension.class)
public class QuizServiceTest {

    @Mock
    private QuizDao quizDao;

    @Mock private QuestionDao questionDao;

    @InjectMocks
    private QuizService quizService;

    private Question question;

    private String title;

    private String category;

    private int noQues;

    private static Object answer(InvocationOnMock invocation) {
        return invocation.getArgument(0);
    }


    @BeforeEach
    public void setup(){
        question = Question.builder().id(23).title("Title").option1("o1").option2("o2").option3("o3").option4("o4")
                .category("SQL").answer("o2").build();

        category = "SQL";

        noQues = 1;

        title = "JTest";

    }


    @Test
    void createNewQuiz(){
        List<Question> questions = IntStream.range(0, noQues).mapToObj(i -> question).collect(Collectors.toList());
        Mockito.when(questionDao.giveRandomQuestionsByCategory(Mockito.eq(category), Mockito.eq(noQues))).thenReturn(questions);

        Quiz quiz = Quiz.builder().title(title).questions(questions).build();
        Mockito.when(quizDao.save(quiz)).thenReturn(quiz);

        ResponseEntity<Integer> resp = quizService.createNewQuizByCategory(title, category, noQues);

        Assertions.assertThat(resp).isNotNull();
        Assertions.assertThat(resp.getBody()).isEqualTo(1);
    }

    @Test
    void getUserScore(){
        ArrayList<Response> responses = new ArrayList<Response>();
        responses.add(Response.builder().id(23).response("o2").build());
        UserResponse userResponse = UserResponse.builder().quizId(23).responses(responses).build();

        Quiz  quiz = Quiz.builder().id(23).questions(List.of(question)).build();

        Mockito.when(quizDao.findById(23)).thenReturn(Optional.of(quiz));

        ResponseEntity<Integer> userScore = quizService.getUserScore(userResponse);

        Assertions.assertThat(userScore).isNotNull();
        Assertions.assertThat(userScore.hasBody()).isTrue();
        Assertions.assertThat(userScore.getBody()).isEqualTo(1);
    }

    @Test
    void createNewQuizAcceptingQuestions(){
        //here we do all the magic here bitch

        QuizQuestions quizQuestions = QuizQuestions.builder().title("Quizzes").questions(List.of(question)).build();
        List<Question> questions = quizQuestions.getQuestions();
        Mockito.when(questionDao.saveAll(questions)).thenReturn(questions);

        Quiz quiz = Quiz.builder().title("random").id(1000).questions(questions).build();

        Mockito.when(quizDao.save(quiz)).thenReturn(Mockito.any(Quiz.class));

        ResponseEntity<String> resp = quizService.createNewQuizAcceptingQuestions(quizQuestions);
        //how to check when the transaction has failed lmao ded
        //this is done.

        Assertions.assertThat(resp).isNotNull();
        Assertions.assertThat(resp.hasBody()).isTrue();
        Assertions.assertThat(resp.getBody()).isEqualTo("success");


    }
}
