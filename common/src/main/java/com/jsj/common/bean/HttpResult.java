package com.jsj.common.bean;

import lombok.Data;

/**
 * Created by jinshouji on 2018/4/8.
 * 说明：定义 Controller的统一返回结果
 */
@Data
public class HttpResult {

    public static int STATE_SUCCESS = 0;
    public static int STATE_FAIL = 1;
    public static int STATE_UNAUTHORIZED = 401;

    private int state;          //执行状态 0 成功 非0是吧
    private String code;        //错误编码
    private String msg;         //相关信息
    private Object obj;         //相关数据

    //无参构造函数
    public HttpResult() {
    }

    //构造函数1
    public HttpResult(int state, String code, String msg, Object obj) {
        this.state = state;
        this.code = code;
        this.msg = msg;
        this.obj = obj;
    }

    //构造函数2
    public HttpResult(int state, String msg, Object obj) {
        this.state = state;
        this.msg = msg;
        this.obj = obj;
    }

    //构造函数3（成功返回）
    public static HttpResult createSuccess(String msg, Object content) {
        return new HttpResult(STATE_SUCCESS, msg, content);
    }

    //构造函数4（失败返回）
    public static HttpResult createFAIL(String code, String msg, Object content) {
        return new HttpResult(STATE_FAIL, code, msg, content);
    }

    //构造函数5（未授权返回）
    public static HttpResult createUnauthorized(String msg) {
        return new HttpResult(STATE_UNAUTHORIZED, msg, null);
    }
}
