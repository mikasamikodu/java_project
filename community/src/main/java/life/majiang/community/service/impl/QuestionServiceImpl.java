package life.majiang.community.service.impl;

import life.majiang.community.dto.PageData;
import life.majiang.community.dto.QuestionDto;
import life.majiang.community.mapper.QuestionMapper;
import life.majiang.community.model.Question;
import life.majiang.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired(required = false)
    private QuestionMapper questionMapper;

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
        List<QuestionDto> questions = questionMapper.findAll((data.getPageNum()-1)*pageSize, pageSize);
        data.setQuestions(questions);
        return data;
    }

    @Override
    public PageData findPageDataById(Integer pageNum, Integer pageSize, Integer id) {
        Integer count = questionMapper.countByUId(id);
        PageData data = new PageData();
        data.setPageCount(count);
        data.setPageSize(pageSize);
        data.setPages(data.setThePages(pageNum, count, pageSize));
        List<QuestionDto> questions = questionMapper.findByUId((data.getPageNum()-1)*pageSize, pageSize, id);
        data.setQuestions(questions);
        return data;
    }
}
