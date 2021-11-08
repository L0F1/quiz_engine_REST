package engine.rest;

import engine.model.*;
import engine.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/quizzes")
public class CustomQuizController {

    private final QuizService quizService;

    @Autowired
    public CustomQuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Quiz> getQuizById(@PathVariable(name = "id") long id) {

        Quiz quiz = quizService.getQuizById(id);
        return quiz == null ? new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                new ResponseEntity<>(quiz, HttpStatus.OK);
    }

    @GetMapping
    public Page<Quiz> getAllQuizzes(@RequestParam(name = "page") int page) {
        return quizService.getAllQuizzes(page);
    }

    @GetMapping("/completed")
    public Page<SolvedQuiz> getSolvedQuizzes(@RequestParam(name = "page") int page) {
        return quizService.getSolvedQuizzes(page);
    }

    @PostMapping
    public Quiz createQuiz(@Valid @RequestBody Quiz quiz) {
        return quizService.saveQuiz(quiz);
    }

    @PostMapping("{id}/solve")
    public ResponseAnswer solveQuiz(@PathVariable("id") long id, @RequestBody QuizAnswer answer) {
        return quizService.solveQuiz(id, answer);
    }

    @PutMapping("{id}")
    public ResponseEntity<Quiz> updateQuiz(@PathVariable(name = "id") long id, @Valid @RequestBody Quiz quiz) {
        return quizService.updateQuiz(id, quiz);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteQuiz(@PathVariable(name = "id") long id) {
        return quizService.deleteQuiz(id);
    }
}
