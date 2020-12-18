package life.majiang.community.dto;

import life.majiang.community.exception.CustomErrorCode;
import life.majiang.community.exception.CustomException;
import lombok.Data;

@Data
public class ResultDto {

    private Integer code;
    private String message;

    public static ResultDto errorOf(Integer code, String message){
        ResultDto dto = new ResultDto();
        dto.setCode(code);
        dto.setMessage(message);
        return dto;
    }

    public static ResultDto errorOf(CustomErrorCode errorCode) {
        return errorOf(errorCode.getCode(), errorCode.getMessage());
    }

    public static ResultDto okOf() {
        ResultDto dto = new ResultDto();
        dto.setCode(200);
        dto.setMessage("请求成功");
        return dto;
    }

    public static ResultDto errorOf(CustomException ex) {
        return errorOf(ex.getCode(), ex.getMessage());
    }
}
