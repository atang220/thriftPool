package com.atang.thrift.thriftPool.example;

import org.apache.commons.pool2.PooledObject;
import org.apache.thrift.TServiceClient;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.atang.thrift.thriftPool.base.AbstractPooledObjectFactory;
import com.atang.thrift.thriftPool.example.client.GmBcService;
import com.atang.thrift.thriftPool.modal.PooledTServiceClient;
import com.atang.thrift.thriftPool.modal.TSConfig;




public class TFramTransportPooledObjectFactory extends AbstractPooledObjectFactory {

	private static final Logger LOG = LoggerFactory.getLogger(TFramTransportPooledObjectFactory.class);


	@Override
	public TProtocol getTProtocol(TSocket socket, TSConfig config) {
		
		TTransport transport = new TFramedTransport(socket);
		// 设置传输协议为 TBinaryProtocol
		TProtocol protocol = new TBinaryProtocol(transport);
		LOG.info("[broadCast]Create TFramedTransport successfully! " + config.getHost()
				+ ":" + config.getPort() + ":" + config.getTimeout() + ":"
				+ config.getWeight());
		return protocol;
	}
	
	protected boolean customValidate(PooledObject<PooledTServiceClient> pooledObject){
        PooledTServiceClient serviceClient = pooledObject.getObject();
        TServiceClient tServiceClient = serviceClient.getServiceClient();
        if (tServiceClient instanceof GmBcService.Client) {
        	GmBcService.Client client = (GmBcService.Client) tServiceClient;
            try {
                // 业务心跳检测
            	client.pingSaaSrv(System.currentTimeMillis());
                return true;
            } catch (Exception e) {
            	LOG.info("[广播服务]心跳检测失败： " + e.getMessage());
                return false;
            }
        }
        return false;
    }

}
