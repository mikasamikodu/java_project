package life.majiang.community.controller;

import life.majiang.community.dto.PageData;
import life.majiang.community.dto.QuestionDto;
import life.majiang.community.mapper.QuestionMapper;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.Question;
import life.majiang.community.model.User;
import life.majiang.community.service.QuestionService;
import life.majiang.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private UserService userService;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String greeting(@RequestParam(name="pageNum",defaultValue = "1") Integer pageNum,
                           @RequestParam(name="pageSize",defaultValue = "10") Integer pageSize,
                           HttpServletRequest request, Model model) {
//        在登陆时检查浏览器的cookie中是否有我们自己定义的名字是token的cookie，如果有就到数据库查一下，是否有用户的token与其一致，
//          有就将用户信息放入session,方便前端页面的获取
        PageData data = questionService.findPageData(pageNum, pageSize);
        model.addAttribute("data", data);
        return "index";
    }
}
