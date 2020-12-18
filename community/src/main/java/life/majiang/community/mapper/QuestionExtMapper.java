package life.majiang.community.mapper;

import life.majiang.community.model.Question;
import life.majiang.community.model.QuestionExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface QuestionExtMapper {
//    更新阅读数
    void updateViewCount(Question question);
//    更新评论数
    void updateCommentCount(Question question);
}