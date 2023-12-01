package cn.edu.nju.se.npmdependency.vo;


import cn.edu.nju.se.npmdependency.enums.ErrorCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultVO<T> implements Serializable {

    private static ErrorCodeEnum success = ErrorCodeEnum.SUCCESS;
    private static ErrorCodeEnum failure = ErrorCodeEnum.SYSTEM_EXECUTION_ERROR;
    private String code;

    private String msg;

    private T result;

    public static  <T> ResultVO<T> buildSuccess() {
        return new ResultVO<T>(success.getCode(), success.getDescription(), null);
    }

    public static <T> ResultVO<T> buildSuccess(T result) {
        return new ResultVO<T>(success.getCode(), success.getDescription(), result);
    }

    public static <T> ResultVO<T> buildFailure(ErrorCodeEnum error) {
        return new ResultVO<T>(error.getCode(),error.getDescription(), null);
    }

    // 默认错误，返回信息
    public static <T> ResultVO<T> buildFailure(String msg) {
        return new ResultVO<T>(failure.getCode(),msg, null);
    }
}
