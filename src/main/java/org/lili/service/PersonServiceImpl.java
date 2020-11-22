package org.lili.service;

import org.springframework.stereotype.Component;

/**
 * @author lili
 * @date 2020/11/22 12:13
 * @notes
 */
public class PersonServiceImpl implements PersonService {
    @Override
    public String sayName() {
        return "lili";
    }
}
