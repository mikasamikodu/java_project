package life.majiang.community.advice;

import com.alibaba.fastjson.JSON;
import life.majiang.community.dto.ResultDto;
import life.majiang.community.exception.CustomErrorCode;
import life.majiang.community.exception.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@ControllerAdvice
public class CustomExceptionHandle {

    @ExceptionHandler(Exception.class)
    ModelAndView handle(HttpServletRequest request, Throwable ex, Model model, HttpServletResponse response){
        String contentType = request.getContentType();
        if ("application/json".equals(contentType)){
//            返回json
            ResultDto resultDto;
            if (ex instanceof CustomException){
                resultDto = ResultDto.errorOf((CustomException) ex);
            }else{
                resultDto = ResultDto.errorOf(CustomErrorCode.SYS_ERROR);
            }
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.setStatus(200);
            try{
                PrintWriter writer = response.getWriter();
                writer.write(JSON.toJSONString(resultDto));
                writer.close();
            }catch (IOException e){

            }
        }else{
//            返回页面
            if(ex instanceof CustomException){
                model.addAttribute("message", ex.getMessage());
            }else{
                model.addAttribute("message", CustomErrorCode.SYS_ERROR.getMessage());
            }
            return new ModelAndView("error");
        }
        return  null;
    }
}
