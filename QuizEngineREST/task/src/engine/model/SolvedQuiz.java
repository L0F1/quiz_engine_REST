package engine.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class SolvedQuiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonIgnore
    private long id;

    @ManyToOne
    @JoinColumn(name = "userId")
    @JsonIgnore
    private User user;

    @JsonProperty("id")
    private long quiz_id;

    @Column(name = "completedAt")
    private Date completedAt;

    public SolvedQuiz(User user, long quiz, Date completedAt) {
        this.user = user;
        this.quiz_id = quiz;
        this.completedAt = completedAt;
    }
}
