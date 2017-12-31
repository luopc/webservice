package cn.gov.shunde.data;

import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * Created by DuLida on 2016/11/14.
 */
@WebService
public interface DataQueryService {
    String query(@WebParam(name = "sId")String sId , @WebParam(name = "type")String type,@WebParam(name = "requestJson")String requestJson);
    
    String fileList(@WebParam(name = "sId")String sId);
}