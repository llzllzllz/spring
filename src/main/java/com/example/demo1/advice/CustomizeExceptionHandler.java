package com.example.demo1.advice;

import com.alibaba.fastjson.JSON;
import com.example.demo1.dto.ResultDto;
import com.example.demo1.exception.CustomizeErrorCode;
import com.example.demo1.exception.CustomizeException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@ControllerAdvice
public class CustomizeExceptionHandler {
    @ExceptionHandler(Exception.class)
    Object handle(HttpServletRequest request, Throwable e, Model model, HttpServletResponse response) throws IOException {
        String contentType = request.getContentType();
        if ("application/json".equals(contentType)){
            ResultDto resultDto;
            if (e instanceof CustomizeException){
                resultDto = ResultDto.errorOf((CustomizeException) e);
            }else{
                resultDto = ResultDto.errorOf(CustomizeErrorCode.SYS_ERROR);
            }
            response.setContentType("application/json");
            response.setStatus(200);
            response.setCharacterEncoding("utf-8");
            PrintWriter writer = response.getWriter();
            writer.write(JSON.toJSONString(resultDto));
            writer.close();
            return null;

        }else{
            if (e instanceof CustomizeException){
                model.addAttribute("message",e.getMessage());
            }else{
                model.addAttribute("message",CustomizeErrorCode.SYS_ERROR.getMessage());
            }
            return new ModelAndView("error");
        }
    }

    private HttpStatus getStatus(HttpServletRequest request){
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null){
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }
}
