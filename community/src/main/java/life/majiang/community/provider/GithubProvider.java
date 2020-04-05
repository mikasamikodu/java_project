package life.majiang.community.provider;

import com.alibaba.fastjson.JSON;
import life.majiang.community.dto.AccessTokenDto;
import life.majiang.community.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GithubProvider {

    public String getAccessToken(AccessTokenDto accessTokenDto){
//        向github发送请求，建立连接得到access_token
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(JSON.toJSONString(accessTokenDto), mediaType);
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try{
            Response response = client.newCall(request).execute();
            String message = response.body().string();
            String access_token = message.split("&")[0].split("=")[1];
            return access_token;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public GithubUser getUser(String access_token){
//        通过access_token再次向github发送请求用户数据
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token="+access_token)
                .build();
        try{
            Response response = client.newCall(request).execute();
            GithubUser githubUser = JSON.parseObject(response.body().string(), GithubUser.class);
            return githubUser;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
