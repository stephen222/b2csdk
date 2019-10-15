/*
@(#)Category   2019-07-24

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
 * @author: Klaus 2019/7/24
 */
public class B2CCategory implements Serializable {
    private static final long serialVersionUID = -4056279007124328631L;

    @RequiredField(value = "第三方类目编号")
    private String categoryID;
    @RequiredField(value = "类目名称")
    private String name;
    @RequiredField(value = "类目状态: 1：使用中；0：已删除")
    private int status = 1;
    @RequiredField(value = "索引顺序，用于排序")
    private long sortOrder;
    @RequiredField(value = "店铺昵称")
    private String shopNick;
    @ApiField(value = "父类目编码，字类目必填")
    private String parentID;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(long sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentID() {
        return parentID;
    }

    public void setParentID(String parentID) {
        this.parentID = parentID;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public static void main(String[] args) {
        B2CCategory category = new B2CCategory();
    }

    public String getShopNick() {
        return shopNick;
    }

    public void setShopNick(String shopNick) {
        this.shopNick = shopNick;
    }
}