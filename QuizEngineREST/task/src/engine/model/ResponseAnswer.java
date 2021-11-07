package engine.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseAnswer {

    public static final ResponseAnswer SUCCESS_TRUE =
            new ResponseAnswer(true, "Congratulations, you're right!");
    public static final ResponseAnswer SUCCESS_FALSE =
            new ResponseAnswer(false, "Wrong answer! Please, try again.");

    private boolean success;
    private String feedback;
}
