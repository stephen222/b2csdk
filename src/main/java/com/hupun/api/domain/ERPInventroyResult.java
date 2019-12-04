/*
@(#)ERPInventroyResult   2019-09-29

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

import java.util.List;

/**
 * @author: Klaus 2019/9/29
 */
public class ERPInventroyResult {
    /** ERP中的仓库编码*/
    private String storage_code;
    /** ERP中的仓库名称*/
    private String storage_name;
    /** 该仓库的可用库存*/
    private double available;
    /** 该仓库的实际库存*/
    private double quantity;
    /** 关联的线上商品编号*/
    private String oln_item_id;
    /** 关联的线上规格编号*/
    private String oln_sku_id;
    /** 商品编码*/
    private String item_code;
    /** 规格编码*/
    private String sku_code;
    /** 最近库存变动时间*/
    private String modified;
    /** 批次号*/
    private List<InventoryBatch> batchs;


    public String getStorage_code() {
        return storage_code;
    }

    public void setStorage_code(String storage_code) {
        this.storage_code = storage_code;
    }

    public String getStorage_name() {
        return storage_name;
    }

    public void setStorage_name(String storage_name) {
        this.storage_name = storage_name;
    }

    public String getOln_item_id() {
        return oln_item_id;
    }

    public void setOln_item_id(String oln_item_id) {
        this.oln_item_id = oln_item_id;
    }

    public String getOln_sku_id() {
        return oln_sku_id;
    }

    public void setOln_sku_id(String oln_sku_id) {
        this.oln_sku_id = oln_sku_id;
    }

    public String getItem_code() {
        return item_code;
    }

    public void setItem_code(String item_code) {
        this.item_code = item_code;
    }

    public String getSku_code() {
        return sku_code;
    }

    public void setSku_code(String sku_code) {
        this.sku_code = sku_code;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public List<InventoryBatch> getBatchs() {
        return batchs;
    }

    public void setBatchs(List<InventoryBatch> batchs) {
        this.batchs = batchs;
    }

    public double getAvailable() {
        return available;
    }

    public void setAvailable(double available) {
        this.available = available;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
}