package com.zmh.redis.util.LowUp;

import cn.hutool.core.util.RandomUtil;

public class LowUpChar {

    public static String ReturnActionUpDownSystem(String code){
        StringBuffer stringBuffer=new StringBuffer();
        for(int i=0;i<code.length();i++){
            char charToArray;
            if(code.charAt(i) >= 'a' && code.charAt(i) <= 'z'){
                    /*小写转大写*/
                    if(getNumberToChar() >= 5){
                        /*50%的概率触发*/
                        charToArray = (char) (code.charAt(i) - 32);
                        stringBuffer.append(charToArray);
                    }else {
                        stringBuffer.append(code.charAt(i));
                    }
            }else if(code.charAt(i) >= 'A' && code.charAt(i) <= 'Z'){
                    /*大写转小写*/
                    if(getNumberToChar() >= 5){
                        /*50%的概率触发*/
                        charToArray = (char) (code.charAt(i) + 32);
                        stringBuffer.append(charToArray);
                    }else {
                        stringBuffer.append(code.charAt(i));
                    }
            }else {
                stringBuffer.append(code.charAt(i));
            }
        }
        return stringBuffer.toString();
    }


    public static Integer getNumberToChar(){
        return RandomUtil.randomInt(10);
    }

}
