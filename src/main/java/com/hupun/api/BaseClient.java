/*
@(#)BaseClient   2019-07-25

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

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hupun.api.enums.Environment;
import com.hupun.api.enums.RequestMethod;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

import javax.net.ssl.*;
import java.io.*;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.CodingErrorAction;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;

/**
 * @author: Klaus 2019/7/25
 */
public class BaseClient implements HupunClient {
    private Environment environment;
    private String key;
    private String secret;
    private int timeout = 300000;

    /**
     * @param e      请求环境
     * @param key    授权key
     * @param secret 授权secret
     */
    private BaseClient(Environment e, String key, String secret) {
        this.environment = e;
        this.key = key;
        this.secret = secret;
    }

    public static BaseClient instance(Environment e, String key, String secret) {
        return new BaseClient(e, key, secret);
    }

    /**
     * 执行请求
     *
     * @param request
     * @return
     * @throws APIException
     * @throws Exception
     */
    public <T extends BaseResponse> T execute(BaseRequest<T> request) throws IOException {
        Map params = params(key, secret, request.getParams());
        try {
            StringBuilder url = new StringBuilder(environment == null ? Environment.PRO.getLable() : environment.getLable()).append("/").append(request.getVersion()).append(request.getApiMethodName());
            if (request.getMethod() == RequestMethod.GET) {
                url.append("?");
            }

            String[] result = request.getMethod() == RequestMethod.GET ? get(url.toString(), params, timeout) : post(url.toString(), params, timeout);
            if (result.length == 2 && !StringUtils.isEmpty(result[1]))
                throw new ApiException("0", result[1]);
            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
//            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
//            String resultStr = StringEscapeUtils.unescapeJava(result[0]);
            T response = mapper.readValue(result[0], request.getResponseClass());
            Map<String, Object> m = mapper.readValue(result[0], Map.class);
            if (response == null) {
                response = (T) new BaseResponse();
                response.setSuccess(true);
            } else if (m != null && m.get("response") != null) {
                response.parseData((String) m.get("response"));
            }
            return response;
        } catch (IOException e) {
            throw e;
        } catch (ApiException e) {

        }
        return null;
    }

    /**
     * 发送 GET 请求
     *
     * @param url    目标地址
     * @param params 参数集
     * @return 响应结果 [正常内容，异常内容]
     * @throws IOException
     */
    protected String[] get(String url, Map<String, String> params, int timeout) throws IOException {
        String query = buildQuery(params, "utf8");
        HttpURLConnection conn = connection(url.concat(query), timeout);
        try {
            String[] res = response(conn, null);
            return res;
        } finally {
            if (conn != null) conn.disconnect();
        }
    }

    /**
     * 发送 POST 请求
     *
     * @param url    目标地址
     * @param params 参数集
     * @return 响应结果 [正常内容，异常内容]
     * @throws IOException
     */
    protected static String[] post(String url, Map<String, String> params, int timeout) throws IOException {
        HttpURLConnection conn = connection(url, timeout);
        try {
            request(conn, params, timeout, new StringBuilder());
            String[] res = response(conn, null);
            return res;
        } finally {
            if (conn != null) conn.disconnect();
        }
    }

    /**
     * 读取响应
     *
     * @param conn 连接
     * @param buf  响应缓存
     * @return 响应内容 [正常内容，异常内容]
     * @throws IOException
     */
    protected static String[] response(HttpURLConnection conn, StringBuilder buf) throws IOException {
        Charset cs = charset(conn.getContentType());

        Reader reader = null;
        int index = 1;
        InputStream is = conn.getErrorStream();
        if (is == null) {
            is = conn.getInputStream();
            index = 0;
        }

        try {
            if (gzip(conn.getHeaderField("Content-Encoding"))) is = new GZIPInputStream(is);
            reader = new InputStreamReader(is, cs.newDecoder().onMalformedInput(CodingErrorAction.REPORT)
                    .onUnmappableCharacter(CodingErrorAction.REPORT));
            char[] cbuf = new char[128];

            StringBuilder rbuf = new StringBuilder(128);
            URL url = conn.getURL();
            if (buf != null) {
                String query = trim(url.getQuery());
                buf.append(url.getPath());
                if (query.length() > 0) buf.append('?').append(query);
                buf.append(" << ");
            }
            for (int r = 0; (r = reader.read(cbuf)) > 0; ) {
                // check(cbuf, 0, r);
                rbuf.append(cbuf, 0, r);
                if (buf != null) buf.append(cbuf, 0, r);
            }

            String[] result = new String[2];
            result[index] = rbuf.toString();
            return result;
        } finally {
            if (is != null) is.close();
        }
    }

    /**
     * 是否压缩
     *
     * @param encodings 类型
     * @return 是、否
     */
    protected static boolean gzip(String encodings) {
        return encodings != null && encodings.indexOf("gzip") > -1;
    }


    /**
     * 获取连接
     *
     * @param url     远程应用地址
     * @param timeout 连接时长
     * @return 连接实例
     * @throws IOException
     */
    protected static HttpURLConnection connection(String url, int timeout) throws IOException {
        URL u = new URL(url);

        HttpURLConnection conn = null;
        if ("https".equals(u.getProtocol())) {
            SSLContext ctx = null;
            try {
                ctx = SSLContext.getInstance("TLS");
                ctx.init(new KeyManager[0], new TrustManager[]{new X509TrustManager() {
                    @Override
                    public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
                    }

                    @Override
                    public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
                    }

                    @Override
                    public X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }
                }}, new SecureRandom());
            } catch (Exception e) {
                throw new IOException(e);
            }
            HttpsURLConnection connHttps = (HttpsURLConnection) u.openConnection();
            connHttps.setSSLSocketFactory(ctx.getSocketFactory());
            connHttps.setHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;// 默认都认证通过
                }
            });
            conn = connHttps;
        } else {
            conn = (HttpURLConnection) u.openConnection();
        }
        conn.setConnectTimeout(timeout);

        return conn;
    }

    /**
     * 构建参数内容
     *
     * @param params  参数集
     * @param charset 字符集
     * @return 内容
     */
    public static String buildQuery(Map params, String charset) {
        return buildQuery(new StringBuilder(), params, charset).toString();
    }

    /**
     * 构建参数内容
     *
     * @param buf     字符缓存区
     * @param params  参数集
     * @param charset 字符集
     * @return 字符缓存区
     */
    public static StringBuilder buildQuery(StringBuilder buf, Map<Object, Object> params, String charset) {
        if (params == null || params.isEmpty()) {
            return null;
        }
        int i = 0;
        for (Map.Entry<Object, Object> entry : params.entrySet()) {
            String name = trim(entry.getKey()), value = trim(entry.getValue());
            // 忽略参数名或参数值为空的参数
            if (name.length() > 0 && value.length() > 0) {

                if (i++ > 0) buf.append("&");

                buf.append(name).append("=").append(encode(value, charset));
            }
        }
        return buf;
    }


    public <T extends BaseResponse> T execute(BaseRequest<T> request, String var2) throws ApiException {
        return null;
    }

    protected Map<String, Object> params(String key, String secret, Map<String, Object> params) {
        params.put("app_key", key);
        params.put("format", "json");
        params.put("timestamp", String.valueOf(System.currentTimeMillis()));
        params.put("sign", signature(params, secret));
        return params;
    }

    public static void main(String[] args) {
        BaseClient client = new BaseClient(Environment.TEST, "S7SZ0S7", "6020E5581DDA324A8EF4CE92B351EB40");
        Map<String, Object> params = new HashMap<>();
        params.put("app_key", client.key);
        params.put("format", "json");
        params.put("timestamp", "1568958595986");
        params.put("trades", "[{\"tradeID\":\"11\",\"shopNick\":\"V52mall\",\"status\":\"1\",\"createTime\":\"1568805892578\",\"payTime\":\"1568805892678\",\"endTime\":\"1568805893678\",\"modifyTime\":\"1568805893678\",\"shippingTime\":\"1568805893678\",\"storeID\":\"001\",\"sellerMemo\":\"eley beley taraq turuq\",\"shippingType\":\"0\",\"totalFee\":\"25.00\",\"postFee\":\"12.00\",\"payment\":\"35.00\",\"discountFee\":\"2.00\",\"buyer\":\"elzoq\",\"buyerMessage\":\"no comment\",\"buyerEmail\":\"elzoq@sina.com\",\"receiverName\":\"elzoq\",\"receiverProvince\":\"新疆\",\"receiverCity\":\"喀什\",\"receiverArea\":\"喀尔萨西路\",\"receiverAddress\":\"智慧呼叫中心第三层\",\"receiverZip\":\"8244800\",\"receiverMobile\":\"16699689295\",\"receiverPhone\":\"0998-0000000\",\"hasRefund\":\"0\",\"orders\":[{\"tradeID\":\"11\",\"orderID\":\"1122\",\"itemID\":\"1243455232\",\"itemTitle\":\"新鲜进口苹果\",\"skuTitle\":\"红色\",\"status\":\"1\",\"hasRefund\":\"0\",\"price\":\"5.00\",\"size\":\"5\",\"snapshot\":\"http://www.aaa.com\",\"imageUrl\":\"https://herp.oss-cn-hangzhou.aliyuncs.com/image/item/48983722927547/1568694742187977.jpg\",\"payment\":\"35.00\"}]}]");
        System.out.println(client.signature(params, client.secret));

    }

    private String signature(Map<String, Object> params, String secret) {
        // 1. sort key
        String[] keys = params.keySet().toArray(new String[params.size()]);
        Arrays.sort(keys);

        // 2. build
        StringBuilder builder = new StringBuilder(secret);
        for (String key : keys) {
            if (params.get(key) == null || params.get(key).equals("")) {
                continue;
            }
            builder.append(key).append(params.get(key));
        }
        builder.append(secret);
//        System.out.println(builder.toString());

        // 3. make sign
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
//			System.out.println("bytes:"+builder.toString().getBytes("UTF-8"));
            byte[] bytes = md.digest(builder.toString().getBytes("UTF-8"));
            return byte2hex(bytes);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;
    }

    private String byte2hex(byte[] bytes) {
        StringBuilder sign = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if (hex.length() == 1) {
                sign.append("0");
            }
            sign.append(hex.toUpperCase());
        }
        return sign.toString();
    }

    /**
     * 发送请求
     *
     * @param conn    连接
     * @param params  参数集
     * @param timeout 超时时长
     * @param buf     请求内容缓存
     * @throws IOException
     */
    protected static void request(HttpURLConnection conn, Map params, int timeout, final StringBuilder buf) throws IOException {
        OutputStream os = null;
        Writer writer = null;
        try {
            conn.setRequestMethod("POST");
            conn.setReadTimeout(timeout);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
            os = conn.getOutputStream();

            writer = new OutputStreamWriter(os, Charset.forName("utf8"));
            if (null != buf) {
                URL url = conn.getURL();
                buf.append("POST ").append(url.getPath());
                if (url.getQuery() != null) {
                    buf.append('?').append(url.getQuery());
                }
                buf.append(" >> ");
                writer = new BufferedWriter(writer) {
                    @Override
                    public Writer append(char c) throws IOException {
                        buf.append(c);
                        return super.append(c);
                    }

                    @Override
                    public Writer append(CharSequence csq) throws IOException {
                        buf.append(csq);
                        return super.append(csq);
                    }
                };
            }

            if (params != null) {
                int i = 0;
                for (Iterator<Map.Entry<Object, Object>> it = params.entrySet().iterator(); it.hasNext(); it.remove(), i++) {
                    Map.Entry<Object, Object> en = it.next();
                    if ("".equals(trim(en.getKey())) || "".equals(trim(en.getValue()))) {
                        continue;
                    }

                    if (i > 0) {
                        writer.append('&');
                    }
                    writer.append(encode(trim(en.getKey()), "utf8"));
                    writer.append('=');
                    writer.append(encode(trim(en.getValue()), "utf8"));
                }
            }
            writer.flush();
        } finally {
            if (writer != null) {
                writer.close();
            }
            if (os != null) {
                os.close();
            }
        }
    }


    /**
     * 修剪字符串
     *
     * @param content 字符串
     * @return 修剪后字符串
     */
    private static String trim(Object content) {
        CharSequence cs = content == null ? null : (content instanceof CharSequence) ? (CharSequence) content : String.valueOf(content);
        if (cs == null) {
            return "";
        }
        int start = 0;
        int end = cs.length() - 1;
        while (start <= end && Character.isWhitespace(cs.charAt(start))) {
            start++;
        }
        while (end >= start && Character.isWhitespace(cs.charAt(end))) {
            end--;
        }
        return cs.subSequence(start, end + 1).toString();
    }

    /**
     * 获取字符集
     *
     * @param ctype 内容类型
     * @return 字符集
     */
    protected static Charset charset(String ctype) {
        Charset charset = Charset.forName("utf8");

        Matcher m = Pattern.compile("charset\\s*=\\s*([^;]+)", Pattern.CASE_INSENSITIVE).matcher(trim(ctype));
        while (m.find()) {
            try {
                charset = Charset.forName(m.group(1));
                break;
            } catch (RuntimeException ignored) {
            }
        }

        return charset;
    }


    /**
     * 转换 URL 参数
     *
     * @param value   参数值
     * @param charset 字符集
     * @return 参数串
     */
    private static String encode(String value, String charset) {
        try {
            return URLEncoder.encode(value, charset);
        } catch (UnsupportedEncodingException e) {
            throw new UnsupportedOperationException();
        }
    }


}