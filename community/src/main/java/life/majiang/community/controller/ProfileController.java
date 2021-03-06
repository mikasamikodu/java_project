package life.majiang.community.controller;

import life.majiang.community.dto.PageData;
import life.majiang.community.model.User;
import life.majiang.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
//处理个人资料中我的问题或我的回复
@Controller
public class ProfileController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/profile/{action}")
    public String toQuestion(@RequestParam(name="pageNum",defaultValue = "1") Integer pageNum,
                             @RequestParam(name="pageSize",defaultValue = "10") Integer pageSize,
                             HttpServletRequest request,@PathVariable("action") String action, Model model){
        if("question".equals(action)){
            model.addAttribute("sectionName", "我的问题");
        }else if("replies".equals(action)){
            model.addAttribute("sectionName", "最新回复");
        }
        PageData data = questionService.findPageDataById(pageNum, pageSize, ((User) request.getSession().getAttribute("user")).getId());
        model.addAttribute("data", data);
        model.addAttribute("action", action);
        return "profile";
    }
}
