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
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class PublishController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }

    @PostMapping("/publish")
    public String savePublish(Question question, HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("user");
        question.setCreatorId(user.getId());
        questionService.saveQuestion(question);
        return "redirect:/";
    }
}