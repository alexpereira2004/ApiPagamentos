package br.com.lunacom.tools.util;

import java.util.Random;

public class RandomData {
    public static String generateLimitedInteger(int length){
        String possibility = new String("0123456789");
        int n = possibility.length();
        String result = new String();
        Random r = new Random();
        for (int i = 0; i < length; i++) {
            result = result + possibility.charAt(r.nextInt(n));
        }
        return result;
    }
}
