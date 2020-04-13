package life.majiang.community.controller;

import life.majiang.community.dto.PageData;
import life.majiang.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/question/{id}")
    public String reply(@PathVariable("id") Integer id, Model model){
        questionService.addViewCount(id);
        PageData data = questionService.findQuestionById(id);
        model.addAttribute("data", data);
        return "question";
    }
}
