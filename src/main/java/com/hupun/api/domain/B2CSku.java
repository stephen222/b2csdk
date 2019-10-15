/*
@(#)B2CSku   2019-09-29

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

/**
 * @author: Klaus 2019/9/29
 */
public class B2CSku implements Serializable {

    private static final long serialVersionUID = 470186108143516581L;
    @RequiredField(value = "线上规格编号")
    private String skuID;

    @RequiredField(value = "线上商品编号")
    private String itemID;

    @RequiredField(value = "规格库存")
    private int quantity;

    @RequiredField(value = "规格价格")
    private double price;

    @ApiField(value = "外部规格编码，商家自己设定的规格编码, 尽量填写，ERP通过该编码区分商品")
    private String skuCode;

    @RequiredField(value = "属性：key1:value;key2:value;...以key:value的键值形式拼接，必须使用半角符 颜色:红色;尺码:M")
    private String attributes;

    @RequiredField(value = "创建时间，格式:yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @RequiredField(value = "最新修改时间，格式:yyyy-MM-dd HH:mm:ss")
    private Date modifiyTime;

    @ApiField(value = "规格图片链接 ，用于ERP图片展示 ")
    private String imageURL;

    @RequiredField(value = "状态,0：已删除，1：在售，2：待售，仓库中")
    private int status;

    @ApiField(value = "重量")
    private double weight;

    @ApiField(value = "条码")
    private String barcode;

    public String getSkuID() {
        return skuID;
    }

    public void setSkuID(String skuID) {
        this.skuID = skuID;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public String getAttributes() {
        return attributes;
    }

    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifiyTime() {
        return modifiyTime;
    }

    public void setModifiyTime(Date modifiyTime) {
        this.modifiyTime = modifiyTime;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }
}