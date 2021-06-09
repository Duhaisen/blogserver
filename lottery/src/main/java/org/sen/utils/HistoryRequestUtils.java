package org.sen.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.sen.constant.RequestConst;
import org.sen.entity.CpHistory;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class HistoryRequestUtils {

    public static JSONObject getRequest(String url) {
        // 创建Httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();

        String resultString = "";
        CloseableHttpResponse response = null;
        try {
            // 创建uri
            URIBuilder builder = new URIBuilder(url);

            URI uri = builder.build();

            // 创建http GET请求
            HttpGet httpGet = new HttpGet(uri);

            // 执行请求
            response = httpclient.execute(httpGet);

            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                resultString = EntityUtils.toString(response.getEntity(), RequestConst.DEFAULT_CHARSET);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return JSONObject.parseObject(resultString);
    }

    /**
     * ignore all exception,always return list
     */
    public static List<CpHistory> result2CpHistoryList(JSONObject jsonObject) {

        try {

            JSONObject value = jsonObject.getJSONObject("value");

            JSONArray jsonArray = value.getJSONArray("list");

            String listString = JSONObject.toJSONString(jsonArray);

            return JSONObject.parseArray(listString, CpHistory.class);

        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}
