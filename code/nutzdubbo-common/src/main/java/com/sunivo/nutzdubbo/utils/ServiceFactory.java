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
     * 服务配置对象集合
     */
    @SuppressWarnings("rawtypes")
    private static final Map<String, ReferenceConfig> SERVICES = new HashMap<String, ReferenceConfig>();

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
        ReferenceConfig reference = SERVICES.get(key);
        if (null == reference) {
            synchronized (clazz) {
                reference = SERVICES.get(key);
                if (null == reference) {
                    reference = CONSUMER_ICO.get(null, "reference");
                    reference.setInterface(clazz);
                    reference.setVersion(version);
                    SERVICES.put(key, reference);
                }
            }
        }
        return (T) reference.get();
    }
}
