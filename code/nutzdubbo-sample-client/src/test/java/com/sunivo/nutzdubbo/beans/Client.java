package com.sunivo.nutzdubbo.beans;

import java.util.Calendar;

import org.nutz.json.Json;

import com.sunivo.nutzdubbo.services.IHelloService;
import com.sunivo.nutzdubbo.services.IPetService;
import com.sunivo.nutzdubbo.utils.ServiceFactory;

public class Client {

    public static void main(String[] args) {
        for (int index = 0; index < 10; index++) {
            // 设置不同版本号，可以调用不同版本的实现
            {
                IPetService petService = ServiceFactory.getService(
                        IPetService.class, "1.0.0");
                Pet pet = petService.createPet();
                System.out.println(Json.toJson(pet));
                pet = petService.createPet("手工设置名字");
                System.out.println(Json.toJson(pet));
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.YEAR, index);
                pet = petService.createPet("手工设置名字", calendar);
                System.out.println(Json.toJson(pet));
            }
            {
                IPetService petService = ServiceFactory.getService(
                        IPetService.class, "1.0.1");
                Pet pet = petService.createPet();
                System.out.println(Json.toJson(pet));
                pet = petService.createPet("手工设置名字");
                System.out.println(Json.toJson(pet));
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.YEAR, index);
                pet = petService.createPet("手工设置名字", calendar);
                System.out.println(Json.toJson(pet));
            }
            {
                IHelloService helloService = ServiceFactory.getService(
                        IHelloService.class, "1.0.0");
                System.out.println(helloService.sayHello("chengjianfang."
                        + index));
            }
        }
    }
}