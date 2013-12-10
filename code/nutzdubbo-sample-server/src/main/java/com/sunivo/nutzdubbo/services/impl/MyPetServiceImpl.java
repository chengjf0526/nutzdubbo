/**
 * 
 */
package com.sunivo.nutzdubbo.services.impl;

import static com.sunivo.nutzdubbo.utils.IocUtils.BEANS_IOC;

import java.util.Calendar;

import org.nutz.ioc.loader.annotation.IocBean;

import com.alibaba.dubbo.config.annotation.Service;
import com.sunivo.nutzdubbo.beans.Pet;
import com.sunivo.nutzdubbo.services.IPetService;

/**
 * @author chengjianfang@sunivo.com
 * 
 *         2013年12月6日 下午9:59:56
 */
@IocBean(name = "myPetService")
@Service(version = "1.0.1")
public class MyPetServiceImpl implements IPetService {

    /*
     * (non-Javadoc)
     * 
     * @see com.sunivo.nutzdubbo.services.IPetService#createPet()
     */
    public Pet createPet() {
        Pet pet = BEANS_IOC.get(null, "pet");
        pet.setVersion("1.0.1");
        return pet;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.sunivo.nutzdubbo.services.IPetService#createPet(java.lang.String)
     */
    public Pet createPet(String name) {
        Pet pet = createPet();
        pet.setName(name);
        return pet;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.sunivo.nutzdubbo.services.IPetService#createPet(java.lang.String,
     * java.util.Calendar)
     */
    public Pet createPet(String name, Calendar birthday) {
        Pet pet = createPet(name);
        pet.setBirthday(birthday);
        return pet;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.sunivo.nutzdubbo.services.IPetService#createPet(java.lang.String,
     * java.util.Calendar, com.sunivo.nutzdubbo.beans.Pet)
     */
    public Pet createPet(String name, Calendar birthday, Pet friend) {
        Pet pet = createPet(name, birthday);
        pet.setFriend(friend);
        return pet;
    }

}
