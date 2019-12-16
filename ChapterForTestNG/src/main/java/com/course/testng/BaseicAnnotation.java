package ChapterForTestNG.src.main.java.com.course.testng;

import org.testng.annotations.*;

public class BaseicAnnotation {

    @Test
    public void testCase1(){

        System.out.println("这是一个测试用例1");
    }

    @Test
    public void testCase2(){

        System.out.println("这是一个测试用例2");
    }

    @Test(enabled = false)
    public void testCase3(){

        System.ou t.println("这不是一个测试用例3，忽略这条case");
    }

    @Test(groups = "testGrops")
    public void testCsee4(){

        System.out.println("这是一个分组的测试用例4");
    }

    @BeforeClass
    public void beforeClass(){

        System.out.println("我是BeforeClass");
    }

    @AfterClass
    public void afterClass(){

        System.out.println("我是AfterClass");
    }

    @BeforeMethod
    public void beforeMethod() {

        System.out.println("我是BeforeMethod");
    }

    @AfterMethod
    public void  afterMethod(){

        System.out.println("我是AfterMethod");
    }

    @BeforeSuite
    public void beforeSuite(){

        System.out.println("我是BeforeSuite");
    }

    @AfterSuite
    public void afterSuite(){

        System.out.println("我是AfterSuite");
    }

    @BeforeTest
    public void beforeTest(){

        System.out.println("我是BeforeTest");
    }

    @AfterTest
    public void afterTest(){

        System.out.println("我是AfterTest");
    }

    @AfterGroups("testGrops")
    public void afterGroups(){

        System.out.println("我是AfterGroups");
    }

    @BeforeGroups("testGrops")
    public void beforeGroups(){

        System.out.println("我是BeforeGroups");

    }



}


/**
 * 运行结果：
 *
 * 我是BeforeSuite
 * 我是BeforeTest
 * 我是BeforeClass
 * 我是BeforeMethod
 * 这是一个测试用例1
 * 我是AfterMethod
 * 我是BeforeMethod
 * 这是一个测试用例2
 * 我是AfterMethod
 * 我是BeforeGroups
 * 我是BeforeMethod
 * 这是一个分组的测试用例4
 * 我是AfterMethod
 * 我是AfterGroups
 * 我是AfterClass
 * 我是AfterTest
 * 我是AfterSuite
 */
