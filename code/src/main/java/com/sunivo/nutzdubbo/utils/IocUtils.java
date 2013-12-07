/**
 * 
 */
package com.sunivo.nutzdubbo.utils;

import org.nutz.ioc.Ioc2;
import org.nutz.ioc.impl.NutIoc;
import org.nutz.ioc.loader.annotation.AnnotationIocLoader;
import org.nutz.ioc.loader.json.JsonLoader;

/**
 * @author chengjianfang@sunivo.com
 * 
 *         2013年12月6日 下午10:01:01
 */
public interface IocUtils {
    /**
     * 实体IOC
     */
    Ioc2 BEANS_IOC = new NutIoc(new AnnotationIocLoader(
            "com.sunivo.nutzdubbo.beans"));
    /**
     * JDBC IOC
     */
    Ioc2 JDBC_IOC = new NutIoc(new JsonLoader("inner/jdbc.js"));

    /**
     * SERVICE IOC
     */
    Ioc2 SERVICE_IOC = new NutIoc(new AnnotationIocLoader(
            "com.sunivo.nutzdubbo.services"));

    /**
     * 服务提供者IOC
     */
    Ioc2 PROVIDER_ICO = new NutIoc(new JsonLoader("common/common.js",
            "provider/provider.js"));

    /**
     * 服务消费者IOC
     */
    Ioc2 CONSUMER_ICO = new NutIoc(new JsonLoader("common/common.js",
            "consumer/consumer.js"));
}
