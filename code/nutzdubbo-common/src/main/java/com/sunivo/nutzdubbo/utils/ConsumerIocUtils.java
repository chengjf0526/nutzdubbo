/**
 * 
 */
package com.sunivo.nutzdubbo.utils;

import org.nutz.ioc.Ioc2;
import org.nutz.ioc.impl.NutIoc;
import org.nutz.ioc.loader.json.JsonLoader;

/**
 * @author chengjianfang@sunivo.com
 * 
 *         2013年12月10日 下午3:03:26
 */
public interface ConsumerIocUtils extends IocUtils {

    /**
     * 服务消费者IOC
     */
    Ioc2 CONSUMER_ICO = new NutIoc(new JsonLoader("common/common.js",
            "consumer/consumer.js"));

}
