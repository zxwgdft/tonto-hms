package com.tonto.hms.im;

import org.apache.log4j.Logger;

import com.tonto2.common.im.IMServletContainer;
import com.tonto2.common.im.exception.IMException;
import com.tonto2.common.im.request.IMRequest;

/**
 * 
 * 简单发送请求的线程
 * 
 * @author TontoZhou
 *
 */
public class SimpleSendRequestThread extends Thread{

	private static final Logger logger = Logger.getLogger(SimpleSendRequestThread.class);

	private IMRequest[] requests = new IMRequest[100];
	private int i = 0;

	private Object lock = new Object();

	public void addRequest(IMRequest request) {

		synchronized (lock) {

			if (i >= requests.length) {
				IMRequest[] newRequests = new IMRequest[requests.length * 2];
				System.arraycopy(requests, 0, newRequests, 0, i);
				requests = newRequests;
			}

			requests[i++] = request;

			lock.notifyAll();
		}

	}

	public void run() {

		IMRequest[] ts = null;

		while (true) {

			synchronized (lock) {

				if (i <= 0)
					try {
						lock.wait();
					} catch (InterruptedException e) {
						continue;
					}

				ts = new IMRequest[i];

				System.arraycopy(requests, 0, ts, 0, i);

				i = 0;
			}

			if (ts != null && ts.length > 0) {
				for (IMRequest request : ts)
					try {
						IMServletContainer.getServlet().sendRequest(request);
					} catch (IMException e) {
						logger.error("send request [" + request.getRequestDescription() + "] error:" + e.getMessage(), e);
					}
			}

		}

	}

}
