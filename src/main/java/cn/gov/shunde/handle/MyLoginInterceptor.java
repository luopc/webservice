package cn.gov.shunde.handle;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.cxf.common.injection.NoJSR250Annotations;
import org.apache.cxf.common.logging.LogUtils;
import org.apache.cxf.helpers.IOUtils;
import org.apache.cxf.interceptor.AbstractLoggingInterceptor;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.io.CachedOutputStream;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.Phase;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


/**
 * A simple logging handler which outputs the bytes of the message to the
 * Logger.
 */
@NoJSR250Annotations
public class MyLoginInterceptor extends AbstractLoggingInterceptor {

//	private ValidataUserManager validataUserManager;

	private static final Logger LOG = LogUtils.getLogger(MyLoginInterceptor.class);

	public MyLoginInterceptor() {
		super(Phase.RECEIVE);
	}

	public MyLoginInterceptor(String phase) {
		super(phase);
	}

	public MyLoginInterceptor(String id, String phase) {
		super(id, phase);
	}

	public MyLoginInterceptor(int lim) {
		this();
		limit = lim;
	}

	public MyLoginInterceptor(String id, int lim) {
		this(id, Phase.RECEIVE);
		limit = lim;
	}

	public MyLoginInterceptor(PrintWriter w) {
		this();
		this.writer = w;
	}

	public MyLoginInterceptor(String id, PrintWriter w) {
		this(id, Phase.RECEIVE);
		this.writer = w;
	}

	@Override
	public void handleMessage(Message message) throws Fault {
		StringBuilder sbu = new StringBuilder();
		reading(sbu, message);
		handleMessage(sbu);
	}

	private void handleMessage(StringBuilder xml) {
		if (xml == null)
			throw new Fault(new RuntimeException("请求消息头异常"));
		InputStream is = new ByteArrayInputStream(xml.toString().getBytes());
		Document doc = getXmlDocument(is);
		Element root = doc.getDocumentElement();
		NodeList children = root.getChildNodes();
		String sId = "", userName = "", userPsw = "";
		for (int i = 0; i < children.getLength(); i++) {
			Node child = children.item(i);
			if (child instanceof Element) {
				Element childEle = (Element) child;
				if (childEle.getTagName().indexOf("Body") > -1) {
					sId = finValueByTagName(childEle, "sId");
					System.out.println("提取的sid = " + sId);
				} else if (childEle.getTagName().indexOf("Header") > -1) {
					userName = finValueByTagName(childEle, "userName");
					userPsw = finValueByTagName(childEle, "userPassWord");
					System.out.println("提取的userName = " + userName);
					System.out.println("提取的userPassWord = " + userPsw);
				}
			}
		}
		if (sId == "")
			throw new Fault(new RuntimeException("请传入正确的参数"));
		if (userName == "" || userPsw == "")
			throw new Fault(new RuntimeException("请传入正确的用户名或密码"));
		boolean success = true;//validataUserManager.validataUser(sId, userName, userPsw);
		if (success) {
			System.out.println("server:check ok");
			return;
		} else {
			System.out.println("没有通过校验");
			throw new Fault(new RuntimeException("用户名或密码不对"));
		}
	}

	@Override
	protected Logger getLogger() {
		return LOG;
	}

	/**
	 * 读取请求的XML文本
	 * 
	 * @param sbu
	 * @param message
	 * @throws Fault
	 */
	private void reading(StringBuilder sbu, Message message) throws Fault {
		String encoding = (String) message.get(Message.ENCODING);
		String ct = (String) message.get("Content-Type");
		InputStream is = (InputStream) message.getContent(InputStream.class);
		if (is != null) {
			CachedOutputStream bos = new CachedOutputStream();
			try {
				IOUtils.copy(is, bos);
				bos.flush();
				is.close();
				message.setContent(InputStream.class, bos.getInputStream());
				bos.writeCacheTo(sbu, encoding, this.limit);
				bos.close();
			} catch (IOException e) {
				throw new Fault(e);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 解析XML
	 * @param xmlIst
	 * @return
	 */
	private Document getXmlDocument(InputStream xmlIst) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();
			Document doc = builder.parse(xmlIst);
			return doc;
		} catch (SAXException | IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 提取所需要的元素
	 * @param root
	 * @param tagName
	 * @return
	 */
	private String finValueByTagName(Element root, String tagName) {
		NodeList children = root.getChildNodes();
		for (int i = 0; i < children.getLength(); i++) {
			Node child = children.item(i);
			if (child.getNodeType() == Node.ELEMENT_NODE && child instanceof Element) {
				Element childEle = (Element) child;
				NodeList params = childEle.getElementsByTagName(tagName);
				if (params.getLength() > 0 && params.item(0).getFirstChild() != null) {
					return params.item(0).getFirstChild().getNodeValue();
				}
			}
		}
		return "";
	}

//	public void setValidataUserManager(ValidataUserManager validataUserManager) {
//		this.validataUserManager = validataUserManager;
//	}

}
