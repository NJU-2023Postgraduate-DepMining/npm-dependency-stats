package cn.edu.nju.se.npmdependency.exception;

import cn.edu.nju.se.npmdependency.enums.ErrorCodeEnum;
import lombok.Data;


/**
 *
 * @author fanyanpeng
 * @date 2023/4/8 2:49
 */

@Data
public class ServiceException extends RuntimeException{


    private String message;
    private ErrorCodeEnum errorCodeEnum;
    public ServiceException(String message){
        this.message=message;
    }
    public ServiceException(ErrorCodeEnum errorCodeEnum){
        this.errorCodeEnum=errorCodeEnum;
    }



    public static ServiceException userNameNotFound(){
        return new ServiceException("用户名不存在");
    }


}
