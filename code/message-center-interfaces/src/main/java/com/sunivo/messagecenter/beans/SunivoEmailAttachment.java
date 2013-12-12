/**
 * 
 */
package com.sunivo.messagecenter.beans;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import org.apache.commons.lang3.ArrayUtils;
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
    private byte[] content;
    /**
     * 附件内容输入流
     */
    private InputStream inputStream;
    /**
     * 附件类型
     */
    private String contentType;
    /**
     * 附件文件
     */
    private File file;

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
     */
    public SunivoEmailAttachment(String name, String contentId, byte[] content,
            String contentType) {
        assert StringUtils.isNotBlank(name) : "附件名称不能为空";
        assert StringUtils.isNotBlank(contentId) : "附件内容编号不能为空";
        assert ArrayUtils.isNotEmpty(content) : "附件内容不能为空";
        assert StringUtils.isNotBlank(contentType) : "附件类型不能为空";
        this.name = name;
        this.contentId = contentId;
        this.content = content;
        this.contentType = contentType;
    }

    /**
     * 构建邮件附件
     * 
     * @param name
     *            附件名称
     * @param contentId
     *            附件内容编号
     * @param file
     *            附件文件
     */
    public SunivoEmailAttachment(String name, String contentId, File file) {
        assert StringUtils.isNotBlank(name) : "附件名称不能为空";
        assert StringUtils.isNotBlank(contentId) : "附件内容编号不能为空";
        assert null != file : "附件文件不能为空";
        assert !(file.exists() && file.isFile()) : "附件文件不存在或是目录";
        this.name = name;
        this.contentId = contentId;
        this.file = file;
    }

    /**
     * 构建邮件附件
     * 
     * @param name
     *            附件名称
     * @param contentId
     *            附件内容编号
     * @param inputStream
     *            附件内容输入流
     * @param contentType
     *            附件类型
     * @throws IOException
     */
    public SunivoEmailAttachment(String name, String contentId,
            InputStream inputStream, String contentType) throws IOException {
        assert StringUtils.isNotBlank(name) : "附件名称不能为空";
        assert StringUtils.isNotBlank(contentId) : "附件内容编号不能为空";
        assert null != inputStream : "附件内容输入流不能为空";
        assert StringUtils.isNotBlank(contentType) : "附件类型不能为空";
        this.name = name;
        this.contentId = contentId;
        this.inputStream = inputStream;
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
    public byte[] getContent() {
        return content;
    }

    /**
     * @param content
     *            the content to set
     */
    public void setContent(byte[] content) {
        this.content = content;
    }

    /**
     * @return the inputStream
     */
    public InputStream getInputStream() {
        return inputStream;
    }

    /**
     * @param inputStream
     *            the inputStream to set
     */
    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    /**
     * @return the contentType
     */
    public String getContentType() {
        return contentType;
    }

    /**
     * @param contentType
     *            the contentType to set
     */
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    /**
     * @return the file
     */
    public File getFile() {
        return file;
    }

    /**
     * @param file
     *            the file to set
     */
    public void setFile(File file) {
        this.file = file;
    }
}
