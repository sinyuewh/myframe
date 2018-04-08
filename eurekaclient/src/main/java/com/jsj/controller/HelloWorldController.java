package com.jsj.controller;

import com.jsj.common.bean.HttpResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jinshouji on 2018/4/8.
 */
@RestController
@RequestMapping(value = "/api/hello/")
public class HelloWorldController {

    @RequestMapping(value="helloworld")
    public HttpResult HelloWorld()
    {
        HttpResult h1=HttpResult.createSuccess("HelloWorld",null);
        return h1;
    }
}
