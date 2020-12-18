package life.majiang.community.controller;

import life.majiang.community.dto.CommentAndUserDto;
import life.majiang.community.dto.PageData;
import life.majiang.community.enums.CommentTypeEnum;
import life.majiang.community.model.Comment;
import life.majiang.community.model.User;
import life.majiang.community.service.CommentService;
import life.majiang.community.service.QuestionService;
import life.majiang.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @GetMapping("/question/{id}")
    public String reply(@PathVariable("id") Long id, Model model){
//        增加浏览数
        questionService.addViewCount(id);
//        查询问题详细信息及问题提出者信息
        PageData data = questionService.findQuestionById(id);
        model.addAttribute("questionDto", data.getQuestionDtos().get(0));
//        查询问题相关评论
//        通过问题id找出所有相关的评论
        List<CommentAndUserDto> commentAndUserDtos = commentService.getCommentList(id, CommentTypeEnum.QUESTION);
        model.addAttribute("commentAndUserDtos", commentAndUserDtos);
        return "question";
    }


}
