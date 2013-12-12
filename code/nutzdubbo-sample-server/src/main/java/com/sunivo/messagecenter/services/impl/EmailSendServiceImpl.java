/**
 * 
 */
package com.sunivo.messagecenter.services.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.util.ByteArrayDataSource;

import jodd.mail.Email;
import jodd.mail.EmailMessage;
import jodd.mail.SendMailSession;
import jodd.mail.SimpleAuthenticator;
import jodd.mail.SmtpServer;
import jodd.mail.att.DataSourceAttachment;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.nutz.ioc.loader.annotation.IocBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.dubbo.config.annotation.Service;
import com.sunivo.messagecenter.beans.EmailObject;
import com.sunivo.messagecenter.beans.Message;
import com.sunivo.messagecenter.beans.SunivoEmailAttachment;
import com.sunivo.messagecenter.services.IEmailSendService;

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
     * 待发邮件列表
     */
    private static final BlockingQueue<EmailObject> EMAIL_OBJECT_QUEUE = new LinkedBlockingDeque<EmailObject>();

    static {
        new Thread() {
            public void run() {
                while (true) {
                    try {
                        SendMailSession session = null;
                        EmailObject emailObject = EMAIL_OBJECT_QUEUE.take();
                        if (null != emailObject) {
                            try {
                                LOGGER.debug("开始构建会话");
                                session = buildSession();
                                LOGGER.debug("打开会话");
                                session.open();
                                LOGGER.debug("开始构建邮件");
                                Email email = buildEmail(emailObject);
                                LOGGER.debug("发送邮件");
                                session.sendMail(email);
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
                        new SimpleAuthenticator("chengjf0526@163.com",
                                "chengjf0526@"));
                SendMailSession session = server.createSession();
                return session;
            }

            /**
             * 构建邮件附件数据源
             * 
             * @param emailAttachment
             * @return
             * @throws IOException
             */
            public DataSource buildDataSource(
                    SunivoEmailAttachment emailAttachment) throws IOException {
                byte[] content = emailAttachment.getContent();
                InputStream inputStream = emailAttachment.getInputStream();
                File file = emailAttachment.getFile();
                String contentType = emailAttachment.getContentType();
                if (null == contentType) {
                    return new FileDataSource(file);
                } else {
                    if (null == content) {
                        return new ByteArrayDataSource(inputStream, contentType);
                    } else {
                        return new ByteArrayDataSource(content, contentType);
                    }
                }
            }

            /**
             * @param emailObject
             */
            private Email buildEmail(EmailObject emailObject) {
                Email email = Email.create();
                String from = emailObject.getFrom();
                if (StringUtils.isNotEmpty(from)) {
                    email.setFrom(from);
                }
                String[] tos = emailObject.getTos();
                if (ArrayUtils.isNotEmpty(tos)) {
                    email.setTo(tos);
                }
                String[] ccs = emailObject.getCcs();
                if (ArrayUtils.isNotEmpty(ccs)) {
                    email.setCc(ccs);
                }
                String[] bccs = emailObject.getBccs();
                if (ArrayUtils.isNotEmpty(bccs)) {
                    email.setBcc(bccs);
                }
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
        }.start();
    }

    public String sendEmail(EmailObject emailObject) {
        try {
            EMAIL_OBJECT_QUEUE.put(emailObject);
            return "唯一标识";
        } catch (Exception ex) {
            LOGGER.error("放入邮件队列失败", ex);
            return null;
        }
    }
}
