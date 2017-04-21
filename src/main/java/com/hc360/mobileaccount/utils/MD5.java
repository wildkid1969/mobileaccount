package com.hc360.mobileaccount.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.SignatureException;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5 {

	public static final String KEY = "1bb762f7";
	
	public static String md5Encode(String inStr) throws Exception {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
			return "";
		}

		byte[] byteArray = inStr.getBytes("UTF-8");
		byte[] md5Bytes = md5.digest(byteArray);
		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16) {
				hexValue.append("0");
			}
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString().toLowerCase();
	}
	
	 /**
     * 字符串加密
     * @param content 需要加密的字符串
     * @param key 密钥
     * @param input_charset 编码格式
     * @return 加密结果
     */
    public static String sign(String content, String key, String input_charset) {
    	content = content + key;
        return DigestUtils.md5Hex(getContentBytes(content, input_charset));
    }
    
    /**
     * MD5验证
     * @param content 需要加密的字符串
     * @param sign 传过来的加密结果
     * @param key 密钥
     * @param input_charset 编码格式
     * @return 验证结果
     */
    public static boolean verify(String content, String sign, String key, String input_charset) {
    	content = content + key;
    	String mysign = DigestUtils.md5Hex(getContentBytes(content, input_charset));
    	if(mysign.equals(sign)) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }

    /**
     * @param content
     * @param charset
     * @return
     * @throws SignatureException
     * @throws UnsupportedEncodingException 
     */
    private static byte[] getContentBytes(String content, String charset) {
        if (charset == null || "".equals(charset)) {
            return content.getBytes();
        }
        try {
            return content.getBytes(charset);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("MD5签名过程中出现错误,指定的编码集不对,您目前指定的编码集是:" + charset);
        }
    }
	
	public static void main(String args[]) throws Exception {
        String str = new String("amigoxiexiexingxing");
        System.out.println("原始：" + str);
        System.out.println("MD5后：" + md5Encode(str));
        
        System.out.println(sign("123","123","utf-8"));
        System.out.println(verify("123",sign("123","123","utf-8"),"123","utf-8"));
    }

}
