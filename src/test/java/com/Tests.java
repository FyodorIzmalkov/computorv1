package com;

import com.compuctor.Main;
import org.junit.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
public class Tests {


    @Test// wrong test
    public void test12() {
        String testStr = "5 * X^0 + 4 * X^1 - 9.3 * X^2 = 1 * X^0";
        Main.parseStringToMap(testStr);
        System.out.println(Main.getMap());
    }
}
