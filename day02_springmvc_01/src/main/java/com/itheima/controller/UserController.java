package com.itheima.controller;

import com.itheima.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/testString")
    public String testString(Model model){
        System.out.println("testString");
        User user = new User();
        user.setName("张三");
        user.setPassword("12312");
        user.setAge(12);
        model.addAttribute("user", user);
        return "success";
    }
    @RequestMapping("/testModelAndView")
    public ModelAndView testModelView(){
        System.out.println("testModelAndView");
        ModelAndView view = new ModelAndView();
        User user = new User();
        user.setName("张三");
        user.setPassword("12312");
        user.setAge(12);
        view.addObject("user", user);
        view.setViewName("success");
        return view;
    }
    @RequestMapping("/testVoid")
    public void testVoid(HttpServletRequest request, HttpServletResponse response) throws Exception{
        System.out.println("testvoid");
        //request.getRequestDispatcher("/WEB-INF/pages/success.jsp").forward(request, response);
        //response.sendRedirect(request.getContextPath()+"/index.jsp");
//response.setCharacterEncoding("UTF-8");
response.setContentType("text/html;charset=UTF-8");
        response.getWriter().print("<h3>张三</h3>");
        return;
    }
    @RequestMapping("/testForward")
    public String testForward() throws Exception{
        System.out.println("testFarword");
        return "redirect:/index.jsp";
//        return "forward:/WEB-INF/pages/success.jsp";
    }

    @RequestMapping("/testAjax")
    public @ResponseBody User testAjax(@RequestBody User user){
        System.out.println("testAjax");
        user.setName("jack");
        user.setAge(10);
        return user;
    }
}
