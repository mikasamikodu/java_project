package life.majiang.community.exception;

public class CustomException extends RuntimeException {
    private String message;

   public CustomException(ICustomErrorCode errorCode){
       this.message = errorCode.getMessage();
   }

   public  String getMessage(){
       return message;
   }
}
