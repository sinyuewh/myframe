package com.jsj.controller;

import com.jsj.common.utility.JTools;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jinshouji on 2018/4/3.
 */

@RestController
@RequestMapping(value="/hello/")
public class HelloWorldController {

    //测试的方法
    @RequestMapping(value="test",method = {RequestMethod.GET,RequestMethod.POST})
    public String test(String name)
    {
        JTools t1=new JTools();
        return "hello,World"+name+t1.getTest();
    }
}
