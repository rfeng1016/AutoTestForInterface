/**
 * Created by RuanFeng on 2017/5/7.
 */

import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.config.HttpClientConfig;
import io.restassured.response.Response;
import io.restassured.path.xml.element.Node;
import io.restassured.path.xml.element.NodeChildren;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static io.restassured.path.xml.XmlPath.with;
import static org.junit.Assert.*;



public class APIForXML {

    private static String xml;
    Response response;

    @Before
    public static void init(){
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

        xml = "<shopping>\n" +
                " <category type=\"groceries\">\n" +
                " <item>\n" +
                " <name>Chocolate</name>\n" +
                " <price>10</price>\n" +
                " </item>\n" +
                " <item>\n" +
                " <name>Coffee</name>\n" +
                " <price>20</price>\n" +
                " </item>\n" +
                " </category>\n" +
                " <category type=\"supplies\">\n" +
                " <item>\n" +
                " <name>Paper</name>\n" +
                " <price>5</price>\n" +
                " </item>\n" +
                " <item quantity=\"4\">\n" +
                " <name>Pens</name>\n" +
                " <price>15</price>\n" +
                " </item>\n" +
                " </category>\n" +
                " <category type=\"present\">\n" +
                " <item when=\"Aug 10\">\n" +
                " <name>Kathryn's Birthday</name>\n" +
                " <price>200</price>\n" +
                " </item>\n" +
                " </category>\n" +
                " </shopping>";
    }

    @Test
    public void xmlfunction1() throws Exception{
        response = RestAssured.get("http://www.runoob.com/try/xml/cd_catalog.xml");
        int cds = response.xmlPath().getList("CATALOG.CD").size();

        for (int i = 0; i < cds;i++){
            //断言cd节点中title全部非空
            assertNotNull(response.xmlPath().getString("CATALOG.CD[" + i + "].TITLE"));

        }
    }

    @Test
    public void xmlfunction2() throws Exception{
        //查询item数量
        int items = with(xml).get("shopping.category.item.size()");
        System.out.println("存在"+items+"个item节点");

        //获取第一个category 节点
        Node category = with(xml).get("shopping.category[0]");

        //获取节点下的对象，打印第一个节点name的值
        System.out.println("category[0] name="+category.children().list().get(0).get("name").toString());

        //循环打印属性
        List<Node> nodes = category.children().list();
        for (Node node:nodes) {
            System.out.println("节点中name="+node.get("name").toString());
        }

        //获取满足条件的节点
        List<Node> itemsBetweenTenAndTwenty = with(xml).get("shopping.category.item.findAll { item -> def price = item.price.toFloat(); price >= 10 && price <= 20 }");

        //获取type = groceries 下所有name属性
        List<String> grocerie_names = with(xml).getList("shopping.category.findAll { it.@type == 'groceries' }.item.name");
        System.out.println("type = groceries 下所有name属性==="+grocerie_names.toString());

        //获取shopping下所有category节点
        NodeChildren nodechildren = with(xml).getNodeChildren("shopping.category");
        System.out.println(nodechildren.list().get(0).children().list().get(0).get("name").toString());
        System.out.println(nodechildren.list().get(1).children().list().get(0).get("name").toString());
    }


}












































































