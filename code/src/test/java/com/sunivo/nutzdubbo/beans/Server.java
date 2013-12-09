package com.sunivo.nutzdubbo.beans;

import static com.sunivo.nutzdubbo.utils.IocUtils.PROVIDER_ICO;

import com.alibaba.dubbo.config.ServiceConfig;

public class Server {
    public static void main(String[] args) {
        Thread providerThread = new Thread() {
            @SuppressWarnings("rawtypes")
            public void run() {
                ServiceConfig service = PROVIDER_ICO.get(null, "service");
                service.export();
                ServiceConfig myService = PROVIDER_ICO.get(null, "myService");
                myService.export();
            }
        };
        providerThread.start();
        try {
            Thread.sleep(100000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}