package life.majiang.community.service;

import life.majiang.community.dto.PageData;
import life.majiang.community.dto.QuestionDto;
import life.majiang.community.model.Question;
import org.springframework.ui.Model;

import java.util.List;

public interface QuestionService {
//    保存问题内容
    void saveOrUpdateQuestion(Question question);
//查询首页问题列表
    PageData findPageData(Integer pageNum, Integer pageSize);
//    查询个人首页问题列表
    PageData findPageDataById(Integer pageNum, Integer pageSize, Long id);

//    查询单个问题信息及相关的人员信息
    PageData findQuestionById(Long id);

    void addViewCount(Long id);
}
