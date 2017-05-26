package com.emin.igwmp.pcs.base;

import com.emin.igwmp.pcs.ProductPriceTest;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Administrator on 2017/3/21.
 */
public class InitRunTest {
    public static void main(String[] pArgs){
        ClassPathXmlApplicationContext c = new ClassPathXmlApplicationContext("spring-servlet.xml");
        c.start();
        new ProductPriceTest().AddTest();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                }
            }
        }).start();
        c.close();

    }
}
