package life.majiang.community.service.impl;

import life.majiang.community.dto.PageData;
import life.majiang.community.dto.QuestionDto;
import life.majiang.community.mapper.QuestionMapper;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.Question;
import life.majiang.community.model.User;
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
    public void saveQuestion(Question question) {
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());
        questionMapper.insert(question);
    }

    public PageData findPageData(Integer pageNum, Integer pageSize){
        Integer count = questionMapper.count();
        PageData data = new PageData();
        data.setPageCount(count);
        data.setPageSize(pageSize);
        data.setPages(data.setThePages(pageNum, count, pageSize));
        List<QuestionDto> questionDtos = questionMapper.findAll((data.getPageNum()-1)*pageSize, pageSize);
        for(QuestionDto questionDto: questionDtos){
            User user = userMapper.findById(questionDto.getCreatorId());
            questionDto.setUser(user);
        }
        data.setQuestionDtos(questionDtos);
        return data;
    }

    @Override
    public PageData findPageDataById(Integer pageNum, Integer pageSize, Integer id) {
        Integer count = questionMapper.countByUId(id);
        PageData data = new PageData();
        data.setPageCount(count);
        data.setPageSize(pageSize);
        data.setPages(data.setThePages(pageNum, count, pageSize));
        List<QuestionDto> questionDtos = questionMapper.findByUId((data.getPageNum()-1)*pageSize, pageSize, id);
        data.setQuestionDtos(questionDtos);
        return data;
    }

    @Override
    public PageData findQuestionById(Integer id) {
        List<QuestionDto> questionDtos = questionMapper.findById(id);
        User user = userMapper.findById(questionDtos.get(0).getCreatorId());
        questionDtos.get(0).setUser(user);
        PageData data = new PageData();
        data.setQuestionDtos(questionDtos);
        return data;
    }
}
