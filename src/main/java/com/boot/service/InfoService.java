package com.boot.service;

import com.boot.domain.entity.Info;
import com.boot.domain.m1.M1InfoRepository;
import com.boot.domain.m2.M2InfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @desc:
 * @author: 鱼唇的人类
 * @date: 2017/9/28 15:09
 */
@Service
public class InfoService {
    @Autowired
    M1InfoRepository m1InfoRepository;
    @Autowired
    M2InfoRepository m2InfoRepository;

    @Transactional
    public void insert() throws CloneNotSupportedException {
        Info info=new Info();
        info.setName("lee");
        info.setAge(20);
        Info info1=new Info();
        info1.setName("lewis");
        info1.setAge(21);
        m1InfoRepository.save(info1);
        m2InfoRepository.save(info1);
    }
}
