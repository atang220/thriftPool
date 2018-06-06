package com.atang.thrift.thriftPool.example.service;

public interface BroadCastService {

	int broadcastByTopSid(long topSid, String data) throws Exception;
	
	int broadcastBySubSid(long topSid, long subSid, String data) throws Exception;
}
