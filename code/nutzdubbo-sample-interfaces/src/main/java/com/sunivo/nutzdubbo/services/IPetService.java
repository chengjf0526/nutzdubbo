/**
 * 
 */
package com.sunivo.nutzdubbo.services;

import java.util.Calendar;

import com.sunivo.nutzdubbo.beans.Pet;

/**
 * @author chengjianfang@sunivo.com
 * 
 *         2013年12月6日 下午9:31:05
 */
public interface IPetService {
    /**
     * 创建对象使用默认参数
     * 
     * @return 实例
     */
    Pet createPet();

    /**
     * 创建对象并设置名字
     * 
     * @param name
     *            名字
     * @return 实例
     */
    Pet createPet(String name);

    /**
     * 创建对象并设置名字和生日
     * 
     * @param name
     *            名字
     * @param birthday
     *            生日
     * @return 实例
     */
    Pet createPet(String name, Calendar birthday);

    /**
     * 创建对象并设置名字和生日和朋友
     * 
     * @param name
     *            名字
     * @param birthday
     *            生日
     * @param friend
     *            朋友
     * @return 实例
     */
    Pet createPet(String name, Calendar birthday, Pet friend);
}
