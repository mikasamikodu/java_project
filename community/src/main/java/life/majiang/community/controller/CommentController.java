package life.majiang.community.controller;

import life.majiang.community.dto.CommentAndUserDto;
import life.majiang.community.dto.CommentDto;
import life.majiang.community.dto.ResultDto;
import life.majiang.community.enums.CommentTypeEnum;
import life.majiang.community.exception.CustomErrorCode;
import life.majiang.community.exception.CustomException;
import life.majiang.community.model.Comment;
import life.majiang.community.model.User;
import life.majiang.community.service.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/comment")
    public Object addComment(@RequestBody CommentDto commentDto, HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        if (user==null){
            return ResultDto.errorOf(CustomErrorCode.NO_LOGIN);
        }else if(StringUtils.isEmpty(commentDto.getContent().trim()) || commentDto.getContent().trim() == ""){
            return ResultDto.errorOf(CustomErrorCode.COMMENT_IS_EMPTY);
        }
        Comment comment = new Comment();
        comment.setCommentor(user.getId());
        BeanUtils.copyProperties(commentDto, comment);
        commentService.saveComment(comment);
        return  ResultDto.okOf();
    }

    @GetMapping("/comment/{id}")
    public Object childComments(@PathVariable("id") Long id, Model model){
        List<CommentAndUserDto> commentAndUserDtos = commentService.getCommentList(id, CommentTypeEnum.COMMENT);
        model.addAttribute("childCommentDtos", commentAndUserDtos);
        return ResultDto.okOf();
    }
}
