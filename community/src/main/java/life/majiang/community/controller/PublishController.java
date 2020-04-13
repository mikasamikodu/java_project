package life.majiang.community.controller;

import life.majiang.community.dto.PageData;
import life.majiang.community.dto.QuestionDto;
import life.majiang.community.model.Question;
import life.majiang.community.model.User;
import life.majiang.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
//处理保存发起的问题
@Controller
public class PublishController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }

    @GetMapping("/publish/{id}")
    public String edit(@PathVariable("id") Integer id, Model model){
        PageData data = questionService.findQuestionById(id);
        Question question = data.getQuestionDtos().get(0).getQuestion();
        model.addAttribute("title", question.getTitle());
        model.addAttribute("description", question.getDescription());
        model.addAttribute("tag", question.getTag());
        model.addAttribute("id", question.getId());
        return "publish";
    }

    @PostMapping("/publish")
    public String savePublish(Question question, HttpServletRequest request,Model model){
        model.addAttribute("title", question.getTitle());
        model.addAttribute("description", question.getDescription());
        model.addAttribute("tag", question.getTag());

        String title = question.getTitle();
        if(title==null||title==""){
            model.addAttribute("error", "标题不能为空");
            return "publish";
        }
        String description = question.getDescription();
        if(description==null||description==""){
            model.addAttribute("error", "问题描述不能为空");
            return "publish";
        }
        String tag = question.getTag();
        if(tag==null||tag==""){
            model.addAttribute("error", "问题标签不能为空");
            return "publish";
        }

        User user = (User)request.getSession().getAttribute("user");
        question.setCreatorId(user.getId());
        questionService.saveOrUpdateQuestion(question);
        return "redirect:/";
    }
}