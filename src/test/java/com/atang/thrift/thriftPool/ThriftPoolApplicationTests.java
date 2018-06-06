package com.atang.thrift.thriftPool;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.atang.thrift.thriftPool.example.service.BroadCastService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ThriftPoolApplicationTests {
	
	@Autowired private BroadCastService broadCastService;

	@Test
	public void userInfoService() throws Exception {
		System.out.println(broadCastService.broadcastBySubSid(40583191, 40583191, "test"));
	}

}
