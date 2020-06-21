package com.zstu.mijazz.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import org.springframework.stereotype.Service;

/**
 * @author MijazzChan
 * @stuID ZSTU.2017326603075
 * Created on 21-Jun-20.
 */
public class PinYinUtil {
    public static String getHeadPinYin(String word) {
        StringBuffer headPinyin = new StringBuffer();
        Character firstChar = word.charAt(0);
        String[] pinYinArray = null;
        if (firstChar > 128) {
            pinYinArray = PinyinHelper.toHanyuPinyinStringArray(firstChar);
            if (pinYinArray != null) {
                headPinyin.append(pinYinArray[0].charAt(0));
            } else {
                headPinyin.append("#");
            }
        } else {
            if (Character.isLetter(firstChar)) {
                headPinyin.append(firstChar);
            }else {
                headPinyin.append("#");
            }
        }
        return headPinyin.toString().toUpperCase();
    }
}
