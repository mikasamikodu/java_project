package life.majiang.community.dto;

import lombok.Data;

@Data
public class AccessTokenDto {
//封装请求github的参数
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;

}
