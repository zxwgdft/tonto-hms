package com.tonto.hms.im;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;

import com.tonto2.common.im.IMConstants;
import com.tonto2.common.im.IMServletContainer;
import com.tonto2.common.im.IMTokenHelper;
import com.tonto2.common.im.request.AbstrctIMRequest;


/**
 * 简单消息请求
 * @author TontoZhou
 *
 */
public class SimpleMessageRequest extends AbstrctIMRequest{

	private String messageJson;
	
	public SimpleMessageRequest(String messageJson)
	{
		this.messageJson=messageJson;
	}
	
	@Override
	public String getRequestDescription() {
		return "发送消息:"+messageJson;
	}

	@Override
	public HttpUriRequest createHttpRequest() {
		
		HttpPost post = new HttpPost(IMServletContainer.getServlet().createIMServerUri("messages"));

		post.addHeader("Content-Type", "application/json");
		post.addHeader("Authorization", "Bearer " + IMTokenHelper.getToken());

		post.setEntity(new StringEntity(messageJson, IMConstants.TEXT_PLAIN));
		
		return null;
	}

	public String getMessageJson() {
		return messageJson;
	}

	public void setMessageJson(String messageJson) {
		this.messageJson = messageJson;
	}

	
}
