/*
@(#)PushCategoryRequest   2019-07-24

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
import com.hupun.api.BaseRequest;
import com.hupun.api.annotate.RequiredField;
import com.hupun.api.domain.B2CCategory;
import com.hupun.api.response.PushCategoryResponse;
import com.hupun.api.enums.RequestMethod;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 商品类目推送接口请求类
 *
 * @author: Klaus 2019/7/24
 */
public class PushCategoryRequest implements BaseRequest<PushCategoryResponse>,Serializable {

    private static final long serialVersionUID = 1959274237732563349L;
    @RequiredField(value = "B2C商品类目集")
    private List<B2CCategory> categories;

    public PushCategoryRequest(List<B2CCategory> categories) {
        this.categories = categories;
    }
    @Override
    public String getApiMethodName() {
        return "/categories/open";
    }
    @Override
    public Class<PushCategoryResponse> getResponseClass() {
        return PushCategoryResponse.class;
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
            map.put("categories", mapper.writeValueAsString(categories));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return map;
    }

}