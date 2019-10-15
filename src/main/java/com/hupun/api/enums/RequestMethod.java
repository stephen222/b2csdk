package com.hupun.api.enums;

public enum RequestMethod {
    /**
     * post请求
     */
    POST(1, "post请求"),
    /**
     * get请求
     */
    GET(0,"get请求");

    RequestMethod(int flag, String label) {
        this.flag = flag;
        this.label = label;
    }
    /** 标识 */
    public final int flag;
    /** 标签 */
    public final String label;
}
