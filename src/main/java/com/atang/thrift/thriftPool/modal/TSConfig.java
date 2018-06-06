package com.atang.thrift.thriftPool.modal;

public class TSConfig {

	/** 服务主机 */
    private String host;

    /** 提供服务的端口 */
    private int port;

    /** 超时时间，单位是毫秒， 默认5秒超时 */
    private int timeout = 5000;

    /** 权重，默认是1，小于0的即为1 */
    private int weight = 1;

    public String getHost() {
        return host;
    }

    public TSConfig setHost(String host) {
        this.host = host;
        return this;
    }

    public int getPort() {
        return port;
    }

    public TSConfig setPort(int port) {
        this.port = port;
        return this;
    }

    public int getTimeout() {
        return timeout;
    }

    public TSConfig setTimeout(int timeout) {
        this.timeout = timeout;
        return this;
    }

    public int getWeight() {
        return weight;
    }

    public TSConfig setWeight(int weight) {
        this.weight = weight < 1 ? 1 : weight;
        return this;
    }
    
    public String toString(){
    	return "TSConfig [host:" + host + " port:" + port + "]";
    }
}
