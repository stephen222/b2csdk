package com.hupun.api;

import java.io.IOException;

public interface HupunClient {
        <T extends BaseResponse> T execute(BaseRequest<T> var1) throws IOException;
        <T extends BaseResponse> T execute(BaseRequest<T> var1, String var2) throws ApiException;
}
