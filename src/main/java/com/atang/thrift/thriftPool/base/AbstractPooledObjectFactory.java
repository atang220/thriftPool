package com.atang.thrift.thriftPool.base;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.thrift.TServiceClient;
import org.apache.thrift.TServiceClientFactory;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.atang.thrift.thriftPool.modal.PooledTServiceClient;
import com.atang.thrift.thriftPool.modal.TSConfig;

public abstract class AbstractPooledObjectFactory extends BasePooledObjectFactory<PooledTServiceClient> {

	private static final Logger LOG = LoggerFactory.getLogger(AbstractPooledObjectFactory.class);

	private List<TSConfig> configList = new ArrayList<TSConfig>();

	private TServiceClientFactory<TServiceClient> factory;
	
	private String factoryName;

	private String serviceName;
	
	@PostConstruct
	public void init(){
		
        try {
        	ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            // 加载factory
    		Class<TServiceClientFactory<TServiceClient>> fi = (Class<TServiceClientFactory<TServiceClient>>)classLoader.loadClass(factoryName);
			this.factory = fi.newInstance();
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			throw new RuntimeException("thrift初始化失败。");
		} 
	}

	@Override
	public PooledTServiceClient create() throws Exception {

		// 选择一个服务器配置
		TSConfig config = getConfigList().get(new Random().nextInt(getConfigList().size()));

		LOG.info("准备创建 PooledTServiceClient： " + config);

		TSocket socket = new TSocket(config.getHost(), config.getPort());

		if (config.getTimeout() > 0) {
			socket.setTimeout(config.getTimeout());
		}

		TTransport transport = socket;

		TProtocol protocol = getTProtocol(socket, config);

		transport.open();

		TServiceClient client = this.getFactory().getClient(protocol);

		PooledTServiceClient pooledTServiceClient = new PooledTServiceClient(getServiceName(), client, transport, config);
		LOG.info("成功创建一个新的 PooledTServiceClient： " + pooledTServiceClient);
		return pooledTServiceClient;
	}

	public abstract TProtocol getTProtocol(TSocket socket, TSConfig config);

	@Override
	public PooledObject<PooledTServiceClient> wrap(PooledTServiceClient obj) {
		return new DefaultPooledObject<PooledTServiceClient>(obj);
	}
	
	/**
     * 从连接池中删除该对象
     *
     * @param pooledObject 要删除的对象
     * @throws Exception
     */
    @Override
    public void destroyObject(PooledObject<PooledTServiceClient> pooledObject) throws Exception {
        super.destroyObject(pooledObject);
        PooledTServiceClient client = pooledObject.getObject();
        LOG.info("销毁一个 PooledTServiceClient： " + client);
        // 调用出错的时候废弃
        client.discard();
        TTransport transport = client.getTransport();
        if (transport != null) {
            transport.close();
        }
    }

    @Override
    public boolean validateObject(PooledObject<PooledTServiceClient> pooledObject) {
    	LOG.info("Validate 一个 TServiceClient 状态： " + pooledObject.getObject());
        PooledTServiceClient serviceClient = pooledObject.getObject();
        if (serviceClient.isDiscard()) {
            return false;
        }
        TTransport transport = serviceClient.getServiceClient().getInputProtocol().getTransport();
        boolean commonValidate = transport.isOpen() && serviceClient.getServiceClient().getOutputProtocol().getTransport().isOpen();
        if (commonValidate) {
            boolean customValidate = customValidate(pooledObject);
            if (!customValidate) {
                serviceClient.discard();
                serviceClient.setRemark("自定义心跳检测异常！");
            }
            return customValidate;
        }
        serviceClient.discard();
        serviceClient.setRemark("Socket.isOpen() 检测异常！");
        return false;
    }

    /**
     * 自定义检测逻辑，比如实现ping方法，用于继承
     *
     * @param pooledObject 连接池对象
     * @return
     */
    protected boolean customValidate(PooledObject<PooledTServiceClient> pooledObject) {
        return true;
    }
	

	public List<TSConfig> getConfigList() {
		return configList;
	}

	public void setConfigList(List<TSConfig> configList) {
		this.configList = configList;
	}

	public TServiceClientFactory<TServiceClient> getFactory() {
		return factory;
	}

	public void setFactory(TServiceClientFactory<TServiceClient> factory) {
		this.factory = factory;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getFactoryName() {
		return factoryName;
	}

	public void setFactoryName(String factoryName) {
		this.factoryName = factoryName;
	}

	
}
