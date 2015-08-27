package com.tonto.hms.service.district;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tonto.hms.dao.DistrictDao;
import com.tonto.hms.model.District;

@Service
@Transactional
public class DistrictService {
	
	@Autowired
	DistrictDao districtDao;
	
	public List<District> getAllDistrict()
	{
		return districtDao.findAll();
		
	}
}
