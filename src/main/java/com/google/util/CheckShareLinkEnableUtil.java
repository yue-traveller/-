package com.google.util;



import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class CheckShareLinkEnableUtil {

    static CloseableHttpClient httpClient = HttpClients.createDefault();

    public static  boolean cheek(String link) throws IOException {
        HttpGet httpGet = new HttpGet(link);
        httpGet.setHeader("User-Agent","Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36");
        CloseableHttpResponse response = httpClient.execute(httpGet);
        HttpEntity entity = response.getEntity();
        String result = EntityUtils.toString(entity, "UTF-8");
        if (result.contains("请输入提取码")||result.contains("分享无限制")){
            return true;
        }else {
            return false;
        }

    }

}
