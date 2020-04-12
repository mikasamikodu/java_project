package life.majiang.community.service.impl;

import life.majiang.community.dto.PageData;
import life.majiang.community.dto.QuestionDto;
import life.majiang.community.mapper.QuestionMapper;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.Question;
import life.majiang.community.model.User;
import life.majiang.community.model.UserExample;
import life.majiang.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired(required = false)
    private QuestionMapper questionMapper;

    @Autowired(required = false)
    private UserMapper userMapper;

    @Override
    public void saveOrUpdateQuestion(Question question) {
        question.setGmtModified(question.getGmtCreate());
        if(question.getId()!=null){
            questionMapper.updateQuestion(question);
        }else{
            question.setGmtCreate(System.currentTimeMillis());
            questionMapper.insert(question);
        }
    }

    public PageData findPageData(Integer pageNum, Integer pageSize){
        Integer count = questionMapper.count(null);
        PageData data = new PageData();
        data.setPageCount(count);
        data.setPageSize(pageSize);
        data.setPages(data.setThePages(pageNum, count, pageSize));
        List<QuestionDto> questionDtos = questionMapper.findAll((data.getPageNum()-1)*pageSize, pageSize, null);
        for(QuestionDto questionDto: questionDtos){
            UserExample example = new UserExample();
            example.createCriteria().andIdEqualTo(questionDto.getCreatorId());
            User user = userMapper.selectByExample(example).get(0);
            questionDto.setUser(user);
        }
        data.setQuestionDtos(questionDtos);
        return data;
    }

    @Override
    public PageData findPageDataById(Integer pageNum, Integer pageSize, Integer id) {
        Integer count = questionMapper.count(id);
        PageData data = new PageData();
        data.setPageCount(count);
        data.setPageSize(pageSize);
        data.setPages(data.setThePages(pageNum, count, pageSize));
        List<QuestionDto> questionDtos = questionMapper.findAll((data.getPageNum()-1)*pageSize, pageSize, id);
        data.setQuestionDtos(questionDtos);
        return data;
    }

    @Override
    public PageData findQuestionById(Integer id) {
        List<QuestionDto> questionDtos = questionMapper.findById(id);
        UserExample example = new UserExample();
        example.createCriteria().andIdEqualTo(questionDtos.get(0).getCreatorId());
        User user = userMapper.selectByExample(example).get(0);
        questionDtos.get(0).setUser(user);
        PageData data = new PageData();
        data.setQuestionDtos(questionDtos);
        return data;
    }
}
