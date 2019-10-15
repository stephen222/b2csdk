/*
@(#)B2COrder   2019-09-29

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

import com.hupun.api.annotate.ApiField;
import com.hupun.api.annotate.RequiredField;

import java.io.Serializable;

/**
 * @author: Klaus 2019/9/29
 */
public class B2COrder implements Serializable {
    private static final long serialVersionUID = 4634714771038692588L;
    @RequiredField(value = "第三方交易号")
    private String tradeID;

    @RequiredField(value = "第三方订单号")
    private String orderID;

    @RequiredField(value = "线上商品编号")
    private String itemID;

    @RequiredField(value = "商品标题")
    private String itemTitle;

    @ApiField(value = "外部商家编码，商家自己设定的编码, 尽量填写，ERP通过该编码区分商品 ")
    private String itemCode;

    @RequiredField(value = "线上规格编号")
    private String skuID;

    @RequiredField(value = "规格标题")
    private String skuTitle;

    @ApiField(value = "外部规格编码，商家自己设定的规格编码, 尽量填写，ERP通过该编码区分商品")
    private String skuCode;

    @ApiField(value = "明细状态:1：等待付款；2：等待发货；3：已完成；4：已关闭；5：等待确认；6：已签收；")
    private int status;

    @RequiredField(value = "商品单价")
    private double price;

    @RequiredField(value = "商品数量")
    private int size;

    @RequiredField(value = "商品实付")
    private double payment;

    @ApiField(value = "商品交易快照地址")
    private String snapshot;

    @ApiField(value = "商品图片地址")
    private String imageUrl;

    @ApiField(value = "明细是否退款")
    private int hasRefund;

    public String getTradeID() {
        return tradeID;
    }

    public void setTradeID(String tradeID) {
        this.tradeID = tradeID;
    }

    public String getSkuID() {
        return skuID;
    }

    public void setSkuID(String skuID) {
        this.skuID = skuID;
    }

    public String getSkuTitle() {
        return skuTitle;
    }

    public void setSkuTitle(String skuTitle) {
        this.skuTitle = skuTitle;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getSnapshot() {
        return snapshot;
    }

    public void setSnapshot(String snapshot) {
        this.snapshot = snapshot;
    }

    public String getOrderID() {

        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getHasRefund() {
        return hasRefund;
    }

    public void setHasRefund(int hasRefund) {
        this.hasRefund = hasRefund;
    }
}