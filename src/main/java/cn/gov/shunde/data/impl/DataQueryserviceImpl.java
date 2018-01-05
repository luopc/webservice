package cn.gov.shunde.data.impl;

import java.util.HashMap;
import java.util.Map;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;

import cn.gov.shunde.data.DataQueryService;
import cn.gov.shunde.util.SessionInstance;

/**
 * Created by luopc on 2017/12/20. data.shunde.gov.cn cn.gov.shunde.data
 */
@WebService(endpointInterface = "cn.gov.shunde.data.DataQueryService")
public class DataQueryserviceImpl implements DataQueryService {

	@Autowired
	private SessionInstance sessionInstance;

	@Override
	public String query(String sId, String type, String requestJson) {
		String userId = String.valueOf(sessionInstance.getValue("userId"));
		String userName = String.valueOf(sessionInstance.getValue("userName"));
		System.out.println("main - [userName=" + userName + ",userId=" + userId + "]");
		Map<String,Object> map = new HashMap<>();
		map.put("userName", userName);
		map.put("userId", userId);
		return JSON.toJSONString(map);
	}

	@Override
	public String fileList(String sId) {
		System.out.println(sId);
		return "";
	}

	public void setSessionInstance(SessionInstance sessionInstance) {
		this.sessionInstance = sessionInstance;
	}
	
	

}
