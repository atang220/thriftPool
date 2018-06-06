package com.atang.thrift.thriftPool.example;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.atang.thrift.thriftPool.base.AbstractPooledObjectFactory;
import com.atang.thrift.thriftPool.modal.TSConfig;

public class TBinaryProtocolTClientPooledObjectFactory extends AbstractPooledObjectFactory {

	private static final Logger LOG = LoggerFactory.getLogger(TBinaryProtocolTClientPooledObjectFactory.class);


	@Override
	public TProtocol getTProtocol(TSocket socket, TSConfig config) {
		
		// 设置传输协议为 TBinaryProtocol
		TProtocol protocol = new TBinaryProtocol(socket);
		LOG.info("[serviceName]Create TBinaryProtocol successfully! " + config.getHost()
				+ ":" + config.getPort() + ":" + config.getTimeout() + ":"
				+ config.getWeight());
		return protocol;
	}
	
}
