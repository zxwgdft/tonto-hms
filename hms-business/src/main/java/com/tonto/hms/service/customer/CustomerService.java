package com.tonto.hms.service.customer;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tonto.hms.dao.CustomerDao;
import com.tonto.hms.dao.base.BaseDao;
import com.tonto.hms.dao.support.Page;
import com.tonto.hms.dao.support.PageHelper;
import com.tonto.hms.model.Customer;
import com.tonto.hms.service.base.BaseModelService;

@Service
@Transactional
public class CustomerService extends BaseModelService<Integer,Customer>{

	@Autowired
	CustomerDao customerDao;
	
	protected BaseDao<Integer,Customer> getDao(){
		return customerDao;
	}
	
	@SuppressWarnings("unchecked")
	public Page<Customer> searchCustomerByPage(int pageNo, int pageSize,
			Customer customer) {
		PageHelper.startPage(pageNo, pageSize);
		customerDao.searchAll(customer);
		return PageHelper.endPage();
	}
	
	
}
