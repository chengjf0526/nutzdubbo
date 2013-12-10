package com.sunivo.nutzdubbo.beans;

import java.io.Serializable;
import java.util.Calendar;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

/**
 * 测试实体
 * 
 * @author chengjianfang@sunivo.com
 * 
 *         2013年12月6日 下午8:52:27
 */
// 当name为类名首字母小写，则可以不写name属性
@IocBean(name = "pet", singleton = false)
public class Pet implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Inject("defaultName")
    private String name;

    @Inject("java:java.util.Calendar.getInstance")
    private Calendar birthday;

    private Pet friend;

    private String version;

    public Pet() {
    }

    public Pet(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Calendar getBirthday() {
        return birthday;
    }

    public void setBirthday(Calendar birthday) {
        this.birthday = birthday;
    }

    public Pet getFriend() {
        return friend;
    }

    public void setFriend(Pet friend) {
        this.friend = friend;
    }

    /**
     * @return the version
     */
    public String getVersion() {
        return version;
    }

    /**
     * @param version
     *            the version to set
     */
    public void setVersion(String version) {
        this.version = version;
    }
}