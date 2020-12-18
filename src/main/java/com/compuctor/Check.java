package com.compuctor;

import static com.compuctor.Main.sqrt;

public class Check {
    public static void main(String[] args) {
        String str = "";
         char chr = '1';
         str += chr;
         str += chr;
        System.out.println(str);
        System.out.println(Long.parseLong("+111111111"));

        double dl = 2.5;
        double dl1 = 2.0;

        System.out.println(dl / (int)dl);
        System.out.println(dl1 / (int)dl1);
        System.out.println(dl1 % 1 == 0);
        System.out.println(dl % 1 == 0);
        Double dabl = 5.0;
        System.out.println(dabl % 1 == 0 ? dabl.longValue() : dabl);
        System.out.println(dabl % 1 == 0 ? dabl : dabl.longValue());
        System.out.println(sqrt(4));
        System.out.println(sqrt(9));
        System.out.println(sqrt(25));
        System.out.println(sqrt(17));
    }
}
