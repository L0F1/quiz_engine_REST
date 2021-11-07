package engine.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Arrays;

@Entity
@Data
@NoArgsConstructor
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "title")
    @NotBlank(message = "Title is required")
    private String title;

    @Column(name = "text")
    @NotBlank(message = "Text is required")
    private String text;

    @Column(name = "options")
    @NotNull
    @Size(min = 2)
    private String[] options;

    @Column(name = "answer")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Integer[] answer = new Integer[0];

    @ManyToOne
    @JoinColumn(name = "userId")
    @JsonIgnore
    private User user;

    public void setAnswer(Integer[] answer) {
        Arrays.sort(answer);
        this.answer = answer;
    }
}
