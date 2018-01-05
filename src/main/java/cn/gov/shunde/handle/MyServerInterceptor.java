package cn.gov.shunde.handle;

import javax.xml.namespace.QName;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.headers.Header;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.springframework.beans.factory.annotation.Autowired;
import org.w3c.dom.Element;

import cn.gov.shunde.util.SessionInstance;

public class MyServerInterceptor extends AbstractPhaseInterceptor<SoapMessage> {

	@Autowired
	private SessionInstance sessionInstance;

	public MyServerInterceptor() {
		super(Phase.PRE_PROTOCOL);
	}

	@Override
	public void handleMessage(SoapMessage msg) throws Fault {
		// 读header里的数据
		Header header = msg.getHeader(new QName("CustomHeader"));// 参数和客户端传的一样
		if (header != null) {
			Element atguigu = (Element) header.getObject();
			String name = atguigu.getElementsByTagName("userName").item(0).getTextContent();
			String pwd = atguigu.getElementsByTagName("userPassWord").item(0).getTextContent();
//			if (name.equals("zhangsan") && pwd.equals(WsClientTool.getMD5Str("zhangsan"))) {
//				System.out.println("server:check ok");
//				return;
//			}
			int id = (int)(Math.random()*1000);
			sessionInstance.putValue("userId", id);
			sessionInstance.putValue("userName", name);
			System.out.println("Interceptor - [userName=" + name + ",userId=" + id + "]");
		}
		// 没通过校验,抛出异常
		System.out.println("没有通过校验");
		throw new Fault(new RuntimeException("用户名或密码不对"));
	}

	public void setSessionInstance(SessionInstance sessionInstance) {
		this.sessionInstance = sessionInstance;
	}

	
}
