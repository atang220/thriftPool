package com.atang.thrift.thriftPool.modal;

import org.apache.thrift.TServiceClient;
import org.apache.thrift.transport.TTransport;

/**
 * @author dw_xiajiqiu1
 * @time 2017/6/19 9:28
 */
public class PooledTServiceClient {

    protected final TServiceClient serviceClient;

    protected final TTransport transport;

    protected final TSConfig config;

    /** 是否是有效的连接 */
    protected volatile boolean discard = false;

    /** 最后一次访问时间 */
    protected volatile long lastAccessTime;

    /** 创建时间 */
    protected final long createTime;

    /** 备注信息，比如连接要丢弃的时候，错误信息 */
    protected String remark;

    /** 业务类名称 */
    protected String serviceClass;

    public PooledTServiceClient(String serviceClass, TServiceClient serviceClient, TTransport transport, TSConfig config) {
        this.serviceClass = serviceClass;
        this.serviceClient = serviceClient;
        this.transport = transport;
        this.config = config;
        this.createTime = System.currentTimeMillis();
    }


    public TServiceClient getServiceClient() {
        return serviceClient;
    }

    public TTransport getTransport() {
        return transport;
    }

    public TSConfig getConfig() {
        return config;
    }


    public boolean isDiscard() {
        return discard;
    }

    public void discard() {
        this.discard = true;
    }

    public long getLastAccessTime() {
        return lastAccessTime;
    }

    public void setLastAccessTime(long lastAccessTime) {
        this.lastAccessTime = lastAccessTime;
    }

    public long getCreateTime() {
        return createTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getServiceClass() {
        return serviceClass;
    }

    public void setServiceClass(String serviceClass) {
        this.serviceClass = serviceClass;
    }

    @Override
    public String toString() {
        return "@" + Integer.toHexString(hashCode()) + ":effective=" + !isDiscard() + ":" + config.getHost() + ":" + config.getPort() + ":" + config.getTimeout() + ":" + getServiceClass() + ":remark=" + getRemark();
    }

}
