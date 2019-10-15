package com.hupun.api.enums;

import com.hupun.api.B2CConstants;

/**
 * 运行环境
 */
public enum Environment {
    /**
     * 测试环境
     */
    TEST(B2CConstants.TEST_SERVER_URL),
    /**
     * 生产环境
     */
    PRO(B2CConstants.PRO_SERVER_URL);
    Environment(String label) {
        this.lable = label;
    }
    private String lable;

    public String getLable() {
        return lable;
    }

}
