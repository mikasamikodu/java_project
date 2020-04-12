package life.majiang.community.controller;

import life.majiang.community.dto.AccessTokenDto;
import life.majiang.community.dto.GithubUser;
import life.majiang.community.provider.GithubProvider;
import life.majiang.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//处理github授权的问题
@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @Autowired
    private UserService userService;

    //    从application.properties中读取相关配置注入到github的请求参数中
    @Value("${github.client.id}")
    private String client_id;
    @Value("${github.client.secret}")
    private String client_secret;
    @Value("${github.redirect.uri}")
    private String redirect_uri;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletResponse response) {
//处理登陆时与github的交互
        AccessTokenDto accessTokenDto = new AccessTokenDto();
        accessTokenDto.setClient_id(client_id);
        accessTokenDto.setClient_secret(client_secret);
        accessTokenDto.setCode(code);
        accessTokenDto.setRedirect_uri(redirect_uri);
        accessTokenDto.setState(state);
//上面是封装请求参数
//得到返回的access_token
        String access_token = githubProvider.getAccessToken(accessTokenDto);
//            使用access_token得到用户数据
        GithubUser githubUser = githubProvider.getUser(access_token);
        if(githubUser!=null){
//                如果得到从github返回的用户信息，将其插入到数据库
            String token = userService.saveOrUpdateUser(githubUser);
//                将用户信息放入cookie,方便在服务器重启时用户也可以不用反复登录
            Cookie cookie = new Cookie("token", token);
            cookie.setPath("/");
            cookie.setMaxAge(7*24*60*60);
            response.addCookie(cookie);

            return "redirect:/";
        }else{
            return "redirect:/";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request,HttpServletResponse response){
        Cookie[] cookies = request.getCookies();//获取cookie数组
        for (Cookie c:cookies) {
            if (c.getName().equals("token")) {
                Cookie cookie = new Cookie(c.getName(), null);
                cookie.setMaxAge(0);
                cookie.setPath("/");
                response.addCookie(cookie);
                request.getSession().removeAttribute("user");
                break;
            }
        }
        return "redirect:/";
    }
}
