/**
 * 
 */
package com.sunivo.messagecenter.beans;

import java.io.Serializable;

/**
 * @author chengjianfang@sunivo.com
 * 
 *         2013年12月11日 上午10:13:31
 */
public final class EmailObject implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * 邮件发送者
     */
    private String from;

    /**
     * 邮件接受者
     */
    private String[] tos;

    /**
     * 邮件抄送接受者
     */
    private String[] ccs;

    /**
     * 邮件密送接受者
     */
    private String[] bccs;

    /**
     * 邮件标题
     */
    private String subject;

    /**
     * 邮件消息内容
     */
    private Message[] messages;

    /**
     * 邮件附件内容
     */
    private SunivoEmailAttachment[] attachments;

    /**
     * @return the from
     */
    public String getFrom() {
        return from;
    }

    /**
     * @param from
     *            the from to set
     */
    public void setFrom(String from) {
        this.from = from;
    }

    /**
     * @return the tos
     */
    public String[] getTos() {
        return tos;
    }

    /**
     * @param tos
     *            the tos to set
     */
    public void setTos(String... tos) {
        this.tos = tos;
    }

    /**
     * @return the ccs
     */
    public String[] getCcs() {
        return ccs;
    }

    /**
     * @param ccs
     *            the ccs to set
     */
    public void setCcs(String... ccs) {
        this.ccs = ccs;
    }

    /**
     * @return the bccs
     */
    public String[] getBccs() {
        return bccs;
    }

    /**
     * @param bccs
     *            the bccs to set
     */
    public void setBccs(String... bccs) {
        this.bccs = bccs;
    }

    /**
     * @return the subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * @param subject
     *            the subject to set
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * @return the messages
     */
    public Message[] getMessages() {
        return messages;
    }

    /**
     * @param messages
     *            the messages to set
     */
    public void setMessages(Message... messages) {
        this.messages = messages;
    }

    /**
     * @return the attachments
     */
    public SunivoEmailAttachment[] getAttachments() {
        return attachments;
    }

    /**
     * @param attachments
     *            the attachments to set
     */
    public void setAttachments(SunivoEmailAttachment... attachments) {
        this.attachments = attachments;
    }
}
