package life.majiang.community.exception;

public enum CustomErrorCode implements ICustomErrorCode {
    QUESTION_NOT_FOUND("你找的问题不在了，要不要换一个试试");

    private String message;
    @Override
    public String getMessage() {
        return message;
    }
    CustomErrorCode(String message){
        this.message = message;
    }
}
