package life.majiang.community.controller;

import life.majiang.community.dto.PageData;
import life.majiang.community.service.QuestionService;
import life.majiang.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
//处理首页列表
@Controller
public class IndexController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String greeting(@RequestParam(name="pageNum",defaultValue = "1") Integer pageNum,
                           @RequestParam(name="pageSize",defaultValue = "10") Integer pageSize,
                           HttpServletRequest request, Model model) {
        PageData data = questionService.findPageData(pageNum, pageSize);
        model.addAttribute("data", data);
        return "index";
    }
}
