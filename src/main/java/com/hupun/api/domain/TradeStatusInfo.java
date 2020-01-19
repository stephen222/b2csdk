/*
@(#)TradeStatusInfo   2019-09-29

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
package com.hupun.api.domain;

/**
 * @author: Klaus 2019/9/29
 */
public class TradeStatusInfo {
    /** 订单号，可能为多个，表示系统已合单*/
    private String tid;

    /** 快递单号*/
    private String waybill;

    /** 系统订单状态，
     * 0：订单审核，
     * 1:打单配货，
     * 2：验货，
     * 3：称重，
     * 4：待发货，
     * 5：财审，
     * 8：已发货，
     * 9：交易成功，
     * 10：交易关闭*/
    private int status;

    /** 快递公司编码*/
    private String express_code;

    /** 快递公司名称*/
    private String express;

    /** 销售税号*/
    private String sale_tax_sn;

    /** 发货类型，
     * 0：快递；
     * 1：EMS；
     * 2：平邮；
     * 9：卖家承担运费（包邮）；
     * 11：虚拟物品；
     * 121：自提；
     * 122：商家自送（门店配送）*/
    private String shippingType;

    /**
     * 系统单号
     */
    private String tradeNo;

    /**
     * 系统快递编码
     */
    private String express_local;

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getWaybill() {
        return waybill;
    }

    public void setWaybill(String waybill) {
        this.waybill = waybill;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getExpress_code() {
        return express_code;
    }

    public void setExpress_code(String express_code) {
        this.express_code = express_code;
    }

    public String getExpress() {
        return express;
    }

    public void setExpress(String express) {
        this.express = express;
    }

    public String getSale_tax_sn() {
        return sale_tax_sn;
    }

    public void setSale_tax_sn(String sale_tax_sn) {
        this.sale_tax_sn = sale_tax_sn;
    }

    public String getShippingType() {
        return shippingType;
    }

    public void setShippingType(String shippingType) {
        this.shippingType = shippingType;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getExpress_local() {
        return express_local;
    }

    public void setExpress_local(String express_local) {
        this.express_local = express_local;
    }
}