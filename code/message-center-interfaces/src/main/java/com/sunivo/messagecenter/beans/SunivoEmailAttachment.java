/**
 * 
 */
package com.sunivo.messagecenter.beans;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

/**
 * @author chengjianfang@sunivo.com
 * 
 *         2013年12月12日 下午3:26:27
 */
public class SunivoEmailAttachment implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * 附件名称
     */
    private final String name;
    /**
     * 附件内容编号
     */
    private final String contentId;
    /**
     * 附件内容
     */
    private final String content;
    /**
     * 附件类型
     */
    private final String contentType;

    /**
     * 构建邮件附件
     * 
     * @param name
     *            附件名称
     * @param contentId
     *            附件内容编号
     * @param content
     *            附件内容
     * @param contentType
     *            附件类型
     * @throws Exception
     */
    public SunivoEmailAttachment(String name, String contentId, String content,
            String contentType) throws Exception {
        assert StringUtils.isNotBlank(name) : "附件名称不能为空";
        assert StringUtils.isNotBlank(contentId) : "附件内容编号不能为空";
        assert StringUtils.isNotEmpty(content) : "附件内容不能为空";
        assert StringUtils.isNotBlank(contentType) : "附件类型不能为空";
        this.name = name;
        this.contentId = contentId;
        this.content = content;
        this.contentType = contentType;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the contentId
     */
    public String getContentId() {
        return contentId;
    }

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @return the contentType
     */
    public String getContentType() {
        return contentType;
    }
}
