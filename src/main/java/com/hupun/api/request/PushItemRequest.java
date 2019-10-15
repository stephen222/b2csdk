/*
@(#)PushItemRequest   2019-09-29

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
package com.hupun.api.request;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hupun.api.BaseRequest;
import com.hupun.api.annotate.RequiredField;
import com.hupun.api.domain.B2CItem;
import com.hupun.api.response.PushItemResponse;
import com.hupun.api.enums.RequestMethod;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 线上商品推送接口请求类
 *
 * @author: Klaus 2019/9/29
 */
public class PushItemRequest implements BaseRequest<PushItemResponse>, Serializable {
    private static final long serialVersionUID = -6623316899311070977L;
    @RequiredField(value = "B2C商品集")
    private List<B2CItem> items;

    public List<B2CItem> getItems() {
        return items;
    }

    public void setItems(List<B2CItem> items) {
        this.items = items;
    }

    @Override
    public String getApiMethodName() {
        return "/items/open";
    }

    @Override
    public Class<PushItemResponse> getResponseClass() {
        return PushItemResponse.class;
    }

    @Override
    public String getVersion() {
        return "v1";
    }

    @Override
    public RequestMethod getMethod() {
        return RequestMethod.POST;
    }

    @Override
    public Map<String, Object> getParams() {
        Map<String, Object> map = new HashMap<String, Object>();
        ObjectMapper mapper = new ObjectMapper();
        try {
            map.put("items", mapper.writeValueAsString(items));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return map;
    }
}