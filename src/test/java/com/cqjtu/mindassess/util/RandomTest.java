package com.cqjtu.mindassess.util;

import java.util.Random;

public class RandomTest {
    public static void main(String[] args) {
        Random random = new Random(System.nanoTime());
        int[] temp = new int[10];
        for (int i = 0; i < 100; i++) {
            int r = random.nextInt(10);
            temp[r]++;
        }
        for (int i = 0; i < temp.length; i++) {
            System.out.println(i + "count: " + temp[i]);
        }
    }
}
