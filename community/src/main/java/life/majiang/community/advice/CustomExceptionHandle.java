package life.majiang.community.advice;

import life.majiang.community.exception.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class CustomExceptionHandle {

    @ExceptionHandler(Exception.class)
    ModelAndView handle(HttpServletRequest request, Throwable ex, Model model){
//        HttpStatus status = getStatus(request);
        if(ex instanceof CustomException){
            model.addAttribute("message", ex.getMessage());
        }else{
            model.addAttribute("message", "页面请求出现异常");
        }
        return new ModelAndView("error");
    }

    /*private HttpStatus getStatus(HttpServletRequest request) {
        Integer ststus = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (ststus==null){
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return  HttpStatus.valueOf(ststus);
    }*/
}
