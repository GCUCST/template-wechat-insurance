package com.cst;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

public class App2 {


    public static int[] twoSum1(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (target - nums[i] == nums[j]) {
                    return new int[]{i,j};
                }
            }
        }
        return null;
    }


    public static void main(String[] args) {
        int[] nums = new int[]{2, 7, 11, 15};
        int target = 26;
        int[] ints = twoSum1(nums, target);
        System.out.println(ints[0]+"  "+ints[1]);


//        String a = new StringBuilder("ja").append("va").toString();
//        String b = new StringBuilder("aa").append("gg").toString();
//        String bb = new StringBuilder("aagg1").append("123").toString();
//        bb.intern();
//
//        a.intern();
//        b.intern();
//
//        System.out.println(a.intern() == a);
//        System.out.println(b.intern() == b);
//        System.out.println(bb == bb.intern());
    }

}
