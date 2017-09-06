package com.pigutu.app;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PigutuApplicationTests {

    @Test
    public void contextLoads() {
        String a = "1.00";
        System.out.println(Integer.parseInt(a));
    }

}
