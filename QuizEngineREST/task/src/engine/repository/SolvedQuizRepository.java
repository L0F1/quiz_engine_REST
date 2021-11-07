package engine.repository;

import engine.model.SolvedQuiz;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolvedQuizRepository extends PagingAndSortingRepository<SolvedQuiz, Long> {

    String query = "SELECT * FROM solved_quiz WHERE user_id = ?1 ORDER BY completed_at DESC";
    String countQuery = "SELECT * FROM solved_quiz";

    @Query(value = query, countQuery = countQuery, nativeQuery = true)
    Page<SolvedQuiz> findAllByUserIdOrderByTimeDesc(long id, Pageable paging);
}