package life.majiang.community.controller;

import life.majiang.community.dto.AccessTokenDto;
import life.majiang.community.dto.GithubUser;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.User;
import life.majiang.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @Autowired(required = false)
    private UserMapper userMapper;

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
        try {
//得到返回的access_token
            String access_token = githubProvider.getAccessToken(accessTokenDto);
//            使用access_token得到用户数据
            GithubUser githubUser = githubProvider.getUser(access_token);
            if(githubUser!=null){
//                如果得到从github返回的用户信息，将其插入到数据库
                User user = new User();
                user.setAccountId(githubUser.getId().toString());
                user.setName(githubUser.getName());
                user.setToken(UUID.randomUUID().toString());
                user.setGmtCreate(System.currentTimeMillis());
                user.setGmtModified(user.getGmtCreate());
                user.setBio(githubUser.getBio());
                userMapper.insert(user);
//                将用户信息放入cookie,方便在服务器重启时用户也可以不用反复登录
                response.addCookie(new Cookie("token", user.getToken()));
                return "redirect:/";
            }else{
                return "redirect:/";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "index";
    }
}
