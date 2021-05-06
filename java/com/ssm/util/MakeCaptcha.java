package com.ssm.util;

import java.util.Random;

/**
 * 生成六位随机数的验证码
 * @author kneesh
 * @date 2021/4/27-11:17
 */
public class MakeCaptcha {
    public static String sixCaptcha(){
        Random random = new Random();
        StringBuilder result = new StringBuilder();
        for (int i=0;i<6;i++){
            result.append(random.nextInt(10));
        }
        System.out.println(result.toString());
        return result.toString();
    }

    public static void main(String[] args) {
        sixCaptcha();
    }
}
