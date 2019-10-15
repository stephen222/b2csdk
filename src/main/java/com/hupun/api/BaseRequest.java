/*
@(#)BaseRequest   2019-07-24

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

import com.hupun.api.enums.RequestMethod;

import java.util.Map;

/**
 * @author: Klaus 2019/7/24
 */
public interface BaseRequest<T extends BaseResponse> {
    /**
     * 获取api方法路径
     * @return api路径
     */
    String getApiMethodName();

    /**
     * 获取响应类型
     * @return 响应类型
     */
    Class<T> getResponseClass();

    /**
     * 获取版本号
     * @return
     */
   String getVersion();

    /**
     * 获取请求方式
     * @return
     */
    RequestMethod getMethod();

    /**
     * 获取接口入参
     * @return
     */
    Map<String, Object> getParams();

}