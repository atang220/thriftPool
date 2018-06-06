package com.atang.thrift.thriftPool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource(locations={"classpath:thrift-client.xml"})
public class ThriftPoolApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThriftPoolApplication.class, args);
	}
}
