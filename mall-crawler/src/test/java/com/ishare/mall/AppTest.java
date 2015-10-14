package com.ishare.mall;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest
        extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(AppTest.class);
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp() {

        String s = "瑞峰电器专营店";
        byte[] bytes = {-17, -65, -67, -17, -65, -67, -17, -65, -67, -17, -65, -67, -17, -65, -67, -17, -65, -67, -41, -88, -45, -86, -17, -65, -67, -17, -65, -67};
        try {

            System.out.println("1 " + s);
            System.out.println("2 " + new String(bytes));
            System.out.println("3 " + new String(bytes, "UTF-8"));
            System.out.println("4 " + new String(bytes, "GBK"));
            System.out.println("5 " + new String(bytes, "ISO-8859-1"));

            System.out.println("1");
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertTrue(true);
    }
}
