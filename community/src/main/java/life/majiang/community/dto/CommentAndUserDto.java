package life.majiang.community.dto;

import life.majiang.community.model.Comment;
import life.majiang.community.model.User;
import lombok.Data;

@Data
public class CommentAndUserDto {

    private Comment comment;
    private User user;
}
