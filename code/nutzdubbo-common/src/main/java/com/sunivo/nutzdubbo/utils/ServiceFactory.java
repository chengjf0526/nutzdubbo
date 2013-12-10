/**
 * 
 */
package com.sunivo.nutzdubbo.utils;

import static com.sunivo.nutzdubbo.utils.ConsumerIocUtils.CONSUMER_ICO;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.dubbo.config.ReferenceConfig;

/**
 * @author chengjianfang@sunivo.com
 * 
 *         2013年12月9日 下午9:52:25
 */
public final class ServiceFactory {
    /**
     * 服务对象集合
     */
    private static final Map<String, Object> SERVICES = new HashMap<String, Object>();

    /**
     * 获取服务对象
     * 
     * @param clazz
     *            服务对象类型
     * @param version
     *            服务版本
     * @return 服务对象
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static final <T> T getService(Class<? extends T> clazz,
            String version) {
        final String key = clazz.getName() + "." + version;
        T service = (T) SERVICES.get(key);
        if (null == service) {
            synchronized (clazz) {
                service = (T) SERVICES.get(key);
                if (null == service) {
                    ReferenceConfig reference = CONSUMER_ICO.get(null,
                            "reference");
                    reference.setInterface(clazz);
                    reference.setVersion(version);
                    service = (T) reference.get();
                    SERVICES.put(key, service);
                }
            }
        }
        return service;
    }
}
