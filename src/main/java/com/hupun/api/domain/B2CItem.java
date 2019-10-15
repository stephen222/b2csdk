/*
@(#)B2CItem   2019-09-29

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
public class B2CItem implements Serializable {
    private static final long serialVersionUID = -473496342948368788L;
    @RequiredField(value = "线上商品编号")
    private String itemID;

    @ApiField(value = "线上商品类目编号")
    private String categoryID;

    @RequiredField(value = "在ERP中注册的店铺昵称 ，重要")
    private String shopNick;

    @RequiredField(value = "商品标题")
    private String title;

    @ApiField(value = "商家编码 ，万里牛商品分类依靠商家自己设定的编码，如果第三方有则尽可能填写")
    private String itemCode;

    @ApiField(value = "价格，若无子规格，必填")
    private double price;

    @ApiField(value = "库存，若无子规格，必填")
    private int quantity;

    @RequiredField(value = "商品链接 ，用于ERP跳转到商场 ")
    private String itemURL;

    @RequiredField(value = "商品图片链接 ，用于ERP图片展示 ")
    private String imageURL;

    @RequiredField(value = "状态,0：已删除，1：在售，2：待售，仓库中")
    private int status;

    @RequiredField(value = "创建时间，格式:yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @RequiredField(value = "最新修改时间，格式:yyyy-MM-dd HH:mm:ss")
    private Date modifyTime;

    @ApiField(value = "属性：key1:value;key2:value;...以key:value的键值形式拼接，必须使用半角符  生产厂家:万里牛;原料:原木")
    private String properties;

    @ApiField(value = "品牌")
    private String brand;

    @ApiField(value = "重量 ")
    private double weight;

    @ApiField(value = "条码")
    private String barcode;

    @ApiField(value = "规格集，无规格商品不填 ")
    private List<B2CSku> skus;

    public String getShopNick() {
        return shopNick;
    }

    public void setShopNick(String shopNick) {
        this.shopNick = shopNick;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public List<B2CSku> getSkus() {
        return skus;
    }

    public void setSkus(List<B2CSku> skus) {
        this.skus = skus;
    }

    public String getItemID() {

        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getItemURL() {
        return itemURL;
    }

    public void setItemURL(String itemURL) {
        this.itemURL = itemURL;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getProperties() {
        return properties;
    }

    public void setProperties(String properties) {
        this.properties = properties;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }
}