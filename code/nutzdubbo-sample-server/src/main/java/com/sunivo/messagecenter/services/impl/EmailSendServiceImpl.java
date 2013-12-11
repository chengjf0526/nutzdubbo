/**
 * 
 */
package com.sunivo.messagecenter.services.impl;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

import jodd.mail.Email;
import jodd.mail.EmailAttachment;
import jodd.mail.EmailMessage;
import jodd.mail.SendMailSession;
import jodd.mail.SimpleAuthenticator;
import jodd.mail.SmtpServer;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.nutz.ioc.loader.annotation.IocBean;

import com.alibaba.dubbo.config.annotation.Service;
import com.sunivo.messagecenter.beans.EmailObject;
import com.sunivo.messagecenter.beans.Message;
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
     * 待发邮件列表
     */
    private static final BlockingQueue<EmailObject> EMAIL_OBJECT_QUEUE = new LinkedBlockingDeque<EmailObject>();

    static {
        new Thread() {
            public void run() {
                try {
                    EmailObject emailObject = EMAIL_OBJECT_QUEUE.take();
                    if (null != emailObject) {
                        Email email = buildEmail(emailObject);
                        SendMailSession session = buildSession();
                        session.open();
                        session.sendMail(email);
                        session.close();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            /**
             * @return
             */
            private SendMailSession buildSession() {
                SmtpServer server = new SmtpServer("smtp.sunivo.com", 25,
                        new SimpleAuthenticator("chengjianfang@sunivo.com",
                                "chengjf0526@"));
                SendMailSession session = server.createSession();
                return session;
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
                EmailAttachment[] attachments = emailObject.getAttachments();
                if (ArrayUtils.isNotEmpty(attachments)) {
                    for (EmailAttachment emailAttachment : attachments) {
                        email.attach(emailAttachment);
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
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
