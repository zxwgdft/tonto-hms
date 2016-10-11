package com.tonto.hms.manager;

import java.util.Collection;
import java.util.Map;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.tonto.hms.util.SpringBeanHolder;

@SuppressWarnings("rawtypes")
@Component
public class ManagerContainer implements ApplicationListener{

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		if (event instanceof ContextRefreshedEvent) {
			ContextRefreshedEvent contextEvent = (ContextRefreshedEvent) event;

			// 由于web项目会存在两个容器导致该事件发生两次，所以在这里判断没父容器的容器发生事件才做操作
			if (contextEvent.getApplicationContext().getParent() == null) {
				Map<String,?> map=SpringBeanHolder.getBeansByType(Manager.class);
				Collection<?> coll=map.values();
				if(coll.size()>0)
				{
					@SuppressWarnings("unchecked")
					Collection<Manager> managers=(Collection<Manager>) coll;
					for(Manager manager:managers)
					{
						manager.init();
					}
					
					for(Manager manager:managers)
					{
						manager.afterInit();
					}
				}
			}		
		}
	}
	
}
