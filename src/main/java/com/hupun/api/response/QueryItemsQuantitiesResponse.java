/*
@(#)QueryItemsQuantitiesResponse   2019-09-29

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

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import com.hupun.api.BaseResponse;
import com.hupun.api.domain.ERPInventroyResult;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author: Klaus 2019/9/29
 */
public class QueryItemsQuantitiesResponse extends BaseResponse<List<ERPInventroyResult>> {
    private long total;
    private int page;
    private List<ERPInventroyResult> inventories;

    public long getTotal() {
        return total;
    }

    public int getPage() {
        return page;
    }

    public List<ERPInventroyResult> getInventories() {
        return inventories;
    }

    @Override
    protected List<ERPInventroyResult> parseData(String json) {
        inventories = Collections.emptyList();
        try {
            if (json != null && json.length() > 0) {
                ObjectMapper mapper = new ObjectMapper();
              /*  SimpleModule module = new SimpleModule();
                module.addSerializer(Date.class, new DateSerializer());
                mapper.registerModule(module);*/
               /* DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                mapper.setDateFormat(df);*/
                Map<String, Object> map = mapper.readValue(json, Map.class);
                total = mapper.convertValue(map.get("total"), Long.class);
                page = mapper.convertValue(map.get("page"), Integer.class);
                inventories = mapper.convertValue(map.get("inventories"),new TypeReference<List<ERPInventroyResult>>(){});
//                inventories = mapper.convertValue(map.get("inventories"),List.class);
            }
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inventories;
    }
}