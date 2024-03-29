package com.quiz.app.services;

import java.util.List;

import com.quiz.app.daos.QuestionDao;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.quiz.app.models.Question;

/**
 * QuestionService
 */
@Service
public class QuestionService {
  @Autowired
  private QuestionDao questionDao;

    public List<Question> getAllQuestions() {
    return questionDao.findAll();
  }

  public ResponseEntity<String> createNewQuestion(Question question) {
    questionDao.save(question);
    return new ResponseEntity<String>("success", HttpStatus.CREATED);
  }

  public ResponseEntity<Question> findById(int id) {
    return new ResponseEntity<Question>(questionDao.findByQuestionId(id), HttpStatus.OK);
  }
}
