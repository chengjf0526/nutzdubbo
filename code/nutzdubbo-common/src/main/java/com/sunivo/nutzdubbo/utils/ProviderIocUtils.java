/**
 * 
 */
package com.sunivo.nutzdubbo.utils;

import org.nutz.ioc.Ioc2;
import org.nutz.ioc.impl.NutIoc;
import org.nutz.ioc.loader.annotation.AnnotationIocLoader;
import org.nutz.ioc.loader.combo.ComboIocLoader;
import org.nutz.ioc.loader.json.JsonLoader;

/**
 * @author chengjianfang@sunivo.com
 * 
 *         2013年12月10日 下午3:04:02
 */
public interface ProviderIocUtils extends IocUtils {
    /**
     * JDBC IOC
     */
    Ioc2 JDBC_IOC = new NutIoc(new JsonLoader("inner/jdbc.js"));

    /**
     * 服务提供者IOC
     */
    Ioc2 PROVIDER_ICO = new NutIoc(new ComboIocLoader(new AnnotationIocLoader(
            "com.sunivo.nutzdubbo.services",
            "com.sunivo.messagecenter.services"), new JsonLoader(
            "common/common.js", "provider/provider.js", "service")));

}
