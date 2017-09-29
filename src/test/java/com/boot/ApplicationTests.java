package com.boot;

import com.boot.domain.entity.Info;
import com.boot.domain.m1.M1InfoRepository;
import com.boot.domain.m2.M2InfoRepository;
import com.boot.service.InfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {
	@Autowired
	M1InfoRepository m1InfoRepository;
	@Autowired
	M2InfoRepository m2InfoRepository;
	@Autowired
	private InfoService infoService;

	@Test
	@Transactional
	public void contextLoads() {
		Info info=new Info();
		info.setName("lee");
		info.setAge(20);
		m1InfoRepository.save(info);
		Info info1=new Info();
		info1.setName("lee");
		info1.setAge(20);
		m2InfoRepository.save(info1);
	}

	@Test
	public void test() throws CloneNotSupportedException {
		infoService.insert();
	}
}
