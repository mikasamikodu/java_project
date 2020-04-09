package life.majiang.community.mapper;

import life.majiang.community.dto.QuestionDto;
import life.majiang.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface QuestionMapper {

    @Insert("insert into question (title, description, gmt_create, gmt_modified, tag, creator_id)values(#{title}, #{description}, #{gmtCreate}, #{gmtModified}, #{tag}, #{creatorId})")
    void insert(Question question);

    @Select("select * from question limit #{pageNum},#{pageSize}")
    List<QuestionDto> findAll(@Param("pageNum") Integer pageNum, @Param("pageSize")Integer pageSize);

    @Select("select count(1) from question")
    Integer count();

    @Select("select count(1) from question where creator_id=#{id}")
    Integer countByUId(@Param("id") Integer id);

    @Select("select * from question where creator_id=#{id} limit #{pageNum},#{pageSize}")
    List<QuestionDto> findByUId(@Param("pageNum") Integer pageNum, @Param("pageSize")Integer pageSize, @Param("id") Integer id);

    @Select("select * from question where id=#{id}")
    List<QuestionDto> findById(@Param("id") Integer id);
}
