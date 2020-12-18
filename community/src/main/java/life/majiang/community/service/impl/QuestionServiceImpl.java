package life.majiang.community.service.impl;

import life.majiang.community.exception.CustomErrorCode;
import life.majiang.community.dto.PageData;
import life.majiang.community.dto.QuestionDto;
import life.majiang.community.exception.CustomException;
import life.majiang.community.mapper.QuestionExtMapper;
import life.majiang.community.mapper.QuestionMapper;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.Question;
import life.majiang.community.model.QuestionExample;
import life.majiang.community.model.User;
import life.majiang.community.model.UserExample;
import life.majiang.community.service.QuestionService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired(required = false)
    private QuestionMapper questionMapper;

    @Autowired(required = false)
    private QuestionExtMapper questionExtMapper;

    @Autowired(required = false)
    private UserMapper userMapper;

    @Override
    public void saveOrUpdateQuestion(Question question) {
        question.setGmtModified(System.currentTimeMillis());
        if(question.getId()!=null){
            QuestionExample example = new QuestionExample();
            example.createCriteria().andIdEqualTo(question.getId());
            questionMapper.updateByExampleSelective(question, example);
        }else{
            question.setGmtCreate(question.getGmtModified());
            int insert = questionMapper.insertSelective(question);
//            需要检验是否更新成功（是否更新了一条数据）
            if(insert!=1){
                throw new CustomException(CustomErrorCode.QUESTION_NOT_FOUND);
            }
        }
    }
//网站首页列表，查找所有的问题
    public PageData findPageData(Integer pageNum, Integer pageSize){
        Integer count = (int) questionMapper.countByExample(new QuestionExample());
        PageData data = new PageData();
        data.setPageCount(count);
        data.setPageSize(pageSize);
        data.setPages(data.setThePages(pageNum, count, pageSize));
        QuestionExample example = new QuestionExample();
        example.setOrderByClause("gmt_create desc");
        List<Question> questions = questionMapper.selectByExampleWithBLOBsWithRowbounds(example, new RowBounds((data.getPageNum()-1)*pageSize, pageSize));
        data = getData(data, questions);
        return data;
    }

//个人问题列表，查找自己所有的问题
    @Override
    public PageData findPageDataById(Integer pageNum, Integer pageSize, Long id) {
        QuestionExample example = new QuestionExample();
        example.createCriteria().andCreatorIdEqualTo(id);
        Integer count = (int) questionMapper.countByExample(example);
        PageData data = new PageData();
        data.setPageCount(count);
        data.setPageSize(pageSize);
        data.setPages(data.setThePages(pageNum, count, pageSize));
        QuestionExample example1 = new QuestionExample();
        example1.createCriteria().andCreatorIdEqualTo(id);
        example1.setOrderByClause("gmt_create desc");
        List<Question> questions = questionMapper.selectByExampleWithBLOBsWithRowbounds(example1, new RowBounds((data.getPageNum()-1)*pageSize, pageSize));
        data = getData(data, questions);
        return data;
    }

//    查找首页单个问题
    @Override
    public PageData findQuestionById(Long id) {
        List<QuestionDto> questionDtos = new ArrayList<>();
        QuestionDto questionDto = new QuestionDto();
        questionDtos.add(questionDto);

        QuestionExample example1 = new QuestionExample();
        example1.createCriteria().andIdEqualTo(id);
        Question question = questionMapper.selectByExampleWithBLOBs(example1).get(0);
//        这里需要判断question是否为空
        if(question==null){
            throw new CustomException(CustomErrorCode.QUESTION_NOT_FOUND);
        }
        questionDto.setQuestion(question);

        UserExample example = new UserExample();
        example.createCriteria().andIdEqualTo(question.getCreatorId());
        User user = userMapper.selectByExample(example).get(0);
        questionDto.setUser(user);

        PageData data = new PageData();
        data.setQuestionDtos(questionDtos);
        return data;
    }

//    增加阅读数
    @Override
    public void addViewCount(Long id) {
        Question question = new Question();
        question.setId(id);
        question.setViewCount(1);
        questionExtMapper.updateViewCount(question);
    }


    public PageData getData(PageData data, List<Question> questions){
        List<QuestionDto> questionDtos = new ArrayList<>();
        Map<Long, User> userMap = new HashMap<>();
        for(Question question: questions){
            Long creatorId = question.getCreatorId();
            User user = userMap.get(creatorId);
            if (user == null){
                UserExample example = new UserExample();
                example.createCriteria().andIdEqualTo(creatorId);
                User user1 = userMapper.selectByExample(example).get(0);
                userMap.put(user1.getId(), user1);
                user = user1;
            }
            QuestionDto questionDto = new QuestionDto();
            questionDto.setUser(user);
            questionDto.setQuestion(question);
            questionDtos.add(questionDto);
        }
        data.setQuestionDtos(questionDtos);
        return data;
    }
}
