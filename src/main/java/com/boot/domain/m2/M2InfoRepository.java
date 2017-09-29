package com.boot.domain.m2;

import com.boot.domain.entity.Info;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @desc:
 * @author: 鱼唇的人类
 * @date: 2017/9/28 10:27
 */
public interface M2InfoRepository extends JpaRepository<Info,Integer>{
}
