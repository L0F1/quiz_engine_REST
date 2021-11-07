package engine.service;

import engine.model.*;
import engine.repository.QuizRepository;
import engine.repository.SolvedQuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.Arrays;
import java.util.Date;

@Service
public class QuizServiceImpl implements QuizService {

    private final QuizRepository quizRepository;
    private final SolvedQuizRepository solvedQuizRepository;
    private final UserService userService;
    private static final int pageSize = 10;

    @Autowired
    public QuizServiceImpl(QuizRepository quizRepository,
                           SolvedQuizRepository solvedQuizRepository,
                           UserService userService) {

        this.quizRepository = quizRepository;
        this.solvedQuizRepository = solvedQuizRepository;
        this.userService = userService;
    }

    @Override
    public Quiz getQuizById(Long id) {
        return quizRepository.findQuizById(id);
    }

    @Override
    public Quiz saveQuiz(Quiz quiz) {

        quiz.setUser(userService.getCurrentAuthUser());
        return quizRepository.save(quiz);
    }

    @Override
    public Page<Quiz> getAllQuizzes(int page) {

        Pageable paging = PageRequest.of(page, pageSize);
        return quizRepository.findAll(paging);
    }

    @Override
    public Page<SolvedQuiz> getSolvedQuizzes(int page) {

        Pageable paging = PageRequest.of(page, pageSize);
        return solvedQuizRepository
                .findAllByUserIdOrderByTimeDesc(userService.getCurrentAuthUser().getId(), paging);
    }

    @Override
    public ResponseAnswer solveQuiz(Long id, QuizAnswer answer) {

        Quiz quiz = quizRepository.findQuizById(id);
        if (quiz == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        // if answers provided in different order
        Integer[] requestAnswer = answer.getAnswer();
        Arrays.sort(requestAnswer);

        if (Arrays.equals(quiz.getAnswer(), requestAnswer)) {
            solvedQuizRepository.save(new SolvedQuiz(userService.getCurrentAuthUser(),
                    quiz.getId(), new Date()));
            return ResponseAnswer.SUCCESS_TRUE;
        }
        else return ResponseAnswer.SUCCESS_FALSE;
    }

    @Override
    public ResponseEntity<Quiz> updateQuiz(long id, Quiz quiz) {

        Quiz oldQuiz = quizRepository.findQuizById(id);
        User currentUser = userService.getCurrentAuthUser();

        if (oldQuiz != null) {
            if (oldQuiz.getUser().getEmail().equals(currentUser.getEmail())) {
                quiz.setId(oldQuiz.getId());
                return new ResponseEntity<>(quizRepository.save(quiz), HttpStatus.OK);
            }
            else return ResponseEntity.status(403).build();
        }
        else return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<Object> deleteQuiz(long id) {

        Quiz quiz = quizRepository.findQuizById(id);
        User currentUser = userService.getCurrentAuthUser();

        if (quiz != null) {
            if (quiz.getUser().getEmail().equals(currentUser.getEmail())) {
                quizRepository.deleteById(id);
                return ResponseEntity.noContent().build();
            }
            else return ResponseEntity.status(403).build();
        }
        else return ResponseEntity.notFound().build();
    }
}
