package com.sunivo.nutzdubbo.beans;

import static com.sunivo.nutzdubbo.utils.IocUtils.CONSUMER_ICO;

import java.util.Calendar;

import org.nutz.json.Json;

import com.alibaba.dubbo.config.ReferenceConfig;
import com.sunivo.nutzdubbo.services.IPetService;

public class Client {

    public static void main(String[] args) {
        ReferenceConfig<IPetService> reference = CONSUMER_ICO.get(null,
                "reference");
        // 设置不同版本号，可以调用不同版本的实现
        reference.setVersion("1.0.1");
        IPetService petService = reference.get();
        Pet pet = petService.createPet();
        System.out.println(Json.toJson(pet));
        pet = petService.createPet("手工设置名字");
        System.out.println(Json.toJson(pet));
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2014);
        pet = petService.createPet("手工设置名字", calendar);
        System.out.println(Json.toJson(pet));
    }
}