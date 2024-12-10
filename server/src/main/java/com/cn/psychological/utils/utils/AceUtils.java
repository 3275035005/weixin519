package com.cn.psychological.utils.utils;

import javax.servlet.http.HttpServletRequest;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.*;
import static java.util.Map.Entry.comparingByValue;

import static java.util.stream.Collectors.toMap;

/**
 * 工具类
 * @version 1.0
 */
public class AceUtils {

    public static String getMapOne(Map<String, Integer> map) {
        // 按值排序降序
        Map<String, Integer> mapOne =   map
                .entrySet()
                .stream()
                .sorted(Collections.reverseOrder(comparingByValue()))
                .collect(
                        toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
                                LinkedHashMap::new));

        Set<String> keys = mapOne.keySet();
        StringBuilder types = new StringBuilder();
        int i = 0;
        for (String key : keys) {
            if(i == 4){
               break;
            }
            types.append(key);
            i++;
        }
        System.out.println("===============================");
        System.out.println(types);
        return ifString(types.toString());

    }

    public static String ifString(String str1){

        if(checkStrings(str1, "istj")){
            return "istj";
        }else if(checkStrings(str1, "isfj")){
            return "isfj";
        }else if(checkStrings(str1, "infj")){
            return "infj";
        }else if(checkStrings(str1, "intj")){
            return "intj";
        }else if(checkStrings(str1, "istp")){
            return "istp";
        }else if(checkStrings(str1, "isfp")){
            return "isfp";
        }else if(checkStrings(str1, "infp")){
            return "infp";
        }else if(checkStrings(str1, "intp")){
            return "intp";
        }else if(checkStrings(str1, "estp")){
            return "estp";
        }else if(checkStrings(str1, "esfp")){
            return "esfp";
        }else if(checkStrings(str1, "enfp")){
            return "enfp";
        }else if(checkStrings(str1, "entp")){
            return "entp";
        }else if(checkStrings(str1, "estj")){
            return "estj";
        }else if(checkStrings(str1, "esfj")){
            return "esfj";
        }else if(checkStrings(str1, "enfj")){
            return "enfj";
        }else if(checkStrings(str1, "entj")){
            return "entj";
        }

        return "";
    }

    private static boolean checkStrings(String i, String j) {
        if (i == null || j == null || i.length() != j.length()) {
            return false; // 长度不等或者其中一个字符串为空时返回false
        }
        String a;
        String b;
        if (i.length() > j.length()) {
            a = i;
            b = j;
        } else {
            a = j;
            b = i;
        }
        int count = 0;

        for (int bi = 0; bi < b.length(); bi++) {
            String[] bArr = b.split("");
            if (a.indexOf(bArr[bi]) != -1) {
                count++;
            } else {
                break;
            }
        }
        if (b.length() == count) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * 返回当前日期时间字符串<br>
     * 默认格式:yyyy-mm-dd hh:mm:ss
     *
     *
     * @return String 返回当前字符串型日期时间
     */
    public static String getCurrentTime() {
        String returnStr = null;
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        returnStr = f.format(date);
        return returnStr;
    }

    public static int getThisMonthDay(){
        Calendar calendar = Calendar.getInstance();
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    public static String getThisMonth() {
        String returnStr = null;
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM");
        Date date = new Date();
        returnStr = f.format(date);
        return returnStr;
    }


    public static String getThisDay() {
        String returnStr = null;
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        returnStr = f.format(date);
        return returnStr;
    }

    /***
     * MD5加码 生成32位md5码
     */
    public static String string2MD5(String inStr){
        MessageDigest md5 = null;
        try{
            md5 = MessageDigest.getInstance("MD5");
        }catch (Exception e){
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }
        char[] charArray = inStr.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte) charArray[i];
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++){
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();

    }


    /**
     * 获取IP
     * @param request
     * @return
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

}
