package com.sc.act.api.commons.web.base;

/**
 * @ClassName IResultEnum
 * @Description 基础枚举类型 被自定枚举实现
 */
public interface IResultEnum {
    /**
     * @return
     * @Title getCode
     * @Description 前台显示信息码
     */
    String getCode();

    /**
     * @return
     * @Title getMessage
     * @Description 前台显示信息
     */
    String getMessage();

    /**
     * @return
     * @Title getDesc
     * @Description 后台描述
     */
    String getDesc();

}
