package com.atang.thrift.thriftPool.example.service;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.atang.thrift.thriftPool.example.client.GmBcService;


/**
 * 文档地址：http://14.17.119.23:45678/doku.php?id=saa
 * 广播成功率：一般是9997或者9998这样，没有客户端的ack
 * @author Administrator
 *
 */
@Service
public class BroadCastServiceImpl implements BroadCastService {
	
	private static final Logger LOG = LoggerFactory.getLogger(BroadCastServiceImpl.class);
	
	@Autowired private GmBcService.Iface broadCastClient;
	
	@Value("${SERVICE_APPID}")
	private int SERVICE_APPID;

	/*
	 *  return
	 *  BEN_SUCCUSS = 0,	// 成功
		BEN_ERROR = 1,		// 发送广播失败，广播系统返回错误
		BEN_OVERFLOW = 2,	// 本bcTypeId发送的广播超过上限
	 */
	@Override
	public int broadcastByTopSid(final long topSid,final String data) throws Exception {
        
		//data = new String(data.getBytes(), "UTF-8");
		
        int result = 1;
        final String reqId = UUID.randomUUID().toString();
        LOG.info("发送广播, topSid:" + topSid + ",reqId:"+ reqId + ", data:" + data);
        result = broadCastClient.broadcastByTopSid(38, topSid, 0l, reqId, SERVICE_APPID, data);
		
        LOG.info("发送广播, topSid:" + topSid + ",reqId:"+ reqId + ", result:" + result + ", data:" + data);
        if(result != 0){
        	LOG.error("broadcastByTopSid发送广播失败, result:" + result);
		}
        return result;
	}

	@Override
	public int broadcastBySubSid(final long topSid, final long subSid, final String data) throws Exception {
		
		//data = new String(data.getBytes(), "UTF-8");
		final String reqId = UUID.randomUUID().toString();
		LOG.info("发送广播,topSid:"+topSid+" subSid" + subSid + ",reqId:"+ reqId + ", data:" + data);
        int result = broadCastClient.broadcastBySubSid(38, topSid, 0l, reqId, subSid, SERVICE_APPID, data);
		
        LOG.info("发送广播,topSid:"+topSid+" subSid" + subSid + ",reqId:"+ reqId +", result:" + result + ", data:" + data);
        if(result != 0){
        	LOG.error("broadcastBySubSid发送广播失败, result:" + result);
		}
        return result;
	}

}
