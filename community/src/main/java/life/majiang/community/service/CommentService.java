package life.majiang.community.service;

import life.majiang.community.dto.CommentAndUserDto;
import life.majiang.community.enums.CommentTypeEnum;
import life.majiang.community.model.Comment;

import java.util.List;

public interface CommentService {
//保存评论
    void saveComment(Comment comment);
//通过id与评论类型查找评论
    public List<Comment> findById(Long id, CommentTypeEnum typeEnum);
//通过id与评论类型查找评论及评论所属用户
    public List<CommentAndUserDto> getCommentList(Long id, CommentTypeEnum typeEnum);
}
