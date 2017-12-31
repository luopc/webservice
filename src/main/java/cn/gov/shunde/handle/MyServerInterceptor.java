package cn.gov.shunde.handle;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.namespace.QName;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.headers.Header;
import org.apache.cxf.helpers.IOUtils;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.interceptor.LoggingMessage;
import org.apache.cxf.io.CachedOutputStream;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.springframework.beans.factory.annotation.Autowired;
import org.w3c.dom.Element;

import com.ces.web.validate.service.ValidateUserManager;

public class MyServerInterceptor extends AbstractPhaseInterceptor<SoapMessage> {
	
	private int limit = 102400; 
	
	
	public MyServerInterceptor() {
		super(Phase.PRE_PROTOCOL);
	}

	@Override
	public void handleMessage(SoapMessage msg) throws Fault {
//		logging(message);//记录日志
		// 读header里的数据
		Header header = msg.getHeader(new QName("CustomHeader"));// 参数和客户端传的一样
		if (header != null) {
			Element atguigu = (Element) header.getObject();
			String name = atguigu.getElementsByTagName("userName").item(0).getTextContent();
			String pwd = atguigu.getElementsByTagName("userPassWord").item(0).getTextContent();
			boolean success = true;
			if (success) {
				System.out.println("server:check ok");
				return;
			}
		}
		// 没通过校验,抛出异常
		System.out.println("没有通过校验");
		throw new Fault(new RuntimeException("用户名或密码不对"));
	}
	
	
	private void logging(Message message) throws Fault {  
        if (message.containsKey(LoggingMessage.ID_KEY)) {  
            return;  
        }  
        String id = (String) message.getExchange().get(LoggingMessage.ID_KEY);  
        if (id == null) {  
            id = LoggingMessage.nextId();  
            message.getExchange().put(LoggingMessage.ID_KEY, id);  
        }  
        message.put(LoggingMessage.ID_KEY, id);  
        LoggingMessage buffer = new LoggingMessage("Inbound Message\n----------------------------", id);  
  
        String encoding = (String) message.get(Message.ENCODING);  
  
        if (encoding != null) {  
            buffer.getEncoding().append(encoding);  
        }  
        String ct = (String) message.get("Content-Type");  
        if (ct != null) {  
            buffer.getContentType().append(ct);  
        }  
        Object headers = message.get(Message.PROTOCOL_HEADERS);  
  
        if (headers != null) {  
            buffer.getHeader().append(headers);  
        }  
        String uri = (String) message.get(Message.REQUEST_URI);  
        if (uri != null) {  
            buffer.getAddress().append(uri);  
        }  
        InputStream is = message.getContent(InputStream.class);
//        InputStream is = (InputStream) message.getContent(InputStream.class);  
        if (is != null) {  
            CachedOutputStream bos = new CachedOutputStream();  
            try {  
                IOUtils.copy(is, bos);  
  
                bos.flush();  
                is.close();  
  
                message.setContent(InputStream.class, bos.getInputStream());  
                if (bos.getTempFile() != null) {  
                    buffer.getMessage().append("\nMessage (saved to tmp file):\n");  
                    buffer.getMessage().append("Filename: " + bos.getTempFile().getAbsolutePath() + "\n");  
                }  
                if (bos.size() > this.limit) {  
                    buffer.getMessage().append("(message truncated to " + this.limit + " bytes)\n");  
                }  
                bos.writeCacheTo(buffer.getPayload(), this.limit);  
  
                bos.close();  
            } catch (IOException e) {  
                throw new Fault(e);  
            }  
        }  
        // 打印日志，保存日志保存这里就可以，可写库或log4j记录日志  
        System.out.println(buffer.toString());  
  
    }  

	

}
