/*
@(#)BaseResponse   2019-07-24

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
package com.hupun.api;

import java.io.Serializable;

/**
 * {"success":true,"error_code":null,"error_msg":null,"response":"[\"kk0807index\"]"}
 * @author: Klaus 2019/7/24
 */
public class BaseResponse<T> implements Serializable {
    private static final long serialVersionUID = -3819718656862094745L;
    protected String error_msg;
    protected String error_code;
    protected boolean success;

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getError_msg() {
        return error_msg;
    }

    public void setError_msg(String error_msg) {
        this.error_msg = error_msg;
    }

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    protected T parseData(String json) {
        return (T) new Object();
    }
}