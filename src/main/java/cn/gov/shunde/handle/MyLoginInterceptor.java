package cn.gov.shunde.handle;

import java.io.IOException;
import java.io.InputStream;
import java.io.SequenceInputStream;
import java.util.logging.Logger;

import org.apache.cxf.common.logging.LogUtils;
import org.apache.cxf.helpers.IOUtils;
import org.apache.cxf.interceptor.AbstractLoggingInterceptor;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.interceptor.LoggingMessage;
import org.apache.cxf.io.CachedOutputStream;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.Phase;

public class MyLoginInterceptor extends AbstractLoggingInterceptor {

	private int limit = 102400;

	private static final Logger LOG = LogUtils.getLogger(MyLoginInterceptor.class);

	public MyLoginInterceptor() {
		super(Phase.PRE_PROTOCOL);
	}

	@Override
	public void handleMessage(Message message) throws Fault {
		logging(message);

	}

	@Override
	protected Logger getLogger() {
		return LOG;
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

		InputStream is = (InputStream) message.getContent(InputStream.class);
		if (is != null) {
			CachedOutputStream bos = new CachedOutputStream();
			try {
//				IOUtils.copy(is, bos);
				IOUtils.copyAtLeast(is, bos, limit == -1 ? Integer.MAX_VALUE : limit);

				bos.flush();
				is = new SequenceInputStream(bos.getInputStream(), is);
				

				message.setContent(InputStream.class, bos.getInputStream());
				
				bos.writeCacheTo(buffer.getPayload(), encoding, limit);
//				bos.writeCacheTo(buffer.getPayload(), this.limit);
				is.close();
				bos.close();
			} catch (IOException e) {
				throw new Fault(e);
			}
		}
		// 打印日志，保存日志保存这里就可以，可写库或log4j记录日志
		System.out.println(buffer.toString());

	}

}
