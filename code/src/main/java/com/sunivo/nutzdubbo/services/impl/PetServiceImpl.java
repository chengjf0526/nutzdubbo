/**
 * 
 */
package com.sunivo.nutzdubbo.services.impl;

import java.util.Calendar;

import org.nutz.ioc.loader.annotation.IocBean;

import static com.sunivo.nutzdubbo.utils.IocUtils.*;

import com.alibaba.dubbo.config.annotation.Service;
import com.sunivo.nutzdubbo.beans.Pet;
import com.sunivo.nutzdubbo.services.IPetService;

/**
 * @author chengjianfang@sunivo.com
 * 
 *         2013年12月6日 下午9:59:56
 */
@IocBean(name = "petService")
@Service(version = "1.0.0")
public class PetServiceImpl implements IPetService {

    /*
     * (non-Javadoc)
     * 
     * @see com.sunivo.nutzdubbo.services.IPetService#createPet()
     */
    public Pet createPet() {
        Pet pet = BEANS_IOC.get(null, "pet");
        pet.setVersion("version-1.0.0");
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
