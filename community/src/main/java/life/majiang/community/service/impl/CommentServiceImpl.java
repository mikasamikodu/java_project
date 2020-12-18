package life.majiang.community.service.impl;

import life.majiang.community.dto.CommentAndUserDto;
import life.majiang.community.enums.CommentTypeEnum;
import life.majiang.community.exception.CustomErrorCode;
import life.majiang.community.exception.CustomException;
import life.majiang.community.mapper.CommentMapper;
import life.majiang.community.mapper.QuestionExtMapper;
import life.majiang.community.mapper.QuestionMapper;
import life.majiang.community.model.Comment;
import life.majiang.community.model.CommentExample;
import life.majiang.community.model.Question;
import life.majiang.community.model.User;
import life.majiang.community.service.CommentService;
import life.majiang.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private QuestionExtMapper questionExtMapper;

    @Autowired
    private UserService userService;

    @Override
    @Transactional
    public void saveComment(Comment comment) {
        Long parentId = comment.getParentId();
        if(parentId == null || parentId == 0){
//           判断评论所属问题是否存在，不存在就抛出异常
            throw new CustomException(CustomErrorCode.TARGET_PARAM_NOT_FOUND);
        }

        Integer type = comment.getType();
        if(type == null || !CommentTypeEnum.isExist(type)){
//            判断评论所属类型
            throw new CustomException(CustomErrorCode.TYPE_PARAM_WRONG);
        }

        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(comment.getGmtCreate());
        if (type == CommentTypeEnum.COMMENT.getType()){
//          这个评论是回复评论的，属于二级评论
            Comment comment1 = commentMapper.selectByPrimaryKey(comment.getParentId());
            if (comment1 == null){
                throw new CustomException(CustomErrorCode.COMMENT_NOT_FOUND);
            }
        }else{
//            这个评论是回复问题的，属于一级评论
            Question question = questionMapper.selectByPrimaryKey(comment.getParentId());
            if (question == null){
                throw new CustomException(CustomErrorCode.COMMENT_NOT_FOUND);
            }
            question.setCommentCount(1);
            questionExtMapper.updateCommentCount(question);
        }
       commentMapper.insertSelective(comment);
    }

    @Override
    public List<Comment> findById(Long id, CommentTypeEnum typeEnum) {
        CommentExample example = new CommentExample();
        example.createCriteria().andParentIdEqualTo(id).andTypeEqualTo(typeEnum.getType());
        example.setOrderByClause("gmt_create desc");
        return commentMapper.selectByExample(example);
    }

    public List<CommentAndUserDto> getCommentList(Long id, CommentTypeEnum typeEnum){
        List<CommentAndUserDto> commentAndUserDtos = new ArrayList<>();
        List<Comment> comments = findById(id, typeEnum);
        Map<Long, User> users = new HashMap();
        for (Comment comment : comments) {
//            通过每条评论的创建者id找出创建者信息
            User user = users.get(comment.getParentId());
            if(user == null){
                User user1 = userService.findById(comment.getCommentor());
                users.put(user1.getId(), user1);
                user = user1;
            }
            CommentAndUserDto commentAndUserDto = new CommentAndUserDto();
            commentAndUserDto.setComment(comment);
            commentAndUserDto.setUser(user);
            commentAndUserDtos.add(commentAndUserDto);
        }
        return commentAndUserDtos;
    }
}
