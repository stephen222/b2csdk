/*
@(#)PushCategoryResponse   2019-07-24

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
package com.hupun.api.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hupun.api.BaseResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 商品类目推送接口返回类
 *
 * @author: Klaus 2019/7/24
 */
public class PushCategoryResponse extends BaseResponse<List<String>> {

    private static final long serialVersionUID = 3106081516391550802L;
    private List<String> categoryIDs;


    public List<String> getCategoryIDs() {
        return categoryIDs;
    }


    @Override
    public List<String> parseData(String json) {
        categoryIDs = Collections.emptyList();
        if (json != null && json.length() > 0) {
            ObjectMapper mapper = new ObjectMapper();
            try {
                categoryIDs = mapper.readValue(json, List.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return categoryIDs;
    }


}