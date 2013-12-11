/**
 * 
 */
package com.sunivo.messagecenter.services;

import com.sunivo.messagecenter.beans.EmailObject;

/**
 * @author chengjianfang@sunivo.com
 * 
 *         2013年12月11日 上午9:57:49
 */
public interface IEmailSendService {
    /**
     * 发送邮件到消息中心
     * 
     * @param emailObject
     *            邮件体
     * @return 消息唯一码
     */
    String sendEmail(EmailObject emailObject);
}
