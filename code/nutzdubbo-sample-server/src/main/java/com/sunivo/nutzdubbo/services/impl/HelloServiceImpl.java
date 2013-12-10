/**
 * 
 */
package com.sunivo.nutzdubbo.services.impl;

import org.nutz.ioc.loader.annotation.IocBean;

import com.sunivo.nutzdubbo.services.IHelloService;

/**
 * @author chengjianfang@sunivo.com
 * 
 *         2013年12月9日 下午9:33:17
 */
@IocBean(name = "helloService")
public class HelloServiceImpl implements IHelloService {

    public String sayHello(String name) {
        return "Hello, " + name;
    }

}
