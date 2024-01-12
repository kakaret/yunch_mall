package com.zrrd.yunchmall.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 工具类，作用是封装请求的响应结果
 * @param <T>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseResult<T> {
    private Integer code;//响应状态码
    private String message;//响应消息文本
    private T data;//查询时将查询结果进行封装

    public ResponseResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}