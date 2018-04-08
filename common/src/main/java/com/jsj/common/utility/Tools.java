package com.jsj.common.utility;

import java.util.UUID;

/**
 * Created by jinshouji on 2018/4/3.
 * 综合工具杂项
 */
public class Tools {

    /**
     * 得到32位的唯一码
     * @return
     */
    public static String get32UUID(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
