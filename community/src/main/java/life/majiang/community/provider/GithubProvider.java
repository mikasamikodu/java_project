package life.majiang.community.provider;

import com.alibaba.fastjson.JSON;
import life.majiang.community.dto.AccessTokenDto;
import life.majiang.community.dto.GithubUser;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class GithubProvider {

    public String getAccessToken(AccessTokenDto accessTokenDto){
//        向github发送请求，建立连接得到access_token
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        HttpMethod method = HttpMethod.POST;
        headers.setContentType(MediaType.parseMediaType("application/json; charset=utf-8"));
        HttpEntity<AccessTokenDto> entity = new HttpEntity<>(accessTokenDto, headers);
        String url = "https://github.com/login/oauth/access_token";
        ResponseEntity<String> exchange = restTemplate.exchange(url, method, entity, String.class);
        String token = exchange.toString().split(",")[1].split("&")[0].split("=")[1];
        return token;
    }

    public GithubUser getUser(String access_token){
//        通过access_token再次向github发送请求用户数据
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        HttpMethod method = HttpMethod.GET;
        headers.setContentType(MediaType.parseMediaType("application/json; charset=utf-8"));
        Map<String, String> map = new HashMap<>();
        map.put("Authorization", "token OAUTH-TOKEN");
        HttpEntity<Map<String, String>> entity = new HttpEntity<>(map, headers);
        String url = "https://api.github.com/user?access_token="+access_token;
        GithubUser user = restTemplate.exchange(url, method, entity, GithubUser.class).getBody();
        return user;
    }
}
