package com.zc.util.common;

import java.util.Random;

public class VerifyCodeUtils {

    public static final int VERIFY_SIZE = 5;
    public static final String VERIFY_CODES = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /**
     * 使用系统默认大小（5）和字符源生成验证码
     * @return
     */
    public static String generateVerifyCode(){
        return generateVerifyCode(VERIFY_SIZE, VERIFY_CODES);
    }

    /**
     * 使用系统默认字符源生成验证码
     * @param verifySize    验证码长度
     * @return
     */
    public static String generateVerifyCode(int verifySize){
        return generateVerifyCode(verifySize, VERIFY_CODES);
    }

    /**
     * 使用指定源生成验证码
     * @param verifySize    验证码长度
     * @param sources   验证码字符源
     * @return
     */
    public static String generateVerifyCode(int verifySize, String sources){
        if(sources == null || sources.length() == 0){
            sources = VERIFY_CODES;
        }
        int codesLen = sources.length();
        Random rand = new Random(System.currentTimeMillis());
        StringBuilder verifyCode = new StringBuilder(verifySize);
        for(int i = 0; i < verifySize; i++){
            verifyCode.append(sources.charAt(rand.nextInt(codesLen - 1)));
        }
        return verifyCode.toString();
    }

}
