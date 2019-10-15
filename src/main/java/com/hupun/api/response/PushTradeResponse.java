/*
@(#)PushTradeResponse   2019-09-29

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
import java.util.Collections;
import java.util.List;

/**
 * @author: Klaus 2019/9/29
 */
public class PushTradeResponse extends BaseResponse<List<String>> {
    private static final long serialVersionUID = 2496798273957778187L;
    private List<String> tradeIDs;

    public List<String> getTradeIDs() {
        return tradeIDs;
    }


    @Override
    public List<String> parseData(String json) {
        tradeIDs = Collections.emptyList();
        if (json != null && json.length() > 0) {
            ObjectMapper mapper = new ObjectMapper();
            try {
                tradeIDs = mapper.readValue(json, List.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return tradeIDs;
    }
}