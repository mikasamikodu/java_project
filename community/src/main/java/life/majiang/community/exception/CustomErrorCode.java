package life.majiang.community.exception;

public enum CustomErrorCode implements ICustomErrorCode {
    QUESTION_NOT_FOUND(2001, "你找的问题不在了，要不要换一个试试"),
    TARGET_PARAM_NOT_FOUND(2002, "未选中任何评论或回复，"),
    NO_LOGIN(2003, "你还未登录，无法进行评论。请先登录"),
    SYS_ERROR(2004, "页面请求出现异常"),
    TYPE_PARAM_WRONG(2005, "评论所属类型错误或不存在"),
    COMMENT_NOT_FOUND(2006, "你找的评论不在了，要不要换一个试试"),
    COMMENT_IS_EMPTY(2007, "评论不能为空"),
    ;

    private String message;
    private Integer code;

    CustomErrorCode(Integer code, String message){
        this.message = message;
    }
    
    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }
}
