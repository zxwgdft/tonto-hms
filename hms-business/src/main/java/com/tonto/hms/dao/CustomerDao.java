package com.tonto.hms.dao;

import java.util.List;

import com.tonto.hms.dao.base.BaseDao;
import com.tonto.hms.model.Customer;

public interface CustomerDao extends BaseDao<Integer,Customer>{   
    List<Customer> searchAll(Customer customer);
}