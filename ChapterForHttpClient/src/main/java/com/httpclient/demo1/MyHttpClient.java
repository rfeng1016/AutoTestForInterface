package com.httpclient.demo1;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import java.io.IOException;

public class MyHttpClient {

    @Test
    public void testCase1() throws IOException {

        String result;
        HttpGet httpGet = new HttpGet("http://www.baidu.com");
        HttpClient httpClient = new DefaultHttpClient();
        HttpResponse httpResponse = httpClient.execute(httpGet);
        result = EntityUtils.toString(httpResponse.getEntity());
        System.out.println(result);


    }

    @Test
    public void testCase2() throws IOException {
        String result;
        HttpPost httpPost = new HttpPost("http://ms.jr.jd.com/gw/generic/bx/na/m/queryGradesForNA");
        HttpClient httpClient = new DefaultHttpClient();
        HttpResponse httpResponse = httpClient.execute(httpPost);
        result = EntityUtils.toString(httpResponse.getEntity());
        System.out.println(result);

    }
}








































