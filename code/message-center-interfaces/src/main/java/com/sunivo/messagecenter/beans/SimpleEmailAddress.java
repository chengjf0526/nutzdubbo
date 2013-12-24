/**
 * 
 */
package com.sunivo.messagecenter.beans;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

import com.sunivo.messagecenter.utils.Base64;

/**
 * Email地址
 * 
 * @author chengjianfang@sunivo.com
 * 
 *         2013年12月24日 下午5:07:50
 */
public class SimpleEmailAddress implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 构造Email地址
     * @param name 显示名称
     * @param email 邮箱地址
     */
    public SimpleEmailAddress(String name, String email) {
        super();
        assert StringUtils.isNotEmpty(name) : "显示名称或邮箱地址不能为空";
        assert StringUtils.isNotEmpty(email) : "邮箱地址不能为空";
        this.name = name;
        this.email = email;
    }

    /**
     * 构造Email地址<br>显示名称和邮箱地址相同
     * @param email 邮箱地址
     */
    public SimpleEmailAddress(String email) {
        this(email, email);
    }

    /**
     * 显示名称
     */
    private String name;

    /**
     * 邮箱地址
     */
    private String email;

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        SimpleEmailAddress other = (SimpleEmailAddress) obj;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        try {
            return "=?UTF-8?B?"
                    + new String(Base64.encode(name.getBytes("UTF-8")), "UTF-8")
                    + "?= <" + email + ">";
        } catch (Exception ex) {
            return name + " <" + email + ">";
        }
    }
}
