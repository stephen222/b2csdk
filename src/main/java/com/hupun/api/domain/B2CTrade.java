/*
@(#)B2CTrade   2019-09-29

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
import java.util.Date;
import java.util.List;

/**
 * @author: Klaus 2019/9/29
 */
public class B2CTrade implements Serializable {
    private static final long serialVersionUID = 4859641054044794927L;

    @RequiredField(value = "线上订单号/交易号")
    private String tradeID;

    @RequiredField(value = "在ERP中注册的B2C店铺昵称")
    private String shopNick;

    @RequiredField(value = "交易状态:0：未创建交易；1：等待付款；2：等待发货；3：已完成；4：已关闭；5：等待确认；6：已签收；")
    private int status;

    @RequiredField(value = "交易创建时间 ，格式:yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @ApiField(value = "交易付款时间 ，付款后必填, 格式:yyyy-MM-dd HH:mm:ss")
    private Date payTime;

    @ApiField(value = "支付交易号")
    private String payNo;

    @ApiField(value = "支付类型")
    private String payType;

    @ApiField(value = "交易结束时间 ，结束后必填, 格式:yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    @RequiredField(value = "交易最新修改时间 ， 格式:yyyy-MM-dd HH:mm:ss")
    private Date modifyTime;

    @ApiField(value = "交易发货时间 ，发货后必填, 格式:yyyy-MM-dd HH:mm:ss")
    private Date shippingTime;

    @ApiField(value = "卖家备注")
    private String sellerMemo;

    @ApiField(value = "发货类型:1：EMS；2：平邮；0：快递；9：卖家承担运费；11：虚拟物品；121：自提；122：商家自送")
    private int shippingType;

    @ApiField(value = "门店ID")
    private String storeID;

    @RequiredField(value = "总金额 (商品金额总数，不含邮费)")
    private double totalFee;

    @RequiredField(value = "邮费")
    private double postFee;

    @RequiredField(value = "实付金额 （总金额-优惠金额+邮费）")
    private double payment;

    @ApiField(value = "优惠金额 (总优惠)")
    private double discountFee;

    @RequiredField(value = "买家")
    private String buyer;

    @ApiField(value = "买家邮件")
    private String buyerEmail;

    @ApiField(value = "买家留言")
    private String buyerMessage;

    @RequiredField(value = "收件人姓名")
    private String receiverName;

    @RequiredField(value = "收件人省")
    private String receiverProvince;

    @RequiredField(value = "收件人市")
    private String receiverCity;

    @ApiField(value = "收件人区/县 ")
    private String receiverArea;

    @RequiredField(value = "收件人详细地址")
    private String receiverAddress;

    @ApiField(value = "收件人邮编")
    private String receiverZip;

    @ApiField(value = "收件人手机")
    private String receiverMobile;

    @ApiField(value = "收件人电话（手机和电话两者必填一处）")
    private String receiverPhone;

    @ApiField(value = "身份证号")
    private String identityNum;

    @ApiField(value = "退款退货标记：1：退款；0：未退款")
    private int hasRefund = 0;


    @RequiredField(value = "订单类型 1:一口价；5：货到付款；7：预约")
    private int tradeType;

    @ApiField(value = "服务费")
    private double serviceFee;

    @RequiredField(value = "交易明细集")
    private List<B2COrder> orders;
    @ApiField(value = "快递公司编码，用户自定义编码")
    private String logisticsCode;
    @ApiField(value = "快递单号")
    private String expressCode;

    @ApiField(value = "省份证上的名称")
    private String identityName;
    @ApiField(value = "订购人手机")
    private String buyerMobile;
    @ApiField(value = "收件人国家")
    private String receiverCountry;
    @ApiField(value = "金额币种")
    private String currencyCode;

    public String getTradeID() {
        return tradeID;
    }

    public void setTradeID(String tradeID) {
        this.tradeID = tradeID;
    }

    public String getShopNick() {
        return shopNick;
    }

    public void setShopNick(String shopNick) {
        this.shopNick = shopNick;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getShippingTime() {
        return shippingTime;
    }

    public void setShippingTime(Date shippingTime) {
        this.shippingTime = shippingTime;
    }

    public int getShippingType() {
        return shippingType;
    }

    public void setShippingType(int shippingType) {
        this.shippingType = shippingType;
    }

    public String getStoreID() {
        return storeID;
    }

    public void setStoreID(String storeID) {
        this.storeID = storeID;
    }

    public double getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(double totalFee) {
        this.totalFee = totalFee;
    }

    public int getTradeType() {
        return tradeType;
    }

    public void setTradeType(int tradeType) {
        this.tradeType = tradeType;
    }

    public double getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(double serviceFee) {
        this.serviceFee = serviceFee;
    }

    public Date getCreateTime() {

        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public String getPayNo() {
        return payNo;
    }

    public void setPayNo(String payNo) {
        this.payNo = payNo;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getSellerMemo() {
        return sellerMemo;
    }

    public void setSellerMemo(String sellerMemo) {
        this.sellerMemo = sellerMemo;
    }

    public double getPostFee() {
        return postFee;
    }

    public void setPostFee(double postFee) {
        this.postFee = postFee;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    public double getDiscountFee() {
        return discountFee;
    }

    public void setDiscountFee(double discountFee) {
        this.discountFee = discountFee;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public String getBuyerEmail() {
        return buyerEmail;
    }

    public void setBuyerEmail(String buyerEmail) {
        this.buyerEmail = buyerEmail;
    }

    public String getBuyerMessage() {
        return buyerMessage;
    }

    public void setBuyerMessage(String buyerMessage) {
        this.buyerMessage = buyerMessage;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverProvince() {
        return receiverProvince;
    }

    public void setReceiverProvince(String receiverProvince) {
        this.receiverProvince = receiverProvince;
    }

    public String getReceiverCity() {
        return receiverCity;
    }

    public void setReceiverCity(String receiverCity) {
        this.receiverCity = receiverCity;
    }

    public String getReceiverArea() {
        return receiverArea;
    }

    public void setReceiverArea(String receiverArea) {
        this.receiverArea = receiverArea;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public String getReceiverZip() {
        return receiverZip;
    }

    public void setReceiverZip(String receiverZip) {
        this.receiverZip = receiverZip;
    }

    public String getReceiverMobile() {
        return receiverMobile;
    }

    public void setReceiverMobile(String receiverMobile) {
        this.receiverMobile = receiverMobile;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public String getIdentityNum() {
        return identityNum;
    }

    public void setIdentityNum(String identityNum) {
        this.identityNum = identityNum;
    }

    public int getHasRefund() {
        return hasRefund;
    }

    public void setHasRefund(int hasRefund) {
        this.hasRefund = hasRefund;
    }

    public List<B2COrder> getOrders() {
        return orders;
    }

    public void setOrders(List<B2COrder> orders) {
        this.orders = orders;
    }

    public String getLogisticsCode() {
        return logisticsCode;
    }

    public void setLogisticsCode(String logisticsCode) {
        this.logisticsCode = logisticsCode;
    }

    public String getExpressCode() {
        return expressCode;
    }

    public void setExpressCode(String expressCode) {
        this.expressCode = expressCode;
    }

    public String getIdentityName() {
        return identityName;
    }

    public void setIdentityName(String identityName) {
        this.identityName = identityName;
    }

    public String getBuyerMobile() {
        return buyerMobile;
    }

    public void setBuyerMobile(String buyerMobile) {
        this.buyerMobile = buyerMobile;
    }

    public String getReceiverCountry() {
        return receiverCountry;
    }

    public void setReceiverCountry(String receiverCountry) {
        this.receiverCountry = receiverCountry;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }
}