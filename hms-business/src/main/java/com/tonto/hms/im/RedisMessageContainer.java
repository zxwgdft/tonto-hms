package com.tonto.hms.im;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import redis.clients.jedis.JedisCommands;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tonto2.common.im.IMServletContainer;
import com.tonto2.common.im.exception.IMException;
import com.tonto2.common.im.request.message.Message;
import com.tonto.hms.common.container.Container;
import com.tonto.redis.JedisContainer;

/**
 * 
 * 基于Redis的消息容器
 * 
 * @author TontoZhou
 * 
 */
@Component
public class RedisMessageContainer extends Thread implements Container<Message> {

	@Autowired
	private JedisContainer jedisContainer;
	
	private static final String REDIS_KEY_IM_REQUEST_LIST = "im_request_list";

	private int maxTargetSize = 20;
	
	@Override
	public void push(Message... messages) {

		JedisCommands jedis = jedisContainer.getJedis();

		for (Message message : messages) {

			String[] targets = message.getTarget();

			if (targets.length > maxTargetSize) {
				
				/*
				 * 限制消息目标数
				 * 
				 * 消息内容大小应该也限制，这里暂时未处理
				 * 
				 */
				
				int size = targets.length;

				for (int i = 0; i < size;) {
					int j = i + maxTargetSize;
					int l = j > size ? (size - i) : maxTargetSize;
					String[] ts = new String[size > maxTargetSize ? maxTargetSize : size];

					System.arraycopy(targets, i, ts, 0, l);

					i = j;

					message.setTarget(ts);
					try {
						jedis.rpush(REDIS_KEY_IM_REQUEST_LIST, message2json(message));
					} catch (JsonProcessingException e) {
					}					
				}

			} else {

				try {
					jedis.rpush(REDIS_KEY_IM_REQUEST_LIST, message2json(message));
				} catch (JsonProcessingException e) {
				}

			}
		}

	}

	/**
	 * 
	 * 放入消息的json格式字符串
	 *  
	 * @param messageJson
	 */
	public void push(String... messageJsons)
	{
		JedisCommands jedis = jedisContainer.getJedis();
		
		for(String message:messageJsons)
			jedis.rpush(REDIS_KEY_IM_REQUEST_LIST, message);
	}
	
	/**
	 * 弹出消息，在这里什么都不返回
	 */
	public Message pop() {

		// do nothing
		return null;

	}
	
	
	public void run() {
		
		while (true) {
					
			while(true)
			{	
				
				try{
					
					List<String> message = jedisContainer.getJedis().blpop(0,REDIS_KEY_IM_REQUEST_LIST);				
					IMServletContainer.getServlet().sendRequest(new SimpleMessageRequest(message.get(1)));						
				}
				catch(IMException e)
				{
					
				}
				catch(Exception e)
				{
					
				}
			}
		}	
		
	}
	
	
	private static ObjectMapper objectMapper = new ObjectMapper();

	/**
	 * message转化为json
	 * 
	 * @param message
	 * @return
	 * @throws JsonProcessingException
	 */
	private String message2json(Message message) throws JsonProcessingException {
		// 替换成小写
		String from = message.getFrom();
		if (from != null)
			message.setFrom(from.toLowerCase());

		String[] target = message.getTarget();
		if (target != null) {
			String[] newTarget = new String[target.length];
			for (int i = 0; i < target.length; i++) {
				String t = target[i];
				if (t != null)
					newTarget[i] = t.toLowerCase();
			}
			message.setTarget(newTarget);
		}

		return objectMapper.writeValueAsString(message);
	}

	public int getMaxTargetSize() {
		return maxTargetSize;
	}

	public void setMaxTargetSize(int maxTargetSize) {
		this.maxTargetSize = maxTargetSize;
	}
	
	
}
