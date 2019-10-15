package test;/*
@(#)test.B2CTest   2019-07-25

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
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hupun.api.BaseClient;
import com.hupun.api.domain.*;
import com.hupun.api.enums.Environment;
import com.hupun.api.request.*;
import com.hupun.api.response.*;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @author: Klaus 2019/7/25
 */

public class B2CTest {
    String testKey = "19KLAUSTEST";
    String testSecret = "1D1DEEF2D89630798CD3837A43A8C34A";
    BaseClient client = BaseClient.instance(Environment.TEST, testKey,testSecret);

    @Test
    public void testPushCategory() throws IOException {
        List<B2CCategory> categories = new ArrayList<>();
        PushCategoryRequest request = new PushCategoryRequest(categories);
        B2CCategory category = new B2CCategory();
        category.setCategoryID("kk0807index");
        category.setName("sdkTest");
        category.setSortOrder(1);
        category.setStatus(1);
        category.setShopNick("klausTEST0807");
        categories.add(category);
        PushCategoryResponse response = client.execute(request);
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writeValueAsString(response));
    }

    @Test
    public void testPushTrade() throws IOException {
        PushTradeRequest request = new PushTradeRequest();
        List<B2CTrade> trades = new ArrayList<>(1);
        request.setTrades(trades);
        List<B2COrder> orders = new ArrayList<>(1);
        B2CTrade trade = new B2CTrade();
        trades.add(trade);
        trade.setOrders(orders);
        trade.setBuyer("15805015199");
        trade.setBuyerEmail("");
        trade.setStatus(2);
        trade.setBuyerMessage("朱阿龙");
        trade.setCreateTime(new Date(1537673109000L));
        trade.setPostFee(0.00);
        trade.setDiscountFee(0);
//        trade.setEndTime(new Date(0));
//        trade.setShippingTime(new Date(0L));
        trade.setModifyTime(new Date(1537871019744L));
        trade.setPayTime(new Date(1537673109000L));
        trade.setPayment(296.70);
        trade.setReceiverAddress("松熹路侨兴巷29号里面有电梯二楼");
        trade.setReceiverArea("晋江市");
        trade.setReceiverCity("泉州市");
        trade.setReceiverName("朱阿龙");
        trade.setReceiverPhone("");
        trade.setStoreID("");
        trade.setSellerMemo("");
        trade.setReceiverMobile("15805015199");
        trade.setReceiverProvince("福建省");
        trade.setReceiverZip("362261");
        trade.setIdentityNum("");
        trade.setHasRefund(0);
//        trade.setInvoice("");
        trade.setShopNick("klaustest");
        trade.setTotalFee(296.70);
        trade.setTradeID("K20190929-2311244661");
        B2COrder order = new B2COrder();
        order.setItemCode("金纺怡神薰衣草促销装(2L+500ml)-20292370");
        order.setItemID("205");
        order.setItemTitle("金纺柔顺剂 薰衣草衣物护理剂液2.5L柔软剂持久留香正品");
        order.setOrderID("K20190929-1782");
        order.setPayment(95.70);
        order.setPrice(31.90);
        order.setSize(3);
        order.setSkuCode("金纺怡神薰衣草促销装(2L+500ml)-20292370");
        order.setSkuID("sku0");
        order.setStatus(0);
        order.setHasRefund(0);
        order.setSkuTitle("");
        order.setSnapshot("");
        order.setTradeID("K20190929-2311244661");
        order.setImageUrl("/upload/201808/13/201808131019557863.jpg");
        orders.add(order);


        PushTradeResponse response = client.execute(request);
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writeValueAsString(response));
    }


    @Test
    public void testPushItem() throws IOException {
        PushItemRequest request = new PushItemRequest();
        List<B2CItem> items = new ArrayList<>();
        request.setItems(items);
        B2CItem item = new B2CItem();
        items.add(item);
        item.setCreateTime(new Date());
        item.setImageURL("http://sdf.png");
        item.setItemCode("129618");
        item.setItemID("K129618");
        item.setItemURL("https://www.hunliji.com/hunpin/detail_129618");
        item.setImageURL("https://qnm.hunliji.com/o_1ddaa02m969p14hu1fa7m2m1jos1o.jpg");
        item.setModifyTime(new Date());
        item.setPrice(6);
//		item.setProperties("factory:china");
        item.setQuantity(3000);
        item.setShopNick("klaustest");
        item.setStatus(2);
        item.setTitle("爱情城堡小动物喜糖盒");
        List<B2CSku> skus = new ArrayList<>();
        item.setSkus(skus);
        B2CSku sku = new B2CSku();
        skus.add(sku);
        sku.setAttributes("size:L");
        sku.setCreateTime(new Date());
        sku.setImageURL("http:cxx.png");
        sku.setItemID("K129618");
        sku.setModifiyTime(new Date());
        sku.setPrice(10);
        sku.setQuantity(10);
        sku.setSkuCode("0901");
        sku.setSkuID("K111-1");
        sku.setStatus(2);

        PushItemResponse response = client.execute(request);
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writeValueAsString(response));
    }

    @Test
    public void queryInventories() throws IOException {
        QueryItemsQuantitiesRequest request = new QueryItemsQuantitiesRequest();
        request.setStart(new Date(System.currentTimeMillis()-3600*24*7*1000));
        request.setEnd(new Date());
        QueryItemsQuantitiesResponse response = client.execute(request);
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writeValueAsString(response));

    }

    @Test
    public void getTradeStatus() throws IOException {
        GetERPTradeStatusRequest request = new GetERPTradeStatusRequest();
        request.setShopNick("klaustest");
        request.setTradeIDs("K20190929-2311244661");
        GetERPTradeStatusResponse response = client.execute(request);
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writeValueAsString(response));
    }
    @Test
    public void testRandom() {
        Random random = new Random();
        System.out.println(random.nextInt(0));
    }
}