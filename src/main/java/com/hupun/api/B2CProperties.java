/*
@(#)B2CProperties   2019-07-25

Copyright (c) 2011-2019 杭州湖畔网络技术有限公司 
保留所有权利 
本软件为杭州湖畔网络技术有限公司所有及包含机密信息，须遵守其相关许可证条款进行使用。
Copyright (c) 2011-2019 HUPUN Network Technology CO.,LTD.
All rights reserved.
This software is the confidential and proprietary information of HUPUN
Network Technology CO.,LTD("Confidential Information").  You shall not
disclose such Confidential Information and shall use it only in
accordance with the terms of the license agreement you entered into with HUPUN.
Website：http://www.hupun.com
 */
package com.hupun.api;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author: Klaus 2019/7/25
 */
public class B2CProperties {
    /**
     * 获取属性值
     *
     * @param key 属性名
     * @return 属性值
     */
    public static String getValue(String key){
        return GetValueByKey(file(), key);
    }
    private static String GetValueByKey(String filePath, String key) {
        Properties pps = new Properties();
        try {
            ClassLoader classLoader = B2CProperties.class.getClassLoader();
//            URL resource = classLoader.getResource(filePath);
//            String path = resource.getPath();
            InputStream in = classLoader.getResourceAsStream(filePath);
            pps.load(in);
            String value = pps.getProperty(key);
//            System.out.println(key + " = " + value);
            return value;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
//        GetValueByKey("b2c.properties","test.severURL");
    }
    public static String file(){
        return "com.hupun.api/b2c.properties";
    }
}