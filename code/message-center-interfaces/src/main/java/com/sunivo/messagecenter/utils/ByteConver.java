/**
 * 
 */
package com.sunivo.messagecenter.utils;

import java.util.List;

import org.nutz.json.Json;

/**
 * 二进制转换
 * 
 * @author chengjianfang@sunivo.com
 * 
 *         2013年12月18日 下午4:59:23
 */
public class ByteConver {
    /**
     * byte数组转化为字符串
     * 
     * @param bytes
     *            byte数组
     * @return 字符串
     * @throws Exception
     *             编码错误
     */
    public static final String conver(byte[] bytes) throws Exception {
        return Json.toJson(bytes);
    }

    /**
     * 字符串转化为byte数组
     * 
     * @param content
     *            字符串
     * @return byte数组
     * @throws Exception
     *             编码错误
     */
    public static final byte[] reConver(String content) throws Exception {
        @SuppressWarnings("unchecked")
        List<Integer> integers = Json.fromJson(List.class, content);
        int arrayLen = integers.size();
        byte[] bytes = new byte[arrayLen];
        for (int index = 0; index < arrayLen; index++) {
            bytes[index] = integers.get(index).byteValue();
        }
        return bytes;
    }
}
