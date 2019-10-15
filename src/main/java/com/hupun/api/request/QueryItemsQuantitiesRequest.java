/*
@(#)QueryItemsQuantities   2019-09-29

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

import com.hupun.api.BaseRequest;
import com.hupun.api.annotate.ApiField;
import com.hupun.api.annotate.RequiredField;
import com.hupun.api.domain.ERPInventroyResult;
import com.hupun.api.enums.RequestMethod;
import com.hupun.api.response.QueryItemsQuantitiesResponse;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 批量获取库存接口请求类
 *
 * @author: Klaus 2019/9/29
 */
public class QueryItemsQuantitiesRequest implements BaseRequest<QueryItemsQuantitiesResponse> {
    @RequiredField(value = "当前查询的页码,默认为 1")
    private int page = 1;
    @RequiredField(value = "每页条数，默认：80，最大值：200")
    private int limit = 80;
    @RequiredField(value = "修改库存的开始时间，格式：yyyy-MM-dd HH:mm:ss")
    private Date start;
    @RequiredField(value = "修改库存的结束时间，格式：yyyy-MM-dd HH:mm:ss")
    private Date end;
    @ApiField(value = "ERP中的仓库编码，默认返回所有仓库的库存")
    private String storageCode;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public String getStorageCode() {
        return storageCode;
    }

    public void setStorageCode(String storageCode) {
        this.storageCode = storageCode;
    }

    @Override
    public String getApiMethodName() {
        return "/inventories/erp";
    }

    @Override
    public Class<QueryItemsQuantitiesResponse> getResponseClass() {
        return QueryItemsQuantitiesResponse.class;
    }

    @Override
    public String getVersion() {
        return "v1";
    }

    @Override
    public RequestMethod getMethod() {
        return RequestMethod.GET;
    }

    @Override
    public Map<String, Object> getParams() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("page", page);
        map.put("limit", limit);
        map.put("start", start);
        map.put("end", end);
        if (storageCode != null) {
            map.put("storage_code", storageCode);
        }
        return map;
    }
}