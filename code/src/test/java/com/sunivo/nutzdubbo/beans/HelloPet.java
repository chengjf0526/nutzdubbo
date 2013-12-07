package com.sunivo.nutzdubbo.beans;

import static com.sunivo.nutzdubbo.utils.IocUtils.PROVIDER_ICO;

import com.alibaba.dubbo.config.ServiceConfig;
import com.sunivo.nutzdubbo.services.IPetService;

public class HelloPet {

    public static void main(String[] args) {
        ServiceConfig<IPetService> service = PROVIDER_ICO.get(null, "service");
        service.export();
    }

}