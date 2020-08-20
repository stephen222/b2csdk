/*
@(#)GetERPTradeStatusRequest   2019-09-29

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
import com.hupun.api.annotate.RequiredField;
import com.hupun.api.enums.RequestMethod;
import com.hupun.api.response.GetERPTradeStatusResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * 获取订单状态接口请求类
 *
 * @author: Klaus 2019/9/29
 */
public class GetERPTradeStatusRequest implements BaseRequest<GetERPTradeStatusResponse>{
    @RequiredField(value = "线上订单号，多个订单以半角逗号相隔，如”123,456”，最多支持200个订单号")
    private String tradeIDs;
    @RequiredField(value = "店铺类型，B2C平台：100")
    private int shopType = 100;
    @RequiredField(value = "店铺昵称，必须和商品推送中的店铺昵称相同")
    private String shopNick;

    public String getTradeIDs() {
        return tradeIDs;
    }

    public void setTradeIDs(String tradeIDs) {
        this.tradeIDs = tradeIDs;
    }

    public int getShopType() {
        return shopType;
    }

    public void setShopType(int shopType) {
        this.shopType = shopType;
    }

    public String getShopNick() {
        return shopNick;
    }

    public void setShopNick(String shopNick) {
        this.shopNick = shopNick;
    }

    @Override
    public String getApiMethodName() {
        return "/trades/erp/status";
    }

    @Override
    public Class<GetERPTradeStatusResponse> getResponseClass() {
        return GetERPTradeStatusResponse.class;
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
        Map<String, Object> params = new HashMap<>();
        params.put("shop_type", shopType);
        params.put("shop_nick", shopNick);
        params.put("trade_ids", tradeIDs);
        return params;
    }
}