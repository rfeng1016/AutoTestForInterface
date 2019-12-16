/**
 * Created by RuanFeng on 2017/5/7.
 */

import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.config.HttpClientConfig;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.get;
import static org.junit.Assert.assertEquals;

public class APIForJson {

    @Before
    public void setUp() throws Exception {

        //定义所有请求使用ssl配置
        RestAssured.useRelaxedHTTPSValidation();
        /*
            设置请求参数进行url code编码
            设置请求超时时间
         */
        RestAssured
                .config()
                .encoderConfig(EncoderConfig
                        .encoderConfig()
                        .defaultCharsetForContentType("utf-8","application/x-www-form-urlencoded"))
                .httpClient(HttpClientConfig.httpClientConfig()
                        .setParam("http.connection.timeout","5000")
                        .setParam("http.socket.timeout","5000"));
    }

    @Test
    public void getfunction1() throws Exception{
        int statuscode = get("https://testerhome.com/api/v3/topics.json?limit=2&offset=0&type=last_actived")
                 .getStatusCode();
        assertEquals(200,statuscode);
    }

    @Test
    public void getfunction2() throws Exception{
        int statuscode = given().param("limit",2)
                .and().param("offset",0)
                .and().param("type","last_actived")
                .get("https://testerhome.com/api/v3/topics.json")
                .getStatusCode();
        assertEquals(200,statuscode);
    }

    @Test
    public void getfunction3() throws Exception{
        Map<String,Object> parameters = new HashMap<String, Object>();
        parameters.put("limit",2);
        parameters.put("offset",0);
        parameters.put("type","last_actived");

        int statuscode = given()
                .param(String.valueOf(parameters))
                .get("https://testerhome.com/api/v3/topics.json?limit=2&offset=0&type=last_actived")
                .getStatusCode();
        assertEquals(200,statuscode);
    }



    @Test
    public void postfunction1() throws Exception{
        int statuscode = given()
                .param("grant_type","password")
                .and()
                .param("username","apitest@apitest.com")
                .post("https://testerhome.com/oauth/token")
                .getStatusCode();
        assertEquals(200,statuscode);
    }

    @Test
    public void postfunction2() throws Exception{
        Map<String,Object> parameters = new HashMap<String, Object>();
        parameters.put("grant_type","password");
        parameters.put("username","apitest@apitest.com");
        parameters.put("password","111111");

        int statuscode = given()
                .given()
                .params(parameters)
                .post("https://testerhome.com/oauth/token")
                .getStatusCode();
        assertEquals(200,statuscode);
    }


    /**
     *  模拟写一条测试用例
     */
    @Test
    public void checkValue() throws Exception{
        //上层接口
        Response response = get("https://testerhome.com/api/v3/topics.json?limit=2&offset=0&type=last_actived");

        //包装接口
        String jsonStr = response.asString();

        List<String> topics = (List<String>) get(response.jsonPath().getString("topics.id"));

        //判断收藏列表是否为5条数据（其中5为预期值 ）
        assertEquals(5,topics.size());

        //查询用户与id是否与预期值的一致(如果数据来源于数据库，可以直接读取然后进行循环判断)
        List<Integer> ids = new ArrayList<Integer>();
        ids.add(8434);
        ids.add(8397);
        ids.add(8298);
        ids.add(8467);
        ids.add(8432);

        assertEquals(ids,response.jsonPath().getList("topics.id"));

        //已知第一条记录的id为8434，判断topic下第一条记录的id是否相等
        assertEquals(8434,response.jsonPath().getInt("topics[0].id"));

        //已经接口业务逻辑 node_id = 13 的时候 node_name 为"社区管理"
        for (int i = 0;i < topics.size();i++){
            if (response.jsonPath().getInt("topics[\"+i+\"].node_id") == 13){
                assertEquals("社区管理",response.jsonPath().getString("topics["+i+"].node_name"));
            }
        }

        //已知node_name为社区管理的帖子是2篇，判断返回的响应数据中是否为2篇
        int nodenames = response.jsonPath().getList("topics.findAll{topics -> topics.node_name == '社区管理'}").size();
        assertEquals(2,nodenames);

    }


}




































































































































































































