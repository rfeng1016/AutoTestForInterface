package wealth.test.stablepage;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class StableSelect {

    @BeforeTest
    public void beforeTest(){

    }


    //产品精选（小白理财）
    @Test
    public void productRecommendations(){

        Map<String,Object> parameters = new HashMap<String, Object>();
        //parameters.put("sPoint","15003##shouye5015_首次登录送888元大礼包$15008##geren5006$15003##shouye5008_理财*定期理财");
        //parameters.put("deviceId","866350035050535");
        //parameters.put("sign","SkRKUl9BbmRyb2lk");
        //parameters.put("clientType","android");
        //parameters.put("pin","");
        //parameters.put("src","JDJR");
        //parameters.put("version","200");
        //parameters.put("clientVersion","4.9.0");
        //parameters.put("deviceInfo","{\"deviceID\":\"866350035050535\",\"deviceModel\":\"vivo X9s\",\"iccID\":\"866350035050535\",\"imei\":\"866350035050535\",\"ipAddress\":\"10.13.72.199\",\"macAddress\":\"90:AD:F7:9A:14:38\",\"ratio\":1.7777778,\"screenHeight\":1920,\"screenWidth\":1080,\"sign\":\"SkRKUl9BbmRyb2lk\",\"softVersion\":\"4.9.0\",\"systemVersion\":\"7.1.1\"}");
        //parameters.put("a2","AAFaoOy4AECiqZLniu4rDk24ypaKlIRccNlHJQTgJoj3qw1KwC05kUbgLea_mBdw3C3C-5OkIkFB_EOSlqJ6-MXSCIrNtm9V");

        given().
                body(parameters).
            when().
                post("http://ms.jr.jd.com/gw/generic/bx/na/m/queryGradesForNA").
                prettyPeek().
            then().
                statusCode(200);



    }

    @AfterTest
    public void afterTest(){

    }

}
