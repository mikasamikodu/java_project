package com.itheima.controller;


import com.itheima.domain.Account;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/param")
@SessionAttributes(value = {"message"})
public class ParamController {

    @RequestMapping(path="/testParam")
    public String testParam(String username,Integer password){
        System.out.println("testParam");
        System.out.println("username:"+username);
        System.out.println("password:"+password);
        return "success";
    }
    @RequestMapping(path="/saveAccount")
    public String saveAccount(Account account){
        System.out.println("saveAccount");
        System.out.println(account);
        return "success";
    }
    @RequestMapping(path="/testRequest")
    public String testRequest(HttpServletRequest request, HttpServletResponse response){
        System.out.println("testRequest");
        System.out.println(response);
        System.out.println(request);
        return "success";
    }
    @RequestMapping(path="/testRequestParam")
    public String testRequestParam(@RequestParam(name="uname") String name){
        System.out.println("testRequestParam");
        System.out.println(name);
        return "success";
    }
    @RequestMapping(path="/testRequestBody")
    public String testRequestBody(@RequestBody String body){
        System.out.println("testRequestBody");
        System.out.println(body);
        return "success";
    }
    @RequestMapping(path="/testPathVariable/{id}")
    public String testPathVariable(@PathVariable(name="id") String body){
        System.out.println("testPathVariable");
        System.out.println(body);
        return "success";
    }
    @RequestMapping(path="/testRequestHeder")
    public String testRequestHeder(@RequestHeader(name="Accept") String body){
        System.out.println("testRequestHeder");
        System.out.println(body);
        return "success";
    }
    @RequestMapping(path="/testCookieValue")
    public String testCookieValue(@CookieValue(name="JSESSIONID") String cookie){
        System.out.println("testCookieValue");
        System.out.println(cookie);
        return "success";
    }
/*
    @RequestMapping(path="/testModelAttribute")
    public String testModelAttribute(User user){
        System.out.println("testModelAttribute");
        System.out.println(user);
        return "success";
    }
*/
/*
    @ModelAttribute
    public User showUser(String name){
        System.out.println("showUser");
        User user = new User();
        user.setName(name);
        user.setBirth(new Date());
        return user;
    }
*/
 /*   @RequestMapping(path="/testModelAttribute")
    public String testModelAttribute(@ModelAttribute("abc") User user){
        System.out.println("testModelAttribute");
        System.out.println(user);
        return "success";
    }
    @ModelAttribute
    public void showUser(String name, Map<String,User> map){
        System.out.println("showUser");
        User user = new User();
        user.setName(name);
        user.setBirth(new Date());
        map.put("abc", user);
    }*/
    @RequestMapping(path="/testSessionAttribute")
    public String testSessionAttribute(Model model){
        System.out.println("testSessionAttribute");
        model.addAttribute("message", "first");//将message存入request域
        return "success";
    }
    @RequestMapping(path="/getSessionAttribute")
    public String getSessionAttribute(ModelMap modelMap){
        System.out.println("getSessionAttribute");
        System.out.println(modelMap.get("message"));
        return "success";
    }
    @RequestMapping(path="/deleteSessionAttribute")
    public String deleteSessionAttribute(SessionStatus status){
        System.out.println("deleteSessionAttribute");
        status.setComplete();
        return "success";
    }
}
