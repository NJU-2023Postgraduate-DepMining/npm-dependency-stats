package cn.edu.nju.se.npmdependency.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 *
 * @author   fanyanpeng
 * @date 2023/4/6 2:22
 * 错误码，错误描述
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ErrorCodeEnum {
    SUCCESS("00000","成功"),
    SYSTEM_EXECUTION_ERROR("B0001","系统执行出错")
    ;

    private String code;
    private String description;


}


