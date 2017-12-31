package cn.gov.shunde.data.impl;

import javax.jws.WebService;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.gov.shunde.data.DataQueryService;

/**
 * Created by luopc on 2017/12/20.
 * data.shunde.gov.cn
 * cn.gov.shunde.data
 */
@WebService(endpointInterface = "cn.gov.shunde.data.DataQueryService")
public class DataQueryserviceImpl implements DataQueryService {
	
	

	@Override
	public String query(String sId, String type, String requestJson) {
		if (StringUtils.isNotBlank(sId)) {
			if (sId != null) {
				return requestJson;
			}else{
				return JSON.toJSONString(nullJsonRs());
			}
		}else{
			return JSON.toJSONString(nullJsonRs());
		}
	}

	@Override
	public String fileList(String sId) {
		System.out.println(sId);
		return "";
	}

	/**
	 * 返回空JSON
	 * @return
	 */
	public JSONObject nullJsonRs() {
		JSONObject data = new JSONObject();
		data.put("STATUS","FAIL");
		data.put("MSG","查询出错");
		return data ;
	}
   

}
