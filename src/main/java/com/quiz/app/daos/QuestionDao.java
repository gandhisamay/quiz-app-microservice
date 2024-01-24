package com.quiz.app.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.quiz.app.models.Question;

/**
 * QuestionDao
 */
//@Repository
public interface QuestionDao extends JpaRepository<Question, Integer> {

  @Query(value = "SELECT * FROM questions WHERE id=?1", nativeQuery = true)
  Question findByQuestionId(int id);

  @Query(value = "SELECT * FROM questions WHERE category=?1 ORDER BY RANDOM() LIMIT ?2", nativeQuery = true)
  public List<Question> giveRandomQuestionsByCategory(String category, int noQues);

}
