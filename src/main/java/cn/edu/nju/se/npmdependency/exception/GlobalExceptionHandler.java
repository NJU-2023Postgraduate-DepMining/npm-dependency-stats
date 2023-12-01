package cn.edu.nju.se.npmdependency.exception;

import cn.edu.nju.se.npmdependency.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * 全局异常处理器
 * RuntimeException可以直接上抛出的，在此处被捕获
 * 若使用@ControllerAdvice，需要在下列方法前添加：@ResponseBody
 * @author fanyanpeng
 * @date 2023/3/24 2:49
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


    /**
     * 后端错误集中处理中心
     * @author   fanyanpeng
     * @date 2023/4/11 1:08
     * @param e
     * @return cn.seecoder.ai.model.vo.ResultVO<java.lang.String>
     */
    @ExceptionHandler(value = ServiceException.class)
    public ResultVO<String> handleAIExternalException(ServiceException e) {
        e.printStackTrace();
        if(e.getErrorCodeEnum()!=null){
            return ResultVO.buildFailure(e.getErrorCodeEnum());
        }
        return ResultVO.buildFailure(e.getMessage());
    }



    /**
     * 枚举类名称解析异常时会报的错，返回前端
     * @author   fanyanpeng
     * @date 2023/4/15 1:07
     * @param e
     * @return cn.seecoder.ai.model.vo.ResultVO<java.util.List<java.lang.String>>
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResultVO<List<String>> handleHttpMessageNotReadableExceptions(HttpMessageNotReadableException e) {
        String message  = e.getMessage();
        log.info("抛出org.springframework.http.converter.HttpMessageNotReadableException异常：{}",message);
        return ResultVO.buildFailure((message));
    }

}
