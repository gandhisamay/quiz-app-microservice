package com.quiz.app.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.quiz.app.models.Quiz;

/**
 * QuizDao
 */
@Repository
public interface QuizDao extends JpaRepository<Quiz, Integer> {


}
