/**
 * 
 */
package com.sunivo.messagecenter.services.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

import javax.activation.DataSource;
import javax.mail.util.ByteArrayDataSource;

import jodd.mail.Email;
import jodd.mail.EmailMessage;
import jodd.mail.SendMailSession;
import jodd.mail.SimpleAuthenticator;
import jodd.mail.SmtpServer;
import jodd.mail.att.DataSourceAttachment;
import jodd.util.MimeTypes;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.dubbo.config.annotation.Service;
import com.sunivo.messagecenter.beans.EmailObject;
import com.sunivo.messagecenter.beans.Message;
import com.sunivo.messagecenter.beans.SimpleEmailAddress;
import com.sunivo.messagecenter.beans.SunivoEmailAttachment;
import com.sunivo.messagecenter.services.IEmailSendService;
import com.sunivo.messagecenter.utils.ByteConver;

/**
 * @author chengjianfang@sunivo.com
 * 
 *         2013年12月11日 上午10:31:17
 */
@IocBean(name = "emailSendService")
@Service(version = "1.0.0")
public class EmailSendServiceImpl implements IEmailSendService {
    /**
     * 日志记录器
     */
    private static final Logger LOGGER = LoggerFactory
            .getLogger(EmailSendServiceImpl.class);

    /**
     * 待发邮件列表(双向队列，如果有急需发送的邮件，可以直接放在队列头)
     */
    private static final BlockingDeque<EmailObject> EMAIL_OBJECT_QUEUE = new LinkedBlockingDeque<EmailObject>();

    static {
        new Thread() {
            public void run() {
                while (true) {
                    try {
                        SendMailSession session = null;
                        EmailObject emailObject = EMAIL_OBJECT_QUEUE.take();
                        if (null != emailObject) {
                            List<EmailObject> tmpEmailObjectList = new ArrayList<EmailObject>();
                            // 取不多于10个
                            tmpEmailObjectList.add(emailObject);
                            for (int index = 0; index < 10; index++) {
                                // 获取邮件对象，如果不存在则结束获取
                                try {
                                    emailObject = EMAIL_OBJECT_QUEUE.remove();
                                    tmpEmailObjectList.add(emailObject);
                                } catch (Exception ex) {
                                    break;
                                }
                            }
                            try {
                                LOGGER.debug("开始构建会话");
                                session = buildSession();
                                LOGGER.debug("打开会话");
                                session.open();
                                for (EmailObject tmpEmailObject : tmpEmailObjectList) {
                                    LOGGER.info(Json.toJson(tmpEmailObject));
                                    LOGGER.debug("开始构建邮件");
                                    Email email = buildEmail(tmpEmailObject);
                                    LOGGER.debug("发送邮件");
                                    session.sendMail(email);
                                }
                            } catch (Exception ex) {
                                LOGGER.error(ex.getMessage(), ex);
                            } finally {
                                LOGGER.debug("关闭连接");
                                if (null != session) {
                                    session.close();
                                }
                                session = null;
                            }
                        }
                        sleep(10000);
                    } catch (Exception ex) {
                        LOGGER.debug("暂停失败", ex);
                    }
                }
            }

            /**
             * @return
             */
            private SendMailSession buildSession() {
                SmtpServer server = new SmtpServer("smtp.163.com", 25,
                        new SimpleAuthenticator("USER@163.com",
                                "PASSWORD"));
                SendMailSession session = server.createSession();
                return session;
            }

            /**
             * 构建邮件附件数据源
             * 
             * @param emailAttachment
             * @return
             * @throws Exception
             */
            public DataSource buildDataSource(
                    SunivoEmailAttachment emailAttachment) throws Exception {
                byte[] content = ByteConver.reConver(emailAttachment
                        .getContent());
                String contentType = emailAttachment.getContentType();
                if (null == contentType) {
                    return new ByteArrayDataSource(content,
                            MimeTypes.MIME_APPLICATION_OCTET_STREAM);
                } else {
                    return new ByteArrayDataSource(content, contentType);
                }
            }

            /**
             * @param emailObject
             * @throws Exception
             */
            private Email buildEmail(EmailObject emailObject) throws Exception {
                Email email = Email.create();
                SimpleEmailAddress from = emailObject.getFrom();
                if (null != from) {
                    email.setFrom(from.toString());
                }
                email.setTo(simpleEmailAddressArray2StringArray(emailObject
                        .getTos()));
                email.setCc(simpleEmailAddressArray2StringArray(emailObject
                        .getCcs()));
                email.setBcc(simpleEmailAddressArray2StringArray(emailObject
                        .getBccs()));
                String subject = emailObject.getSubject();
                if (StringUtils.isNotEmpty(subject)) {
                    email.setSubject(subject);
                }
                Message[] messages = emailObject.getMessages();
                if (ArrayUtils.isNotEmpty(messages)) {
                    for (Message message : messages) {
                        email.addMessage(new EmailMessage(message.getContent(),
                                message.getMimeType(), "UTF-8"));
                    }
                }
                SunivoEmailAttachment[] attachments = emailObject
                        .getAttachments();
                if (ArrayUtils.isNotEmpty(attachments)) {
                    for (SunivoEmailAttachment emailAttachment : attachments) {
                        try {
                            email.attach(new DataSourceAttachment(
                                    buildDataSource(emailAttachment),
                                    emailAttachment.getName(), emailAttachment
                                            .getContentId()));
                        } catch (IOException ex) {
                            LOGGER.error("添加附件失败，该附件将被忽略", ex);
                        }
                    }
                }
                return email;
            }

            /**
             * 将SimpleEmailAddress列表转换为字符串列表
             * 
             * @param simpleEmailAddresses
             *            SimpleEmailAddress列表
             * @return 字符串列表<li>null if simpleEmailAddresses is null or empty
             */
            private String[] simpleEmailAddressArray2StringArray(
                    SimpleEmailAddress[] simpleEmailAddresses) {
                String[] strings = null;
                if (ArrayUtils.isNotEmpty(simpleEmailAddresses)) {
                    int simpleEmailAddressesLen = simpleEmailAddresses.length;
                    strings = new String[simpleEmailAddressesLen];
                    for (int index = 0; index < simpleEmailAddressesLen; index++) {
                        strings[index] = simpleEmailAddresses[index].toString();
                    }
                }
                return strings;
            }
        }.start();
    }

    public String sendEmail(EmailObject emailObject) {
        try {
            // 如果优先发送，将消息放在队列头
            if (emailObject.isFirstSend()) {
                EMAIL_OBJECT_QUEUE.offerFirst(emailObject);
            }
            // 如果不优先发送，正常放置于队列尾
            else {
                EMAIL_OBJECT_QUEUE.offer(emailObject);
            }
            return "唯一标识";
        } catch (Exception ex) {
            LOGGER.error("放入邮件队列失败", ex);
            return null;
        }
    }
}
