/**
 * 
 */
package com.sunivo.messagecenter.beans;

import java.io.Serializable;

/**
 * @author chengjianfang@sunivo.com
 * 
 *         2013年12月11日 上午10:17:21
 */
public final class Message implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * 消息类型
     * 
     * @see jodd.util.MimeTypes
     */
    private String mimeType;

    /**
     * 消息内容
     */
    private String content;

    /**
     * @return the mimeType
     */
    public String getMimeType() {
        return mimeType;
    }

    /**
     * @param mimeTypes
     *            the mimeTypes to set
     */
    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content
     *            the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }
}
