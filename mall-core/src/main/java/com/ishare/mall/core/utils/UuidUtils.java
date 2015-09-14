package com.ishare.mall.core.utils;

import java.util.Date;
import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;

public class UuidUtils {
 /**
  * @description:随机获取key值
  * @return
  */
 private String guid() {
  UUID uuid = UUID.randomUUID();
  String key = uuid.toString();
  return key;
 }
 /**
  * 这是其中一个url的参数，是GUID的，全球唯一标志符
  * 
  * @return
  */
 public String App_Id() {
	 UuidUtils g = new UuidUtils();
  String guid = g.guid();
  
  StringBuilder sb = new StringBuilder();
  char c = '-';
  for (int i = 0; i < guid.length(); i++) {
      char ch = guid.charAt(i);
      if (ch != c) {
          sb.append(ch);
      			}
      }
  
  String app_Id = sb.toString();
  return app_Id;
 }
 /**
  * 根据md5加密
  * @param date 当前日期
  	 *  加密方式为 日期3-10位 + app_key + 日期2-6位 通过md5加密    
  * @return
  */
 public String App_screct(String date, String app_Id) {
	String datePrefix = date.substring(3, 10);
	String dateSuffix = date.substring(2, 6);
  String mw = datePrefix + app_Id + dateSuffix;
  String app_screct = DigestUtils.md5Hex(mw).toUpperCase();
  return app_screct;
 }

 public static void main(String[] args) {  
	 UuidUtils gd = new UuidUtils(); 
  String app_Id=gd.App_Id(); 
  System.out.println("app_key: "+app_Id);
  String app_screct=gd.App_screct(String.valueOf(new Date().getTime()), app_Id);    
  System.out.println("app_screct: "+app_screct);  
 }
}