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

    @Select("select q.id as id,q.title as title,q.description as description,q.gmt_create as gmt_create, q.gmt_modified as gmt_modified,q.comment_count as comment_count,q.view_count as view_count,q.like_count as like_count,q.tag as tag, u.avatar_url as avatar_url from question q, `user` u where q.creator_id=u.id limit #{pageNum},#{pageSize}")
    List<QuestionDto> findAll(@Param("pageNum") Integer pageNum, @Param("pageSize")Integer pageSize);

    @Select("select count(1) from question")
    Integer count();

    @Select("select count(1) from question where creator_id=#{id}")
    Integer countByUId(@Param("id") Integer id);

    @Select("select q.id as id,q.title as title,q.description as description,q.gmt_create as gmt_create, q.gmt_modified as gmt_modified,q.comment_count as comment_count,q.view_count as view_count,q.like_count as like_count,q.tag as tag, u.avatar_url as avatar_url from question q, `user` u where q.creator_id=u.id and u.id=#{id} limit #{pageNum},#{pageSize}")
    List<QuestionDto> findByUId(@Param("pageNum") Integer pageNum, @Param("pageSize")Integer pageSize, @Param("id") Integer id);
}
