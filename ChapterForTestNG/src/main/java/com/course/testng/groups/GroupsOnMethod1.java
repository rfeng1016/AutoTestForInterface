package groups;

import org.testng.annotations.Test;

public class GroupsOnMethod1 {

    @Test (groups = "server")
    public void test1(){
        System.out.println("这是服务端组的测试方法1");
    }

    @Test (groups = "server")
    public void test2(){
        System.out.println("这是服务端组的测试方法2");
    }
}
