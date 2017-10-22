/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unittestproject;

import java.util.Arrays;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jimstewart
 */
public class UnitTestProjectTest {
    
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of main method, of class UnitTestProject.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        UnitTestProject.main(args);
        // TODO review the generated test code and remove the default call to fail.
        assertTrue(1>0);
    }
    
    class NumberListTest{
        public int[] list;
        NumberListTest(int[] list){
            this.list = list; 
        }
    }
    
    @Test
    public void testNumberList(){
        UnitTestProject utp = new UnitTestProject();
        NumberListTest[] tests = new NumberListTest[5];
        int[] test0 = {0};
        tests[0] = new NumberListTest(test0);
        int [] test1 = {2,3,4};
        tests[1] = new NumberListTest(test1);
        int[] test2 = {-10, -9, -8, -7, -6};
        tests[2] = new NumberListTest(test2);
        int [] test3 = {Integer.MAX_VALUE};
        tests[3] = new NumberListTest(test3);
        int [] test4 = {Integer.MIN_VALUE, Integer.MIN_VALUE+1, Integer.MIN_VALUE+2, Integer.MIN_VALUE+3};
        tests[4] = new NumberListTest(test4);
        for(NumberListTest test:tests){
            int[] result = utp.numberList(test.list[0], test.list.length);
            if(Arrays.equals(test.list, result)){
                System.out.println("Test passed");
                
            } else {
                System.out.println("Passed list:");
                for(int i:test.list)
                    System.out.printf("%d ", i);
                System.out.println();
                System.out.println("Received list:");
                for(int i:result)
                    System.out.printf("%d ", i);
                System.out.println();
            }
            assertArrayEquals(test.list, result);
        }
        
    }
}
