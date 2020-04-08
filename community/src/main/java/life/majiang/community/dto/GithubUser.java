package life.majiang.community.dto;

import lombok.Data;

@Data
public class GithubUser {
    //封装请求github成功后返回的部分参数
    private String name;
    private Long id;
    private String bio;
    private String avatar_url;
}
