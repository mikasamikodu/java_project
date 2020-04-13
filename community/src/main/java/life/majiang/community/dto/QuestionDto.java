package life.majiang.community.dto;

import life.majiang.community.model.Question;
import life.majiang.community.model.User;
import lombok.Data;

@Data
public class QuestionDto {

    private Question question;
    private User user;
}
