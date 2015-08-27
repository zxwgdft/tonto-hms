package com.tonto.hms.service.loupan;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tonto.hms.dao.LoupanDao;
import com.tonto.hms.dao.support.Page;
import com.tonto.hms.dao.support.PageHelper;
import com.tonto.hms.exception.BusinessException;
import com.tonto.hms.model.District;
import com.tonto.hms.model.Loupan;
import com.tonto.hms.service.district.DistrictManager;
import com.tonto.hms.util.TreeNode;

@Service
@Transactional
public class LoupanService {
	
	@Autowired
	LoupanDao loupanDao;
	
	public List<Loupan> getAllLoupan()
	{		
		return loupanDao.findAll();
	}
	
	@SuppressWarnings("unchecked")
	public Page<Loupan> getLoupanByPage(int pageNo,int pageSize)
	{
		PageHelper.startPage(pageNo,pageSize);
		loupanDao.findAll();
		return PageHelper.endPage();
	}

	public void saveLoupan(Loupan loupan)
	{
		Integer code=loupan.getDistrictCode();
		TreeNode<District,Integer> districtNode=DistrictManager.getDistrictNode(code);
		if(districtNode==null)
			throw new BusinessException("区域不存在");
		
		int[] keys=new int[10];
		int count=keys.length;
		
	    TreeNode<District, Integer> node=districtNode;
	    do{
	    	keys[--count]=node.getValue().getCode();
	    	node = node.getParent();
	    }while(count>0&&node!=null);
		
	    if(count<10)
	    	loupan.setDistrict1(keys[count++]);
	    if(count<10)
	    	loupan.setDistrict2(keys[count++]);
	    if(count<10)
	    	loupan.setDistrict3(keys[count++]);
	    if(count<10)
	    	loupan.setDistrict4(keys[count++]);
	    
	    loupanDao.insert(loupan);
	}

	public Loupan getLoupan(int id) {
		return loupanDao.selectByPrimaryKey(id);
	}

	public int updateLoupan(Loupan loupan) {
		Integer code=loupan.getDistrictCode();
		TreeNode<District,Integer> districtNode=DistrictManager.getDistrictNode(code);
		if(districtNode==null)
			throw new BusinessException("区域不存在");
		
		loupan.setDistrict1(null);
		loupan.setDistrict2(null);
		loupan.setDistrict3(null);
		loupan.setDistrict4(null);
		
		int[] keys=new int[10];
		int count=keys.length;
		
	    TreeNode<District, Integer> node=districtNode;
	    do{
	    	keys[--count]=node.getValue().getCode();
	    	node = node.getParent();
	    }while(count>0&&node!=null);
		
	    if(count<10)
	    	loupan.setDistrict1(keys[count++]);
	    if(count<10)
	    	loupan.setDistrict2(keys[count++]);
	    if(count<10)
	    	loupan.setDistrict3(keys[count++]);
	    if(count<10)
	    	loupan.setDistrict4(keys[count++]);
	    
	    return loupanDao.updateByPrimaryKey(loupan);
	}

	public void deleteLoupan(int id) {	
		//外键关联问题
		loupanDao.deleteByPrimaryKey(id);
	}

	@SuppressWarnings("unchecked")
	public Page<Loupan> searchLoupanByPage(int pageNo,int pageSize, Loupan loupan) {

		PageHelper.startPage(pageNo,pageSize);
		if(loupan!=null)
		{
			Integer code=loupan.getDistrictCode();
			if(code!=null)
			{
				TreeNode<District, Integer> node=DistrictManager.getDistrictNode(code);
				int level=1;
				while(node.getParent()!=null)
				{
					node=node.getParent();
					level++;
				}
				switch(level)
				{
					case 1:loupan.setDistrict1(code);break;
					case 2:loupan.setDistrict2(code);break;
					case 3:loupan.setDistrict3(code);break;
					case 4:loupan.setDistrict4(code);break;
				}
			}
			loupanDao.searchAll(loupan);
		}
		else
		{
			loupanDao.findAll();
		}
		return PageHelper.endPage();
	}
}
