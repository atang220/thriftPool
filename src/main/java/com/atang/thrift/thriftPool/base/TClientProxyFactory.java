package com.atang.thrift.thriftPool.base;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

import com.atang.thrift.thriftPool.example.TFramTransportPooledObjectFactory;
import com.atang.thrift.thriftPool.modal.PooledTServiceClient;

public class TClientProxyFactory implements FactoryBean, InitializingBean {

    protected final Logger logger = LoggerFactory.getLogger(TClientProxyFactory.class);

    /** 代理对象实例 */
    protected Object proxyObject;

    /** 对象类型 */
    protected Class<?> iface;
    protected String ifaceName;

    /** TClient连接池 */
    protected GenericObjectPool<PooledTServiceClient> pool;

    /** 服务类，可以是由Thrift 生成出来的 那个类，也可以是Iface的全类名，系统会自动适配 */
    protected String serviceClass;

    /** 连接池配置信息 */
    protected GenericObjectPoolConfig poolConfig;


    /** 对象工厂类 */
    protected AbstractPooledObjectFactory pooledObjectFactory;


	public Class<?> getIface() {
		return iface;
	}

	public void setIface(Class<?> iface) {
		this.iface = iface;
	}

	public String getIfaceName() {
		return ifaceName;
	}

	public void setIfaceName(String ifaceName) {
		this.ifaceName = ifaceName;
	}

	public GenericObjectPool<PooledTServiceClient> getPool() {
		return pool;
	}

	public void setPool(GenericObjectPool<PooledTServiceClient> pool) {
		this.pool = pool;
	}

	public GenericObjectPoolConfig getPoolConfig() {
		return poolConfig;
	}

	public void setPoolConfig(GenericObjectPoolConfig poolConfig) {
		this.poolConfig = poolConfig;
	}

	public String getServiceClass() {
		return serviceClass;
	}

	@Override
    public Object getObject() throws Exception {
        return this.proxyObject;
    }

    @Override
    public Class<?> getObjectType() {
        return this.iface;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    public AbstractPooledObjectFactory getPooledObjectFactory() {
        return pooledObjectFactory;
    }

    public void setPooledObjectFactory(AbstractPooledObjectFactory pooledObjectFactory) {
        this.pooledObjectFactory = pooledObjectFactory;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        try {
            if (this.poolConfig == null) {
                logger.info("使用默认的连接池配置【" + this.serviceClass + "】");
                this.poolConfig = new GenericObjectPoolConfig();
            }

            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            // 加载Iface接口
            this.iface = classLoader.loadClass(ifaceName);
            logger.info("Load Iface type successfully [" + this.iface.getName() + "]");

            // 加载Client.Factory类
           /* Class<TServiceClientFactory<TServiceClient>> fi = ThriftUtils.parseClientFactoryClassExt(serviceClass);
            final TServiceClientFactory<TServiceClient> clientFactory = fi.newInstance();*/

            if (pooledObjectFactory == null) {
                pooledObjectFactory = createDefaultPooledObjectFactory();
            }
           // pooledObjectFactory.setConfigProvider(configProvider);
           // pooledObjectFactory.setClientFactory(clientFactory);
            pooledObjectFactory.setServiceName(serviceClass);

            pool = new GenericObjectPool<PooledTServiceClient>(pooledObjectFactory, poolConfig);

            // 创建动态代理
            this.proxyObject = createProxyObject(classLoader);
            logger.info("Build[" + this.iface.getName() + "]'s proxy successfully!");
        } catch (Exception e) {
            logger.error("Create Thrift Client [" + this.serviceClass + "] Proxy Error: " + e.getMessage());
            throw e;
        }
    }

    protected Object createProxyObject(final ClassLoader classLoader) {
        return Proxy.newProxyInstance(classLoader, new Class[]{getObjectType()}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                // 从连接池中获取client
                PooledTServiceClient client = pool.borrowObject();
                String argsString = argsToString(args);
                try {
                    logger.info("method=" + method.getName() + "args=" + argsString + ", 当前Client: " + client);
                    // 设置最后一次访问时间
                    client.setLastAccessTime(System.currentTimeMillis());
                    // 执行方法
                    return method.invoke(client.getServiceClient(), args);
                } catch (Exception e) {
                    // 调用出错的话，直接将该连接丢弃
                    client.discard(); // 遗弃
                    String remark = "Thrift 调用异常: method=" + method.getName() + ", args=" + argsString;
                    client.setRemark(remark);
                    logger.warn(remark + ", client =[" + client + "], error = " + e.getMessage(), e);
                    throw e;
                } finally {
                    // 释放到连接池中
                    pool.returnObject(client);
                }
            }
        });
    }

    private static String argsToString(Object[] args) {
        StringBuilder builder = new StringBuilder("[");

        if (args == null || args.length < 1) {
            builder.append("null]");
        } else {
            for (Object arg : args) {
                builder.append(arg).append(",");
            }
            builder.setLength(builder.length() - 1);
            builder.append("]");
        }

        return builder.toString();
    }

    protected AbstractPooledObjectFactory createDefaultPooledObjectFactory() {
        return new TFramTransportPooledObjectFactory();
    }

    /**
     * 加载Iface接口
     *
     * @param serviceClass 业务类全路径，有可能是Thrift生成的类，也可能直接是Iface接口
     * @return
     */
    private Class<?> loadObjectType(String serviceClass) throws ClassNotFoundException {
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            if (serviceClass.endsWith("$Iface")) {
                return classLoader.loadClass(serviceClass);
            }
            serviceClass = serviceClass + "$Iface";
            return classLoader.loadClass(serviceClass);
        } catch (ClassNotFoundException e) {
            logger.error("Load thrift service interface error: " + e.getMessage());
            throw e;
        }
    }
}