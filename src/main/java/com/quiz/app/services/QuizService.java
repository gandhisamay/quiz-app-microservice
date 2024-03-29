package com.quiz.app.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.quiz.app.models.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.quiz.app.daos.QuestionDao;
import com.quiz.app.daos.QuizDao;

/**
 * QuizService
 */
@Service
public class QuizService {
    public QuizDao quizDao;
    public QuestionDao questionDao;

    QuizService(QuizDao quizDao, QuestionDao questionDao) {
        this.quizDao = quizDao;
        this.questionDao = questionDao;
    }

    public ResponseEntity<Integer> createNewQuizByCategory(String title, String category, int noQues) {

        List<Question> questions = questionDao.giveRandomQuestionsByCategory(category, noQues);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);


        quizDao.save(quiz);
        return new ResponseEntity<Integer>(1, HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuiz(int id) {

        Optional<Quiz> quiz_o = quizDao.findById(id);

        if (quiz_o.isPresent()) {
            Quiz quiz = quiz_o.get();

            List<QuestionWrapper> questionWrappers = quiz.getQuestions().stream()
                    .map(question -> new QuestionWrapper(question.getId(), question.getTitle(), question.getOption1(),
                            question.getOption2(),
                            question.getOption3(), question.getOption4()))
                    .collect(Collectors.toList());

            return new ResponseEntity<List<QuestionWrapper>>(questionWrappers, HttpStatus.OK);

        } else {
            return new ResponseEntity<List<QuestionWrapper>>(new ArrayList<>(), HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Integer> getUserScore(UserResponse userResponse) {

        Optional<Quiz> quiz_o = quizDao.findById(userResponse.getQuizId());

        if (quiz_o.isPresent()) {
            Quiz quiz = quiz_o.get();
            List<Question> questions = quiz.getQuestions();
            List<Response> responses = userResponse.getResponses();

            int score = IntStream.range(0, questions.size())
                    .filter(i -> questions.get(i).getAnswer().equals(responses.get(i).getResponse())).reduce(0, (total, sum) -> total + 1);

            return new ResponseEntity<Integer>(score, HttpStatus.OK);

        } else {
            return new ResponseEntity<Integer>(-1, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Transactional
    public ResponseEntity<String> createNewQuizAcceptingQuestions(QuizQuestions quizQuestions) {
        List<Question> savedQuestions = questionDao.saveAll(quizQuestions.getQuestions());
        Quiz quiz = Quiz.builder().title(quizQuestions.getTitle()).questions(savedQuestions).build();
        quizDao.save(quiz);

        return new ResponseEntity<String>("success", HttpStatus.CREATED);
    }
}
