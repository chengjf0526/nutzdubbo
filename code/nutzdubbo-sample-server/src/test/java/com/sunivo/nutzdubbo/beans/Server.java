package com.sunivo.nutzdubbo.beans;

import static com.sunivo.nutzdubbo.utils.ProviderIocUtils.PROVIDER_ICO;

import com.alibaba.dubbo.config.ServiceConfig;

public class Server {
    public static void main(String[] args) {
        Thread providerThread = new Thread() {
            @SuppressWarnings("rawtypes")
            public void run() {
                String[] names = PROVIDER_ICO.getNames();
                for (String name : names) {
                    if (name.startsWith("service.")) {
                        ServiceConfig service = PROVIDER_ICO.get(null, name);
                        service.export();
                    }
                }
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