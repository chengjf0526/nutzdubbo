package com.sunivo.nutzdubbo.beans;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Calendar;

import jodd.util.MimeTypes;

import org.nutz.json.Json;

import com.sunivo.messagecenter.beans.EmailObject;
import com.sunivo.messagecenter.beans.Message;
import com.sunivo.messagecenter.beans.SunivoEmailAttachment;
import com.sunivo.messagecenter.services.IEmailSendService;
import com.sunivo.messagecenter.utils.ByteConver;
import com.sunivo.nutzdubbo.services.IHelloService;
import com.sunivo.nutzdubbo.services.IPetService;
import com.sunivo.nutzdubbo.utils.ServiceFactory;

public class Client {

    public static void main(String[] args) throws Exception {
        for (int index = 0; index < 1; index++) {
            // 设置不同版本号，可以调用不同版本的实现
            createPet100(index);
            createPet101(index);
            sayHello100(index);
            sendMail100(index);
        }
    }

    /**
     * @param index
     * @throws Exception
     */
    private static void sendMail100(int index) throws Exception {
        IEmailSendService emailSendService = ServiceFactory.getService(
                IEmailSendService.class, "1.0.0");
        EmailObject emailObject = new EmailObject();
        emailObject.setFrom("\"chengjianfang\" <chengjf0526@163.com>");
        emailObject.setTos("\"chengjianfang\" <chengjf0526@gmail.com>");
        // emailObject.setCcs(new String[] { "wlzhongguo@gmail.com" });
        emailObject.setSubject("测试nutz+dubbo做ws调用，jodd做邮件发送，第【" + (index + 1)
                + "】封");
        Message textMessage = new Message();
        textMessage.setContent("测试");
        textMessage.setMimeType(MimeTypes.MIME_TEXT_PLAIN);
        Message htmlMessage = new Message();
        htmlMessage
                .setContent("<br><center><h1>文章标题</h1><div>纯粹测试</div></center>");
        htmlMessage.setMimeType(MimeTypes.MIME_TEXT_HTML);
        emailObject.setMessages(textMessage, htmlMessage);
        InputStream is = null;
        byte[] content = null;
        try {
            is = new FileInputStream("D:/TD/出差申请(20130218-20130331).png");
            content = new byte[is.available()];
            is.read(content);
        } finally {
            if (null != is) {
                is.close();
            }
        }
        SunivoEmailAttachment attachment = new SunivoEmailAttachment(
                "出差申请(20130218-20130331).png", "B001",
                ByteConver.conver(content), "application/file");
        emailObject.setAttachments(attachment);
        String result = emailSendService.sendEmail(emailObject);
        if (null != result) {
            System.out.println("唯一标识为:" + result);
        } else {
            System.out.println("发送邮件失败");
        }
    }

    /**
     * @param index
     */
    private static void sayHello100(int index) {
        IHelloService helloService = ServiceFactory.getService(
                IHelloService.class, "1.0.0");
        System.out.println(helloService.sayHello("chengjianfang." + index));
    }

    /**
     * @param index
     */
    private static void createPet101(int index) {
        IPetService petService = ServiceFactory.getService(IPetService.class,
                "1.0.1");
        Pet pet = petService.createPet();
        System.out.println(Json.toJson(pet));
        pet = petService.createPet("手工设置名字");
        System.out.println(Json.toJson(pet));
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, index);
        pet = petService.createPet("手工设置名字", calendar);
        System.out.println(Json.toJson(pet));
    }

    /**
     * @param index
     */
    private static void createPet100(int index) {
        IPetService petService = ServiceFactory.getService(IPetService.class,
                "1.0.0");
        Pet pet = petService.createPet();
        System.out.println(Json.toJson(pet));
        pet = petService.createPet("手工设置名字");
        System.out.println(Json.toJson(pet));
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, index);
        pet = petService.createPet("手工设置名字", calendar);
        System.out.println(Json.toJson(pet));
    }
}