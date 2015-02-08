package com.xxfff.core.util;

import com.xxfff.core.model.CommonStatus;

public class StringUtils extends org.apache.commons.lang.StringUtils {
	public static String toFirstUpperCase(String str) {
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}
	
	public static String toFirstLowerCase(String str) {
		return str.substring(0, 1).toLowerCase() + str.substring(1);
	}
	
	public static Integer countIgnoreCase(String o,String f){
		o = o.toLowerCase();
		f = f.toLowerCase();
		return countMatches(o, f);
	}

	public static boolean isInteger(String s) {
		return s.matches("\\d+");
	}
	
	//功能：根据用户输入的Email跳转到相应的电子邮箱首页
    public static String getEmail(String mail) {
        String suff = mail.split("@")[1];
        suff = suff.toLowerCase();
        if (suff.equals("163.com")) {
            return "mail.163.com";
        } else if (suff.equals("vip.163.com")) {
            return "vip.163.com";
        } else if (suff.equals("126.com")) {
            return "mail.126.com";
        } else if (suff.equals("qq.com") || suff.equals("vip.qq.com") || suff.equals("foxmail.com")) {
            return "mail.qq.com";
        } else if (suff.equals("gmail.com")) {
            return "mail.google.com";
        } else if (suff.equals("sohu.com")) {
            return "mail.sohu.com";
        } else if (suff.equals("tom.com")) {
            return "mail.tom.com";
        } else if (suff.equals("vip.sina.com")) {
            return "vip.sina.com";
        } else if (suff.equals("sina.com.cn") || suff.equals("sina.com")) {
            return "mail.sina.com.cn";
        } else if (suff.equals("tom.com")) {
            return "mail.tom.com";
        } else if (suff.equals("yahoo.com.cn") || suff.equals("yahoo.cn")) {
            return "mail.cn.yahoo.com";
        } else if (suff.equals("tom.com")) {
            return "mail.tom.com";
        } else if (suff.equals("yeah.net")) {
            return "www.yeah.net";
        } else if (suff.equals("21cn.com")) {
            return "mail.21cn.com";
        } else if (suff.equals("hotmail.com")) {
            return "www.hotmail.com";
        } else if (suff.equals("sogou.com")) {
            return "mail.sogou.com";
        } else if (suff.equals("188.com")) {
            return "www.188.com";
        } else if (suff.equals("139.com")) {
            return "mail.10086.cn";
        } else if (suff.equals("189.cn")) {
            return "webmail15.189.cn/webmail";
        } else if (suff.equals("wo.com.cn")) {
            return "mail.wo.com.cn/smsmail";
        } else if (suff.equals("139.com")) {
            return "mail.10086.cn";
        } else {
            return "";
        }
    };
    
    public static String getExt(String fileName){
    	int index = fileName.lastIndexOf(".");
		if(index < 0){
			return "";
		}
		return fileName.substring(index + 1);
    }
}
