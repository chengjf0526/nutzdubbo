/**
 * 
 */
package com.sunivo.nutzdubbo.utils;

import org.nutz.ioc.Ioc2;
import org.nutz.ioc.impl.NutIoc;
import org.nutz.ioc.loader.annotation.AnnotationIocLoader;

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
}
