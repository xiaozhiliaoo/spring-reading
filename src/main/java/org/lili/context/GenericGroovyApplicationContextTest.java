package org.lili.context;

import org.lili.aop.Person;
import org.lili.service.PersonService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericGroovyApplicationContext;

/**
 * @author lili
 * @date 2020/11/22 12:32
 * @notes
 */
public class GenericGroovyApplicationContextTest {
    public static void main(String[] args) {
        ApplicationContext context = new GenericGroovyApplicationContext("services.groovy");
        PersonService bean = context.getBean(PersonService.class);
        System.out.println(bean.sayName());
    }
}
