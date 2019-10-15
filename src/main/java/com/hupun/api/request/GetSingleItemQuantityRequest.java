/*
@(#)GetSingleItemQuantityRequest   2019-09-29

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
import com.hupun.api.annotate.ApiField;
import com.hupun.api.annotate.RequiredField;
import com.hupun.api.enums.RequestMethod;
import com.hupun.api.response.GetSingleItemQuantityResponse;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 单笔库存查询接口请求类
 *
 * @author: Klaus 2019/9/29
 */
public class GetSingleItemQuantityRequest implements BaseRequest<GetSingleItemQuantityResponse>,Serializable {
    private static final long serialVersionUID = 9000229946927234687L;
    @RequiredField(value = "店铺类型")
    private int shopType = 100;
    @RequiredField(value = "在ERP中注册的B2C店铺昵称")
    private String shopNick;
    @RequiredField(value = "商品编号，对应商品推送中的itemID")
    private String itemID;
    @ApiField(value = "如果商品含规格，则必填，对应商品推送的中skuID")
    private String skuID;
    @ApiField(value = "ERP中的仓库编码，默认返回所有仓库的库存")
    private String storageCode;


    public String getShopNick() {
        return shopNick;
    }

    public void setShopNick(String shopNick) {
        this.shopNick = shopNick;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getSkuID() {
        return skuID;
    }

    public void setSkuID(String skuID) {
        this.skuID = skuID;
    }

    public String getStorageCode() {
        return storageCode;
    }

    public void setStorageCode(String storageCode) {
        this.storageCode = storageCode;
    }

    @Override
    public String getApiMethodName() {
        return "/inventories/erp/single";
    }

    @Override
    public Class<GetSingleItemQuantityResponse> getResponseClass() {
        return GetSingleItemQuantityResponse.class;
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
//        ObjectMapper mapper = new ObjectMapper();
        map.put("shop_type", shopType);
        map.put("shop_nick", getShopNick());
        map.put("item_id", getItemID());
        map.put("sku_id", getSkuID());
        map.put("storage_code", getStorageCode());
        return map;
    }
}