package com.kzm.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerExceptionHandler {

    private final Logger logger= LoggerFactory.getLogger(this.getClass());

    /*@ExceptionHandler(Exception.class)
    public String handlerException(HttpServletRequest request,Exception e){
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("url",request.getRequestURI());
        map.put("error",e.getMessage());
        request.setAttribute("javax.servlet.error.status_code",500);
        logger.error(map.toString());
        return "forward:error";
    }*/

    @ExceptionHandler(Exception.class)
    public ModelAndView handlerException(HttpServletRequest request, Exception e) throws Exception {
        if(AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class)!=null){
            throw e;
        }
        ModelAndView mv = new ModelAndView();
        mv.addObject("url",request.getRequestURI());
        mv.addObject("error",e.getMessage());
        mv.addObject("exception",e);
        mv.setViewName("error/error");
        logger.error(mv.toString());
        e.printStackTrace();
        return mv;
    }
}
