package life.majiang.community.mapper;

import life.majiang.community.dto.QuestionDto;
import life.majiang.community.model.Question;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface QuestionMapper {

    @Insert("insert into question (title, description, gmt_create, gmt_modified, tag, creator_id)values(#{title}, #{description}, #{gmtCreate}, #{gmtModified}, #{tag}, #{creatorId})")
    void insert(Question question);

    @Select({"<script>",
            "select * from question ",
            "<when test='id!=null'>",
            " where creator_id=#{id} ",
            "</when>",
            " limit #{pageNum},#{pageSize}",
            "</script>"
    })
    List<QuestionDto> findAll(@Param("pageNum") Integer pageNum, @Param("pageSize")Integer pageSize, @Param("id") Integer id);

    @Select({"<script>",
            "select count(1) from question",
            "<when test='id!=null'>",
            " where creator_id=#{id} ",
            "</when>",
            "</script>"
    })
    Integer count(@Param("id") Integer id);

    @Select("select * from question where id=#{id}")
    List<QuestionDto> findById(@Param("id") Integer id);

//    更新个人问题信息
    @Update("update question set title=#{title},description=#{description},tag=#{tag} where id=#{id}")
    void updateQuestion(Question question);
}
