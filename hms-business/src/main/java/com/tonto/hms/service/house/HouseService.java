package com.tonto.hms.service.house;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tonto.hms.dao.HouseDao;
import com.tonto.hms.dao.support.Page;
import com.tonto.hms.dao.support.PageHelper;
import com.tonto.hms.model.House;
import com.tonto.hms.model.HouseDetail;


@Service
@Transactional
public class HouseService {
	
	@Autowired
	HouseDao houseDao;
	
	@SuppressWarnings("unchecked")
	public Page<House> getHouseByPage(int pageNo,int pageSize)
	{
		PageHelper.startPage(pageNo, pageSize);
		houseDao.findAll();
		return PageHelper.endPage();
	}
	
	
	/**
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param houseDetail
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Page<HouseDetail> searchHouseByPage(int pageNo,int pageSize, HouseDetail houseDetail) {

		PageHelper.startPage(pageNo,pageSize);
		houseDao.searchDetailAll(houseDetail);
		return PageHelper.endPage();
	}

	public void saveHouse(House house) {
		houseDao.insert(house);
	}


	public House getHouse(int id) {
		return houseDao.selectByPrimaryKey(id);
	}


	public void updateHouse(House house) {
		houseDao.updateByPrimaryKey(house);
	}


	public void deleteHouse(int id) {
		houseDao.deleteByPrimaryKey(id);
	}

}
