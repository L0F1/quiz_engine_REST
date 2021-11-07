package engine.service;

import engine.model.*;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface QuizService {

    Quiz getQuizById(Long id);

    Quiz saveQuiz(Quiz quiz);

    Page<Quiz> getAllQuizzes(int page);

    Page<SolvedQuiz> getSolvedQuizzes(int page);

    ResponseAnswer solveQuiz(Long id, QuizAnswer answer);

    ResponseEntity<Quiz> updateQuiz(long id, Quiz quiz);

    ResponseEntity<Object> deleteQuiz(long id);
}
