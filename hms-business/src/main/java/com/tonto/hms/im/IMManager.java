package com.tonto.hms.im;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tonto2.common.im.DefaultServletFactory;
import com.tonto2.common.im.ExceptionResponseHandler;
import com.tonto2.common.im.IMServletContainer;
import com.tonto2.common.im.IMServletFactory;
import com.tonto2.common.im.exception.IMException;
import com.tonto2.common.im.request.IMRequest;
import com.tonto2.common.im.request.RetransmissionRequest;
import com.tonto2.common.im.request.message.Message;
import com.tonto2.common.im.request.message.MessageRequest;
import com.tonto.hms.manager.Manager;

/**
 * 
 * IM Manager
 * 
 * @author TontoZhou
 * 
 */
@Component
public class IMManager implements Manager {

	@Autowired
	RedisMessageContainer redisMessageContainer;

	SimpleSendRequestThread sendRequestThread;

	@Override
	public boolean init() {
		return true;
	}

	@Override
	public boolean afterInit() {
		
		//异常处理，在401和408情况下继续存入消息再次发送
		ExceptionResponseHandler responseHandler = new ExceptionResponseHandler(){

			@Override
			public void handle(CloseableHttpResponse response, IMRequest request) {
				
				if(request instanceof RetransmissionRequest)
				{
					request = ((RetransmissionRequest) request).getRequest();
				}
				
				if(request instanceof SimpleMessageRequest)
				{
					
					int httpStatus = response.getStatusLine().getStatusCode();
					
					if(httpStatus == 401 || httpStatus == 408)
						redisMessageContainer.push(((SimpleMessageRequest)request).getMessageJson());
					
				}
				
			}
	
		};
		
		IMServletFactory servletFactory = new DefaultServletFactory();
		servletFactory.getIMServlet().setExceptionResponseHandler(responseHandler);	
		
		//设置IM SERVLET
		IMServletContainer.setServletFactory(servletFactory);
		//初始化简单发送请求线程
		sendRequestThread = new SimpleSendRequestThread();
		
		//获取待发送消息
		//从数据库读出未发出的请求...（不做操作，在redis端处理）
		
		
		//启动消息队列容器
		if(!redisMessageContainer.isAlive())
			redisMessageContainer.start();
		
		return true;
	}

	/**
	 * 同步发送IM请求
	 * 
	 * @param request
	 * @return
	 * @throws IMException
	 */
	public CloseableHttpResponse sendSynchronousRequest(IMRequest request) throws IMException {
		return IMServletContainer.getServlet().sendRequest(request);
	}

	/**
	 * <p>
	 * 异步发送消息
	 * </p>
	 * <p>
	 * </p>
	 * 
	 * @param message
	 */
	public void sendAsynchronousMessage(Message message) {
		redisMessageContainer.push(message);
	}

	/**
	 * <p>
	 * 异步发送请求
	 * </p>
	 * <p>
	 * </p>
	 * 
	 * @param request
	 */
	public void sendAsynchronousRequest(IMRequest request) {

		if (request instanceof MessageRequest) {
			sendAsynchronousMessage(((MessageRequest) request).getMessage());
		} else {
			sendRequestThread.addRequest(request);
		}
	}

}
